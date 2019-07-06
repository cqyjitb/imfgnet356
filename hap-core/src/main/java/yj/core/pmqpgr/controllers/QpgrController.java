package yj.core.pmqpgr.controllers;

import com.hand.hap.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import yj.core.pmqpgr.service.IQpgrService;

@Controller
public class QpgrController extends BaseController {

    @Autowired
    private IQpgrService service;

}