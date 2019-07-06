/*

package com.hand.hap.activiti.demo.components;

import com.hand.hap.activiti.listeners.IUserTaskNotifier;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.impl.RequestHelper;
import com.hand.hap.hr.dto.Employee;
import com.hand.hap.hr.dto.EmployeeAssign;
import com.hand.hap.hr.dto.Position;
import com.hand.hap.hr.mapper.EmployeeAssignMapper;
import com.hand.hap.hr.mapper.EmployeeMapper;
import com.hand.hap.hr.mapper.PositionMapper;
import com.hand.hap.mail.ReceiverTypeEnum;
import com.hand.hap.mail.dto.MessageReceiver;
import com.hand.hap.mail.mapper.MessageTemplateMapper;
import com.hand.hap.mail.service.IMessageService;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * @author shengyang.zhou@hand-china.com
 *//*


//@Component
public class DemoNotifier implements IUserTaskNotifier {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final String activityEmailNotifierTemplateCode = "ACT_EMAIL_NOTIFIER";

    @Autowired
    MessageTemplateMapper templateMapper;

    @Autowired
    IMessageService messageService;

    @Autowired
    EmployeeAssignMapper employeeAssignMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    PositionMapper positionMapper;

    @Override
    public void onTaskCreate(TaskEntity task, UserEntity userEntity) {
        Map<String, Object> contentInfo = new HashMap();
        contentInfo.put("dom", task.getName());
        contentInfo.put("receiver", userEntity.getFirstName());
        contentInfo.put("content", task.getDescription());
        IRequest iRequest = RequestHelper.getCurrentRequest();

        List<MessageReceiver> receivers = new ArrayList<>();
        MessageReceiver receiver = new MessageReceiver();
        receiver.setMessageAddress(userEntity.getEmail());
        receiver.setMessageType(ReceiverTypeEnum.NORMAL.getCode());
        receivers.add(receiver);
        try {
            messageService.addEmailMessage(iRequest.getUserId(), "TEST", activityEmailNotifierTemplateCode, contentInfo,
                    null, receivers);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void onTaskCreate(TaskEntity task, GroupEntity groupEntity) {
        Position position = positionMapper.getPositionByCode(groupEntity.getId());
        Long positionId = position.getPositionId();
        EmployeeAssign ass = new EmployeeAssign();
        ass.setPositionId(positionId);
        List<EmployeeAssign> employeeAssign = employeeAssignMapper.select(ass);

        if (employeeAssign.isEmpty()) {
            logger.error("NO EMPLOYEE");
        } else {
            for (EmployeeAssign employee : employeeAssign) {
                Employee employee1 = employeeMapper.selectByPrimaryKey(employee.getEmployeeId());

                Map<String, Object> contentInfo = new HashMap();
                contentInfo.put("dom", task.getName());
                contentInfo.put("receiver", employee1.getName());
                contentInfo.put("content", task.getDescription());
                IRequest iRequest = RequestHelper.getCurrentRequest();

                List<MessageReceiver> receivers = new ArrayList<>();
                MessageReceiver receiver = new MessageReceiver();
                receiver.setMessageAddress(employee1.getEmail());
                receiver.setMessageType(ReceiverTypeEnum.NORMAL.getCode());
                receivers.add(receiver);
                try {
                    messageService.addEmailMessage(iRequest.getUserId(), "TEST", activityEmailNotifierTemplateCode, contentInfo,
                            null, receivers);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }
}
*/
