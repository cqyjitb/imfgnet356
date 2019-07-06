package com.hand.hap.task.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.hap.task.dto.TaskDetail;
import com.hand.hap.task.info.TaskDataInfo;

import java.util.List;

/**
 * 任务/任务组 Service.
 *
 * @author lijian.yin@hand-china.com
 * @date 2017/11/6.
 **/

public interface ITaskDetailService extends IBaseService<TaskDetail>, ProxySelf<ITaskDetailService> {

    /**
     * 通过子任务ID查询子任务列表.
     *
     * @param idList 子任务Id集合
     * @return 子任务集合
     */
    List<TaskDetail> queryChildrenByPrimaryKey(List<String> idList);

    /**
     * 查找任务组未绑定的任务.
     *
     * @param request  IRequest
     * @param dto      任务
     * @param idList   子任务ID集合
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     * @return 任务列表
     */
    List<TaskDetail> queryUnboundTasks(IRequest request, TaskDetail dto, List<String> idList, int pageNum, int pageSize);

    /**
     * 执行任务.
     *
     * @param iRequest     IRequest
     * @param taskDataInfo 任务对象封装类
     * @return 任务执行记录ID与任务代码
     * @throws Exception 异常
     */
    List<String> execute(IRequest iRequest, TaskDataInfo taskDataInfo) throws Exception;

    /**
     * 更新子任务.
     *
     * @param iRequest IRequest
     * @param data     任务（包含子任务）
     * @return 更新数量
     */
    int updateChildrenTasks(IRequest iRequest, TaskDetail data);

    /**
     * 条件查询任务组.
     *
     * @param requestContext IRequest
     * @param taskDetail     任务
     * @return 任务组
     */
    TaskDetail getGroupById(IRequest requestContext, TaskDetail taskDetail);

    /**
     * 查询指定任务/任务组 详细信息.
     *
     * @param iRequest   IRequest
     * @param taskDetail 任务
     * @return 任务/任务组 集合
     * @throws Exception
     */
    List<TaskDetail> queryTaskDetail(IRequest iRequest, TaskDetail taskDetail) throws Exception;

    /**
     * 任务执行集合.
     *
     * @param iRequest   IRequest
     * @param taskDetail 任务
     * @param page       页码
     * @param pageSize   每页显示数量
     * @return 任务执行集合
     */
    List<TaskDetail> executeQuery(IRequest iRequest, TaskDetail taskDetail, int page, int pageSize);

    /**
     * 删除所有任务关联信息.
     *
     * @param list 任务集合
     */
    void remove(List<TaskDetail> list);

    /**
     * 插入执行记录.
     *
     * @param iRequest IRequest
     * @param taskData 任务数据封装类
     * @param code     编码
     * @return 执行记录ID
     */
    Long insertExecution(IRequest iRequest, TaskDataInfo taskData, String code);

}