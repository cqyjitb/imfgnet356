package yj.core.qcauditlist.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.qcauditlist.dto.Qcauditlist;
import yj.core.qcauditlist.mapper.QcauditlistMapper;
import yj.core.qcauditlist.service.IQcauditlistService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QcauditlistServiceImpl extends BaseServiceImpl<Qcauditlist> implements IQcauditlistService{
    @Autowired
    private QcauditlistMapper qcauditlistMapper;
    @Override
    public int insertNewRow(List<Qcauditlist> list) {
        int num = 0;
        if (list.size() > 0){
            for (int i=0;i<list.size();i++){
                num = num + qcauditlistMapper.insertNewRow(list.get(i));
            }
        }
        return num;
    }

    @Override
    public List<Qcauditlist> selectById(String werks, String recordid) {
        return qcauditlistMapper.selectById(werks,recordid);
    }

    @Override
    public List<Qcauditlist> selectCounts(String werks, String recordid) {
        return qcauditlistMapper.selectCounts(werks,recordid);
    }

    @Override
    public int deleteById(String werks, String recordid) {
        return qcauditlistMapper.deleteById(werks,recordid);
    }

//    @Override
//    public List<Qcauditlist> selectBatch(List<Qcauditlist> list) {
//        List<Qcauditlist>  returnlist = new ArrayList<>();
//        for (int i=0;i<list.size();i++){
//            Qcauditlist qcauditlist = qcauditlistMapper.selectBatch(list.get(i).getWerks(),list.get(i).getRecordid(),list.get(i).getItem());
//            list.add(qcauditlist);
//        }
//        return null;
//    }


    @Override
    public Qcauditlist selectBatch(String werks, String recordid, String item) {
                List<Qcauditlist>  returnlist = new ArrayList<>();
                Qcauditlist qcauditlist = new Qcauditlist();
        qcauditlist = qcauditlistMapper.selectBatch(werks,recordid,item);


       return qcauditlist;
    }

    @Override
    public int updateStatus(List<Qcauditlist> list) {
        int num  = 0;
        for (int i=0;i<list.size();i++){
            num = qcauditlistMapper.updateStatus(list.get(i));
        }
        return num;
    }
}
