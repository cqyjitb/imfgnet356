package yj.core.imfpmimptttemp.controllers;

import com.hand.hap.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import yj.core.imfpmimptttemp.service.IImpttTempService;

@Controller
public class ImpttTempController extends BaseController {

    @Autowired
    private IImpttTempService service;

}