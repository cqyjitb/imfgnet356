package com.hand.hap.activiti.controllers;

import javax.servlet.http.HttpServletRequest;

import com.hand.hap.activiti.components.ActivitiMultiLanguageManager;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.impl.RequestHelper;
import io.swagger.annotations.ApiParam;
import org.activiti.rest.service.api.repository.ProcessDefinitionResource;
import org.activiti.rest.service.api.repository.ProcessDefinitionResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiang.zeng
 * @date 2018/9/13.
 */
@RestController
@RequestMapping(value = {"/wfl", "/api/wfl"})
public class ProcessDefinitionRestResource extends ProcessDefinitionResource {


    @RequestMapping(value = "/repository/process-definitions/{processDefinitionId}", method = RequestMethod.GET, produces = "application/json")
    public ProcessDefinitionResponse getProcessDefinition(@ApiParam(name = "processDefinitionId", value = "The id of the process definition to get.") @PathVariable String processDefinitionId, HttpServletRequest request) {
        ProcessDefinitionResponse processDefinitionResponse = super.getProcessDefinition(processDefinitionId,request);
        IRequest iRequest = RequestHelper.createServiceRequest(request);
        processDefinitionResponse.setName(ActivitiMultiLanguageManager.getMultLanguageInfoByCode(processDefinitionResponse.getName(), iRequest));
        return processDefinitionResponse;
    }
}
