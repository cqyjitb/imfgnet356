package yj.core.pmimptt.controllers;

import com.hand.hap.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import yj.core.pmimptt.service.IImpttService;

@Controller
public class ImpttController extends BaseController {

    @Autowired
    private IImpttService service;

}