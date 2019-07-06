package com.hand.hap.task.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.hand.hap.code.rule.exception.CodeRuleException;
import com.hand.hap.code.rule.service.ISysCodeRuleProcessService;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.core.exception.BaseException;
import com.hand.hap.system.mapper.ParameterConfigMapper;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import com.hand.hap.task.TaskConstants;
import com.hand.hap.task.dto.TaskDetail;
import com.hand.hap.task.dto.TaskExecution;
import com.hand.hap.task.dto.TaskExecutionDetail;
import com.hand.hap.task.exception.TaskExecuteException;
import com.hand.hap.task.exception.TaskInterruptException;
import com.hand.hap.task.exception.TaskInvalidException;
import com.hand.hap.task.info.ParameterInfo;
import com.hand.hap.task.info.TaskDataInfo;
import com.hand.hap.task.mapper.TaskAssignMapper;
import com.hand.hap.task.mapper.TaskDetailMapper;
import com.hand.hap.task.mapper.TaskExecutionDetailMapper;
import com.hand.hap.task.mapper.TaskExecutionMapper;
import com.hand.hap.task.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 任务/任务组 Service - 实现类.
 *
 * @author lijian.yin@hand-china.com
 * @date 2017/11/15.
 **/

@Service
public class TaskDetailServiceImpl extends BaseServiceImpl<TaskDetail> implements ITaskDetailService, ProxySelf<ITaskDetailService>{

    private static final String TASK_EXECUTION_CODE = "TASK_EXECUTION_CODE";

    private Logger logger = LoggerFactory.getLogger(TaskDetailServiceImpl.class);

    @Autowired
    private TaskDetailMapper taskDetailMapper;

    @Autowired
    private TaskAssignMapper taskAssignMapper;

    @Autowired
    private TaskExecutionMapper taskExecutionMapper;

    @Autowired
    private TaskExecutionDetailMapper taskExecutionDetailMapper;

    @Autowired
    private ParameterConfigMapper parameterConfigMapper;

    @Autowired
    private ITaskExecutionService iTaskExecutionService;

    @Autowired
    private ITaskExecutionDetailService iTaskExecutionDetailService;

    @Autowired
    private IExecuteService iExecuteService;

