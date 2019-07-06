package com.hand.hap.activiti.demo.service;

import com.hand.hap.activiti.demo.dto.DemoVacation;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;

import java.util.List;

public interface IDemoVacationService extends IBaseService<DemoVacation>, ProxySelf<IDemoVacationService> {

    void createVacationInstance(IRequest iRequest, DemoVacation demoVacation);

    List<DemoVacation> selectVacationHistory(IRequest iRequest);

}