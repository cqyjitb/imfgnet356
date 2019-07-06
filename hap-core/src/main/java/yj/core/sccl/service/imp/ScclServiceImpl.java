package yj.core.sccl.service.imp;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.sccl.dto.Sccl;
import yj.core.sccl.mapper.ScclMapper;
import yj.core.sccl.service.IScclService;

import java.util.List;

@Service
@Transactional
public class ScclServiceImpl extends BaseServiceImpl<Sccl> implements IScclService {
    @Autowired
    private ScclMapper scclMapper;
    @Override
    public Sccl selectByMatnr(String matnr, String werks) {
        return scclMapper.selectByMatnr(matnr,werks);
    }

    @Override
    public int batchModify(List<Sccl> list) {
        int sum = 0;
        for( int i = 0;i<list.size();i++){
            if (this.isExit(list.get(i).getMatnr(),list.get(i).getWerks())){
                sum = sum  + scclMapper.updateSccl(list.get(i));

            }else{
                sum = sum +scclMapper.insertSccl(list.get(i));
            }
        }
        return sum;
    }

    @Override
    public boolean isExit(String matnr, String werks) {
        int i = scclMapper.isExit(matnr,werks);
        if ( i == 1){
            return true;
        }else{
            return false;
        }
    }


}