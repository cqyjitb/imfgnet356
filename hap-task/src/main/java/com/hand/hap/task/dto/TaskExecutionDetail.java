package com.hand.hap.task.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 任务/任务组执行 执行记录详情 DTO.
 *
 * @author peng.jiang@hand-china.com
 * @date 2017/11/16.
 **/

@ExtensionAttribute(disable = true)
@Table(name = "sys_task_execution_detail")
public class TaskExecutionDetail extends BaseDTO {

    public static final String FIELD_EXECUTION_DETAIL_ID = "executionDetailId";
    public static final String FIELD_EXECUTION_ID = "executionId";
    public static final String FIELD_PARAMETER = "parameter";
    public static final String FIELD_STACKTRACE = "stacktrace";
    public static final String FIELD_EXECUTION_LOG = "executionLog";

    @Id
    @GeneratedValue
    private Long executionDetailId;

    /**
     * 任务执行ID
     */
    @NotNull
    private Long executionId;

    /**
     * 参数
     */
    private String parameter;

    /**
     * 错误堆栈
     */
    private String stacktrace;

    /**
     * 执行日志
     */
    private String executionLog;

    public void setExecutionDetailId(Long executionDetailId) {
        this.executionDetailId = executionDetailId;
    }

    public Long getExecutionDetailId() {
        return executionDetailId;
    }

    public void setExecutionId(Long executionId) {
        this.executionId = executionId;
    }

    public Long getExecutionId() {
        return executionId;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public String getExecutionLog() {
        return executionLog;
    }

    public void setExecutionLog(String executionLog) {
        this.executionLog = executionLog;
    }
}
