package yj.kanb.viewdataschemaline.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.kanb.viewdataschemaline.dto.Viewdataschemaline;

public interface IViewdataschemalineService extends IBaseService<Viewdataschemaline>,ProxySelf<IViewdataschemalineService> {
    Viewdataschemaline selectforKanb(String groupId, String matnr, String deptId, String bukrs, String werks);
    int insertforKanb(Viewdataschemaline viewdata);
    int updateforKanb(Viewdataschemaline viewdata);
}
