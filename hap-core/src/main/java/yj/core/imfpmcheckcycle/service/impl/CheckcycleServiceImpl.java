package yj.core.imfpmcheckcycle.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.imfpmcheckcycle.dto.Checkcycle;
import yj.core.imfpmcheckcycle.mapper.CheckcycleMapper;
import yj.core.imfpmcheckcycle.service.ICheckcycleService;
import yj.core.imfpmcheckdoc.dto.Checkdoc;
import yj.core.imfpmcheckdoc.mapper.CheckdocMapper;
import yj.core.imfpmcheckplan.dto.Checkplan;
import yj.core.imfpmcheckplan.mapper.CheckplanMapper;
import yj.core.pmimptt.dto.Imptt;
import yj.core.pmimptt.mapper.ImpttMapper;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CheckcycleServiceImpl extends BaseServiceImpl<Checkcycle> implements ICheckcycleService {

    @Autowired
    private CheckcycleMapper checkcycleMapper;
    @Autowired
    private CheckdocMapper checkdocMapper;
    @Autowired
    private CheckplanMapper checkplanMapper;
    @Autowired
    private ImpttMapper impttMapper;

    @Override
    public List<Checkcycle> selectCheckcycle(IRequest requestContext, Integer id, String checkcycle, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return checkcycleMapper.selectCheckcycle(id,checkcycle);
    }

    @Override
    public int selectMaxCheckcycle() {
        return checkcycleMapper.selectMaxCheckcycle();
    }

    @Override
    public String updateCheckcycle(List<Checkcycle> dto, String userName) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                List<Checkcycle> list = checkcycleMapper.selectCheckcycle(null,dto.get(i).getCheckcycle());
                if(list.size() > 0){
                    return "周期描述已存在，不能保存！";
                }else{
                    Checkcycle checkcycle = dto.get(i);
                    List<Checkcycle> list1 = checkcycleMapper.selectCheckcycle(checkcycle.getId(),null);
                    DateFormat df = DateFormat.getDateInstance();
                    DateFormat dft = DateFormat.getTimeInstance();
                    Date dateTime = new Date();
                    String date = df.format(dateTime);
                    String time = dft.format(dateTime);
                    if(list1.size() == 1){
                        checkcycle.setChdate(date);
                        checkcycle.setChtime(time);
                        checkcycle.setChuser(userName);
                        checkcycleMapper.updateCheckcycle(checkcycle);
                    }else{
                        checkcycle.setCrdate(date);
                        checkcycle.setCrtime(time);
                        checkcycle.setCruser(userName);
                        checkcycleMapper.insertCheckcycle(checkcycle);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String deleteCheckcycle(List<Checkcycle> dto, String userName) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                Checkcycle checkcycle = dto.get(i);
                List<Checkplan> checkplan = checkplanMapper.selectCheckcycleAndCheckreq(checkcycle.getCheckcycle(),null);
                List<Imptt> imptt = impttMapper.selectCheckcycleAndCheckreq(checkcycle.getCheckcycle(),null);
                if(checkplan.size()>0 || imptt.size()>0){
                    return "该周期已被使用，不允许删除";
                }else{
                    List<Checkdoc> checkdoc = checkdocMapper.selectCheckcycleAndCheckreq(checkcycle.getCheckcycle(),null);
                    if(checkdoc.size() > 0){
                        DateFormat df = DateFormat.getDateInstance();
                        DateFormat dft = DateFormat.getTimeInstance();
                        Date dateTime = new Date();
                        String date = df.format(dateTime);
                        String time = dft.format(dateTime);
                        checkcycle.setDeleteFlag("X");
                        checkcycle.setChdate(date);
                        checkcycle.setChtime(time);
                        checkcycle.setChuser(userName);
                        checkcycleMapper.updateCheckcycle(checkcycle);
                    }else{
                        checkcycleMapper.delete(checkcycle);
                    }
                }
            }
        }
        return null;
    }
}