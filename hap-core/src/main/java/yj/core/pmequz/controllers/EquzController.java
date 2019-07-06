package yj.core.pmequz.controllers;

import com.hand.hap.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import yj.core.pmequz.service.IEquzService;

@Controller
public class EquzController extends BaseController {

    @Autowired
    private IEquzService service;

}