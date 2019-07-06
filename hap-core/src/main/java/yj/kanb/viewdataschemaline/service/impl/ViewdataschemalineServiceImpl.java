package yj.kanb.viewdataschemaline.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.viewdataschemaline.dto.Viewdataschemaline;
import yj.kanb.viewdataschemaline.mapper.ViewdataschemalineMapper;
import yj.kanb.viewdataschemaline.service.IViewdataschemalineService;

@Service
@Transactional
public class ViewdataschemalineServiceImpl extends BaseServiceImpl<Viewdataschemaline> implements IViewdataschemalineService{
    @Autowired
    private ViewdataschemalineMapper mapper;
    @Override
    public Viewdataschemaline selectforKanb(String groupId, String matnr, String deptId, String bukrs, String werks) {
        return mapper.selectforKanb(groupId,matnr,deptId,bukrs,werks);
    }

    @Override
    public int insertforKanb(Viewdataschemaline viewdata) {
        return mapper.insertforKanb(viewdata);
    }

    @Override
    public int updateforKanb(Viewdataschemaline viewdata) {
        return mapper.updateforKanb(viewdata);
    }
}
