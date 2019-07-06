package yj.core.imfpmcheckrequest.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.imfpmcheckdoc.dto.Checkdoc;
import yj.core.imfpmcheckdoc.mapper.CheckdocMapper;
import yj.core.imfpmcheckplan.dto.Checkplan;
import yj.core.imfpmcheckplan.mapper.CheckplanMapper;
import yj.core.imfpmcheckrequest.dto.Checkrequest;
import yj.core.imfpmcheckrequest.mapper.CheckrequestMapper;
import yj.core.imfpmcheckrequest.service.ICheckrequestService;
import yj.core.pmimptt.dto.Imptt;
import yj.core.pmimptt.mapper.ImpttMapper;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CheckrequestServiceImpl extends BaseServiceImpl<Checkrequest> implements ICheckrequestService {

    @Autowired
    private CheckrequestMapper checkrequestMapper;
    @Autowired
    private CheckdocMapper checkdocMapper;
    @Autowired
    private CheckplanMapper checkplanMapper;
    @Autowired
    private ImpttMapper impttMapper;

    @Override
    public List<Checkrequest> selectCheckrequest(IRequest requestContext, Integer id, String checkrequest, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return checkrequestMapper.selectCheckrequest(id,checkrequest);
    }

    @Override
    public int selectMaxCheckrequest() {
        return checkrequestMapper.selectMaxCheckrequest();
    }

    @Override
    public String updateCheckrequest(List<Checkrequest> dto, String userName) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                List<Checkrequest> list = checkrequestMapper.selectCheckrequest(null,dto.get(i).getCheckreq());
                if(list.size() > 0){
                    return "点检要求描述已存在，不能保存！";
                }else{
                    Checkrequest checkrequest = dto.get(i);
                    List<Checkrequest> list1 = checkrequestMapper.selectCheckrequest(checkrequest.getId(),null);
                    DateFormat df = DateFormat.getDateInstance();
                    DateFormat dft = DateFormat.getTimeInstance();
                    Date dateTime = new Date();
                    String date = df.format(dateTime);
                    String time = dft.format(dateTime);
                    if(list1.size() == 1){
                        checkrequest.setChdate(date);
                        checkrequest.setChtime(time);
                        checkrequest.setChuser(userName);
                        checkrequestMapper.updateCheckrequest(checkrequest);
                    }else{
                        checkrequest.setCrdate(date);
                        checkrequest.setCrtime(time);
                        checkrequest.setCruser(userName);
                        checkrequestMapper.insertCheckrequest(checkrequest);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String deleteCheckrequest(List<Checkrequest> dto, String userName) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                Checkrequest checkrequest = dto.get(i);
                List<Checkplan> checkplan = checkplanMapper.selectCheckcycleAndCheckreq(null,checkrequest.getCheckreq());
                List<Imptt> imptt = impttMapper.selectCheckcycleAndCheckreq(null,checkrequest.getCheckreq());
                if(checkplan.size()>0 || imptt.size()>0){
                    return "该点检要求已被使用，不允许删除";
                }else{
                    List<Checkdoc> checkdoc = checkdocMapper.selectCheckcycleAndCheckreq(null,checkrequest.getCheckreq());
                    if(checkdoc.size() > 0){
                        DateFormat df = DateFormat.getDateInstance();
                        DateFormat dft = DateFormat.getTimeInstance();
                        Date dateTime = new Date();
                        String date = df.format(dateTime);
                        String time = dft.format(dateTime);
                        checkrequest.setDeleteFlag("X");
                        checkrequest.setChdate(date);
                        checkrequest.setChtime(time);
                        checkrequest.setChuser(userName);
                        checkrequestMapper.updateCheckrequest(checkrequest);
                    }else{
                        checkrequestMapper.delete(checkrequest);
                    }
                }
            }
        }
        return null;
    }
}