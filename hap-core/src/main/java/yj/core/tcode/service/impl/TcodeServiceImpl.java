package yj.core.tcode.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.tcode.dto.Tcode;
import yj.core.tcode.mapper.TcodeMapper;
import yj.core.tcode.service.ITcodeService;

import java.util.List;

@Service
@Transactional
public class TcodeServiceImpl extends BaseServiceImpl<Tcode> implements ITcodeService {
    @Autowired
    private TcodeMapper tcodeMapper;

    @Override
    public String updateOrInsert(IRequest request, List<Tcode> dto) {
        int insertnum = 0;
        int updatenum = 0;
        if (dto.size() > 0){
            for (int i=0;i<dto.size();i++){
                Tcode tcode = dto.get(i);
                //判断是否存在
                int num = tcodeMapper.selectByUserName(tcode.getUserName());
                if (num == 1){
                    tcodeMapper.update(tcode);
                }else{
                    tcodeMapper.insert(tcode);
                }
            }
        }
        return "更新成功！";
    }

    @Override
    public int isExit(Tcode dto) {
        return tcodeMapper.isExit(dto);
    }

    @Override
    public int selectByUserName(String username) {
        return tcodeMapper.selectByUserName(username);
    }

    @Override
    public Tcode getProfile(String userName) {
        return tcodeMapper.getProfile(userName);
    }
}