package yj.core.zrwklist.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.mapper.InOutRecordMapper;
import yj.core.qjcode.dto.Qjcode;
import yj.core.qjcode.mapper.QjcodeMapper;
import yj.core.zrwklist.dto.Zrwklist;
import yj.core.zrwklist.mapper.ZrwklistMapper;
import yj.core.zrwklist.service.IZrwklistService;

import java.util.List;

@Service
@Transactional
public class ZrwklistServiceImpl extends BaseServiceImpl<Zrwklist> implements IZrwklistService{
    @Autowired
    private ZrwklistMapper zrwklistMapper;
    @Autowired
    private InOutRecordMapper inOutRecordMapper;
    @Autowired
    private QjcodeMapper qjcodeMapper;
    @Override
    public int insertItem(List<Zrwklist> list) {
        int sum = 0;
        if (list.size() > 0){
            for(int i=0;i<list.size();i++){
                sum = sum + zrwklistMapper.insertItem(list.get(i));
            }

        }
        return sum;
    }

    @Override
    public List<Zrwklist> selectZrwklist(IRequest requestContext, Zrwklist dto) {
        //PageHelper.startPage(page,pageSize);
        List<Zrwklist> list = zrwklistMapper.selectZrwklist(dto);
        if(list.size() > 0){
            for(int i=0;i<list.size();i++){
                Zrwklist zrwklist = list.get(i);
                InOutRecord inOutRecord = inOutRecordMapper.selectById(zrwklist.getZqjjlh());
                Qjcode qjcode = qjcodeMapper.selectById(Integer.valueOf(zrwklist.getZotype()));
                zrwklist.setVornr_old(inOutRecord.getVornr());
                zrwklist.setRcode(qjcode.getRcode());
            }
        }
        return list;
    }
}