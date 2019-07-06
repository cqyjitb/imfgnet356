package yj.core.qppdrcd.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.qppdrcd.dto.Qppdrcd;

import java.util.List;

public interface IQppdrcdService extends IBaseService<Qppdrcd>, ProxySelf<IQppdrcdService> {
    int insertPdRow(Qppdrcd qppdrcd);

    List<Qppdrcd> queryAll(String werks, String pddatbefore, String pddatafter, String fevor, int page, int pageSize, IRequest iRequest);
}