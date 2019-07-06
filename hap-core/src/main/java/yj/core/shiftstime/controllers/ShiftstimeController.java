package yj.core.shiftstime.controllers;

import com.hand.hap.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import yj.core.shiftstime.service.IShiftstimeService;

@Controller
public class ShiftstimeController extends BaseController {

    @Autowired
    private IShiftstimeService service;

}