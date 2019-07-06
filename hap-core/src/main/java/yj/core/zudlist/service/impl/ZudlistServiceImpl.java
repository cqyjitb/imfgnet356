package yj.core.zudlist.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.zudlist.dto.Zudlist;
import yj.core.zudlist.mapper.ZudlistMapper;
import yj.core.zudlist.service.IZudlistService;

import java.util.List;

@Service
@Transactional
public class ZudlistServiceImpl extends BaseServiceImpl<Zudlist> implements IZudlistService {
    @Autowired
    private ZudlistMapper zudlistMapper;
    @Override
    public int insertItem(List<Zudlist> list) {
        int sum = 0;
        for (int i=0;i<list.size();i++){
            sum = sum + zudlistMapper.insertItem(list.get(i));
        }
        return sum;
    }

    @Override
    public List<Zudlist> selectZudlist(IRequest requestContext, Zudlist dto) {
        List<Zudlist> list = zudlistMapper.selectZudlist(dto);
        List<Zudlist> list2 = zudlistMapper.selectZudlistTypeBlpcl(dto);

            for (int i=0;i<list2.size();i++){//类型为1 线边库不良品记录
                list.add(list2.get(i));
            }
        if(list.size() > 0){
            for(int i=0;i<list.size();i++){
                if("".equals(list.get(i).getReviewc()) || list.get(i).getReviewc() == null){
                    list.get(i).setReviewc("F");
                }
            }
        }
        return list;
    }


    @Override
    public int updateItem(Zudlist zudlist) {
        return zudlistMapper.updateItem(zudlist);
    }

    @Override
    public List<Zudlist> selectByZudnumForUnprocess(String zudnum) {
        return zudlistMapper.selectByZudnumForUnprocess(zudnum);
    }

    @Override
    public Zudlist selectByIdAndItem(String zudnum, String item) {
        return zudlistMapper.selectByIdAndItem(zudnum,item);
    }

    public ZudlistMapper getZudlistMapper() {
        return zudlistMapper;
    }

    public void setZudlistMapper(ZudlistMapper zudlistMapper) {
        this.zudlistMapper = zudlistMapper;
    }

    @Override
    public List<Zudlist> selectForPassrateReportgf(String lineId, String matnr, String datestart, String dateend) {
        return zudlistMapper.selectForPassrateReportgf(lineId,matnr,datestart,dateend);
    }

    @Override
    public List<Zudlist> selectForPassrateReportlf(String lineId, String matnr, String datestart, String dateend) {
        return zudlistMapper.selectForPassrateReportlf(lineId,matnr,datestart,dateend);
    }

    @Override
    public List<Zudlist> selectForBaltuChart(String lineId, String matnr, String datastart, String dateend) {
        return zudlistMapper.selectForBaltuChart(lineId,matnr,datastart,dateend);
    }
}