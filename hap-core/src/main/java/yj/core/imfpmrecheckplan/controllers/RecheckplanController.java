package yj.core.imfpmrecheckplan.controllers;

import com.hand.hap.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import yj.core.imfpmrecheckplan.service.IRecheckplanService;

@Controller
public class RecheckplanController extends BaseController {

    @Autowired
    private IRecheckplanService service;

}