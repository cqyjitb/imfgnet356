package yj.core.crhd.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.crhd.dto.Crhd;
import yj.core.crhd.mapper.CrhdMapper;
import yj.core.crhd.service.ICrhdService;

import java.util.List;

@Service
@Transactional
public class CrhdServiceImpl extends BaseServiceImpl<Crhd> implements ICrhdService{
    @Autowired
    private CrhdMapper crhdMapper;
    @Override
    public int selectnumByObjid(String objid) {
        return crhdMapper.selectnumByObjid(objid);
    }

    @Override
    public int updateRows(List<Crhd> list) {
        int num = 0;
        for (int i = 0;i<list.size();i++){
            num = num + 1;
            crhdMapper.updateRow(list.get(i));
        }
        return num;
    }

    @Override
    public int insertRows(List<Crhd> list) {
        int num = 0;
        for (int i = 0;i<list.size();i++){
            num = num + 1;
            crhdMapper.insertRow(list.get(i));
        }
        return num;
    }

    @Override
    public List<Crhd> selecByWerksAndArbpl(String werks, String arbpl) {
        return crhdMapper.selecByWerksAndArbpl(werks,arbpl);
    }
}