    @Autowired
    private ISysCodeRuleProcessService iSysCodeRuleProcessService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ITaskAssignService iTaskAssignService;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<TaskDetail> queryChildrenByPrimaryKey(List<String> idList) {
        return taskDetailMapper.queryChildrenByPrimaryKey(idList);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<TaskDetail> queryUnboundTasks(IRequest request, TaskDetail dto, List<String> idList,
                                              int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return taskDetailMapper.queryUnboundTasks(dto, idList);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public int updateChildrenTasks(IRequest iRequest, TaskDetail data) {
        List<TaskDetail> children = data.getChildrenTasks();
        List<Long> ids = new ArrayList<>();
        children.sort(Comparator.comparing(TaskDetail::getOrder));
        for (TaskDetail taskDetail : children) {
            ids.add(taskDetail.getTaskId());
        }

        TaskDetail taskDetail = new TaskDetail();
        taskDetail.setIds(StringUtils.join(ids, ","));
        taskDetail.setTaskId(data.getTaskId());
        return mapper.updateByPrimaryKeySelective(taskDetail);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public TaskDetail getGroupById(IRequest requestContext , TaskDetail taskDetail) {
        TaskDetail group = mapper.selectByPrimaryKey(taskDetail);
        if (group.getIds() != null){
            List<String> idList = Arrays.asList(group.getIds().split(","));
            List<TaskDetail> childrenTasks = queryChildrenByPrimaryKey(idList);
            List<TaskDetail> childrens = new ArrayList<>();
            idList.forEach(id -> {
                childrenTasks.forEach(children -> {
                    if (children.getTaskId().toString().equals(id)){
                        childrens.add(children);
                        children.setOrder(childrens.size());
                    }
                });
            });
            group.setChildrenTasks(childrens);
        }
        return group;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<TaskDetail> queryTaskDetail(IRequest iRequest, TaskDetail taskDetail) throws BaseException {

        List<String> idList = new ArrayList<>();
        if (StringUtil.isNotEmpty(taskDetail.getIds())) {
            idList = Arrays.asList(taskDetail.getIds().split(","));
        }
        List<TaskDetail> taskDetails = taskDetailMapper.queryTask(taskDetail, idList);
        TaskDetail task = taskDetails.get(0);
        List<String> msg = new ArrayList<>();
        taskInvalid(task, msg);
        List<TaskDetail> subtaskList = new ArrayList<>();
        for (int i = 0, j = idList.size(); i < j; i++) {
            for (int n = 1, m = taskDetails.size(); n < m; n++) {
                if (idList.get(i).equals(taskDetails.get(n).getTaskId() + "")) {
                    taskInvalid(taskDetails.get(n), msg);
                    subtaskList.add(taskDetails.get(n));
                    break;
                }
            }
        }
        if (CollectionUtils.isNotEmpty(msg)) {
            String message = StringUtils.join(msg, ",");
            throw new TaskInvalidException(TaskInvalidException.MSG_TASK_INVALID, new Object[]{message});
        }
        task.setChildrenTasks(subtaskList);
        return Arrays.asList(task);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<TaskDetail> executeQuery(IRequest iRequest, TaskDetail taskDetail, int page, int pageSize) {

        // 权限 返回 Task ids
        List<Long> taskIds = iTaskAssignService.queryTaskId(iRequest, false);
        if (taskIds.size() == 0) {
            return new ArrayList<>();
        }
        PageHelper.startPage(page, pageSize);

        return taskDetailMapper.queryByTask(taskDetail, taskIds);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<String> execute(IRequest iRequest, TaskDataInfo taskDataInfo) throws BaseException {
        try {
            String code = getRoleCode();
            Long executionId = self().insertExecution(iRequest, taskDataInfo, code);
            taskDataInfo.setUsername(iRequest.getUserName());
            iExecuteService.taskExecute(taskDataInfo);
            return Arrays.asList(code, executionId + "");
        } catch (CodeRuleException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TaskExecuteException(TaskExecuteException.CODE_EXECUTE_ERROR, TaskExecuteException.MSG_SERVER_BUSY);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Long insertExecution(IRequest iRequest, TaskDataInfo taskData, String code) {

        Long executionId;

        //更新上一次执行时间
        TaskExecution taskExecution = taskExecutionMapper.getLastExecuteDate(taskData.getTaskId());
        if (taskExecution != null) {
            executionId = insertExecutionAndDetail(iRequest, taskData, 0L, code, null, taskExecution.getStartTime());
        } else {
            executionId = insertExecutionAndDetail(iRequest, taskData, 0L, code, null, null);
        }

        taskData.setExecutionId(executionId);

        List<TaskDataInfo> taskItem = taskData.getTaskDatas();
        if (null != taskItem) {
            for (int i = 0, j = taskItem.size(); i < j; i++) {
                TaskDataInfo data = taskItem.get(i);
                Long id = insertExecutionAndDetail(iRequest, data, i + 1L, null, executionId, null);
                data.setExecutionId(id);
            }
        }
        return executionId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<TaskDetail> list) {
        batchDelete(list);
        list.forEach(taskDetail -> {
            taskAssignMapper.deleteByTaskId(taskDetail.getTaskId());
            if (taskDetail.getType().equals(TaskConstants.TASK_TYPE_TASK)) {
                parameterConfigMapper.deleteByTaskId(taskDetail.getTaskId());
            }
        });

        List<TaskExecution> executionList = new ArrayList<>();
        List<TaskExecution> deleteList = new ArrayList<>();
        list.forEach(taskDetail -> {
            List<TaskExecution> executions = taskExecutionMapper.selectByTaskId(taskDetail.getTaskId());
            executionList.addAll(executions);
        });
        executionList.forEach(execution -> {
            List<TaskExecution> executions = taskExecutionMapper.selectByExeId(execution.getExecutionId());
            deleteList.addAll(executions);
        });

        deleteList.forEach(execution -> {
            taskExecutionDetailMapper.deleteByExecutionId(execution.getExecutionId());
        });
        iTaskExecutionService.batchDelete(deleteList);

    }

    /**
     * 判断任务是否失效
     *
     * @param taskDetail 任务/任务组
     * @param msg        判定信息
     */
    private void taskInvalid(TaskDetail taskDetail, List<String> msg) throws TaskInvalidException {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        try {
            Long now = sdf.parse(dateString).getTime();
            boolean start = taskDetail.getStartDate() == null || now >= taskDetail.getStartDate().getTime();
            boolean end = taskDetail.getEndDate() == null || now <= taskDetail.getEndDate().getTime();
            if (!(start && end)) {
                msg.add(taskDetail.getCode());
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * 编码规则获取execution code
     *
     * @return
     */
    private String getRoleCode() throws CodeRuleException {
        return iSysCodeRuleProcessService.getRuleCode(TASK_EXECUTION_CODE);
    }

    /**
     * 添加任务执行记录与执行记录详情.
     *
     * @param iRequest    IRequest
     * @param taskData    任务封装类
     * @param order       执行顺序
     * @param code        编码
     * @param executionId 执行记录ID
     * @param lastExecuteDate 上一次执行时间
     * @return 执行记录ID
     */
    private Long insertExecutionAndDetail(IRequest iRequest, TaskDataInfo taskData,
                                          Long order, String code, Long executionId, Date lastExecuteDate) {

        TaskExecution taskExecution = new TaskExecution();
        taskExecution.setExecutionNumber(code);
        taskExecution.setTaskId(taskData.getTaskId());
        taskExecution.setExecutionOrder(order);
        taskExecution.setParentId(executionId);
        if (null != taskData.getDescription()) {
            taskExecution.setExecutionDescription(taskData.getDescription());
        }
        if (null != lastExecuteDate) {
            taskExecution.setLastExecuteDate(lastExecuteDate);
        }

        iTaskExecutionService.insertExecution(iRequest, taskExecution);

        //  存参数 表 sys_task_execution_detail
        TaskExecutionDetail taskExecutionDetail = new TaskExecutionDetail();
        taskExecutionDetail.setExecutionId(taskExecution.getExecutionId());
        taskExecutionDetail.setParameter(createParam(taskData.getParam()));
        iTaskExecutionDetailService.insertSelective(iRequest, taskExecutionDetail);
        return taskExecution.getExecutionId();

    }

    /**
     * 拼接执行参数.
     *
     * @param params 执行参数
     * @return 参数字符串
     */
    private String createParam(List<ParameterInfo> params) {
        if (null == params) {
            return "";
        }
        try {
            String paramString = objectMapper.writeValueAsString(params);
            return paramString.replaceAll("null", "\"\"");
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            return "";
        }
    }

    @Override
    protected boolean useSelectiveUpdate() {
        return false;
    }

}