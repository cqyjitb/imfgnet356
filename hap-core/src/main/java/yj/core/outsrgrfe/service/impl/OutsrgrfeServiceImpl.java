package yj.core.outsrgrfe.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.outsrgrfe.dto.Outsrgrfe;
import yj.core.outsrgrfe.mapper.OutsrgrfeMapper;
import yj.core.outsrgrfe.service.IOutsrgrfeService;

import java.util.List;

@Service
@Transactional
public class OutsrgrfeServiceImpl extends BaseServiceImpl<Outsrgrfe> implements IOutsrgrfeService {
    @Autowired
    private OutsrgrfeMapper outsrgrfeMapper;
    @Override
    public int insertOutsrgrfe(Outsrgrfe outsrgrfe) {
        return outsrgrfeMapper.insertOutsrgrfe(outsrgrfe);
    }

    @Override
    public Outsrgrfe selectByCondition(String werks, String aufnr, String vornr, String matnr, String lifnr, String ebeln, String ebelp) {
        return outsrgrfeMapper.selectByCondition(werks,aufnr,vornr,matnr,lifnr,ebeln,ebelp);
    }

    @Override
    public int updateByCondition(Outsrgrfe outsrgrfe) {
        return outsrgrfeMapper.updateByCondition(outsrgrfe);
    }

    @Override
    public List<Outsrgrfe> selectForSortl(String lifnr) {
        return outsrgrfeMapper.selectForSortl(lifnr);
    }

    @Override
    public List<Outsrgrfe> selectAllLifnr() {
        return outsrgrfeMapper.selectAllLifnr();
    }

    @Override
    public List<Outsrgrfe> sapquery(String werks, String matnr, String matnr_l, String matnr_h, String lifnr, String lifnr_l, String lifnr_h, String ktsch, String ktsch_l, String ktsch_h) {
        return outsrgrfeMapper.sapquery(werks,matnr,matnr_l,matnr_h,lifnr,lifnr_l,lifnr_h,ktsch,ktsch_l,ktsch_h);
    }
}