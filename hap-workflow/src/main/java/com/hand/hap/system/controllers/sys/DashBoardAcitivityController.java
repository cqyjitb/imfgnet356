package com.hand.hap.system.controllers.sys;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.hand.hap.activiti.service.IActivitiService;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import org.activiti.rest.common.api.DataResponse;
import org.activiti.rest.service.api.runtime.task.TaskQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * DashBoardController.
 *
 * @author zhizheng.yang@hand-china.com
 */

@Controller
public class DashBoardAcitivityController extends BaseController {

    @Autowired
    private IActivitiService activitiService;

    /**
     * 个人工作流代办仪表盘
     *
     * @param httpRequest
     */
    @RequestMapping(value = "/dashboard/task.html", method = RequestMethod.GET)
    public ModelAndView getQueryResult(HttpServletRequest httpRequest) {
        IRequest iRequest = createRequestContext(httpRequest);
        TaskQueryRequest request = new TaskQueryRequest();
        request.setCandidateOrAssigned(iRequest.getEmployeeCode());
        request.setSort("createTime");
        request.setOrder("asc");
        Map<String, String> map = new HashMap<String, String>();
        map.put("size", "20");
        DataResponse dataResponse = activitiService.queryTaskList(iRequest, request, map);
        ModelAndView view = new ModelAndView(getViewPath() + "/dashboard/task");
        view.addObject("message", dataResponse.getData());
        return view;
    }

}
