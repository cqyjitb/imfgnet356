package yj.core.bomroutings.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.bomoperations.mapper.OperationsMapper;
import yj.core.bomroutings.dto.Routings;
import yj.core.bomroutings.mapper.RoutingsMapper;
import yj.core.bomroutings.service.IRoutingsService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RoutingsServiceImpl extends BaseServiceImpl<Routings> implements IRoutingsService{
    @Autowired
    private RoutingsMapper routingsMapper;
    @Autowired
    private OperationsMapper operationsMapper;

    @Override
    public List<Routings> selectFromPage(Routings dto, IRequest requestContext, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Routings> routings = routingsMapper.selectFromPage(dto);
        return routings;
    }

    @Override
    public String updateOrInsert(IRequest requestCtx, List<Routings> dto, String userId){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(dto.size() > 0){
            for(int i=0;i<dto.size(); i++){
                Routings routings = dto.get(i);
                String startDate = routings.getStartDate2().substring(0,10).replace("/","-");
                routings.setStartDate(startDate);
                if(routings.getEndDate2() == null || routings.getEndDate2() == ""){

                }else{
                    String endDate = routings.getEndDate2().substring(0,10).replace("/","-");
                    routings.setEndDate(endDate);
                }
                if(routings.getRoutingId() == null || routings.getRoutingId() == 0){
                    routings.setCreationDate2(df.format(new Date()));
                    routings.setCreatedBy2(userId);
                    routingsMapper.insertRoutings(routings);
                }else{
                    routings.setCreatedBy2(routings.getCreatedBy2());
                    routings.setCreationDate2(routings.getCreationDate2());
                    routings.setLastUpdatedDate(df.format(new Date()));
                    routings.setLastUpdatedBy(userId);
                    routingsMapper.updateRoutings(routings);
                }
            }
        }

        return null;
    }

    @Override
    public void deleteRoutings(List<Routings> dto) {
        if(dto.size() > 0){
            for (int i=0;i<dto.size();i++){
                Routings routings = dto.get(i);
                routingsMapper.deleteRoutings(routings.getRoutingId());
                operationsMapper.deleteOperations(null,routings.getRoutingId());
            }
        }
    }
}
