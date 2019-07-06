package yj.core.imfpmcheckdoc.controllers;


import com.hand.hap.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import yj.core.imfpmcheckdoc.service.ICheckdocService;

@Controller
public class CheckdocController extends BaseController {

    @Autowired
    private ICheckdocService service;

}