package yj.core.pmcabn.controllers;

import com.hand.hap.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import yj.core.pmcabn.service.ICabnService;

@Controller
public class CabnController extends BaseController {

    @Autowired
    private ICabnService service;

}