package yj.core.wipdot.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wipdot.dto.Dot;
import yj.core.wipdot.mapper.DotMapper;
import yj.core.wipdot.service.IDotService;
import yj.core.wipusers.mapper.UsersMapper;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DotServiceImpl extends BaseServiceImpl<Dot> implements IDotService{
    @Autowired
    private DotMapper dotMapper;
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<Dot> selectFromPage(IRequest requestContext, Dot dto, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return dotMapper.selectFromPage(dto);
    }

    @Override
    public String updateOrInsert(IRequest requestCtx, List<Dot> dto, String userName) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                Dot dot = dto.get(i);
                if("Y".equals(dot.getEnableFlag())){
                    dot.setEnableFlag("1");
                }else{
                    dot.setEnableFlag("0");
                }if("Y".equals(dot.getFirstFlag())){
                    dot.setFirstFlag("1");
                }else{
                    dot.setFirstFlag("0");
                }if ("Y".equals(dot.getEndFlag())) {
                    dot.setEndFlag("1");
                }else{
                    dot.setEndFlag("0");
                }if ("Y".equals(dot.getMonitorFlag())) {
                    dot.setMonitorFlag("1");
                }else{
                    dot.setMonitorFlag("0");
                }
                if (dot.getDotId() != null){
                    dot.setLastUpdatedDate(new Date());
                    dot.setLastUpdatedBy(userName);
                    dotMapper.updateDot(dot);
                }else{
                    dot.setCreationDate(new Date());
                    dot.setCreatedBy(userName);
                    dotMapper.insertDot(dot);
                }
            }
        }
        return null;
    }

    @Override
    public String deleteDot(List<Dot> dto) {
        if(dto.size() > 0){
            for (int i=0;i<dto.size();i++){
                Dot dot = dto.get(i);
                int num = usersMapper.selectDotId(dot.getDotId());
                if(num != 0){
                    return "采集点Id已使用，不允许删除！";
                }else{
                    dotMapper.delete(dot);
                }
            }
        }
        return null;
    }
}
