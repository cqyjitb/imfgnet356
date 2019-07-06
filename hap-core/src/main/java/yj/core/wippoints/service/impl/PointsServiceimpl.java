package yj.core.wippoints.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.bomoperations.mapper.OperationsMapper;
import yj.core.wipdot.mapper.DotMapper;
import yj.core.wippoints.dto.Points;
import yj.core.wippoints.mapper.PointsMapper;
import yj.core.wippoints.service.IPointsService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PointsServiceimpl extends BaseServiceImpl<Points> implements IPointsService{
    @Autowired
    private PointsMapper pointsMapper;
    @Autowired
    private DotMapper dotMapper;
    @Autowired
    private OperationsMapper operationsMapper;

    @Override
    public List<Points> selectFromPage(Points dto, IRequest requestContext, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return pointsMapper.selectFromPage(dto);
    }

    @Override
    public String updateOrInsert(IRequest requestCtx, List<Points> dto, String userId) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                Points points = dto.get(i);
                if (points.getPointId() != 0){
                    points.setLastUpdatedDate(df.format(new Date()));
                    points.setLastUpdatedBy(userId);
                    pointsMapper.updatePoints(points);
                }else{
                    points.setCreationDate(new Date());
                    points.setCreatedBy(userId);
                    pointsMapper.insertPoints(points);
                }
            }
        }
        return null;
    }

    @Override
    public String setMessageLines(List<Points> dto) {
        if(dto.size() > 0){
            for(int i= 0;i< dto.size();i++){
                Points points = dto.get(i);
                if(points.getPointNum() == null){
                    return "工序序号不能为空";
                }else if(points.getPointCode()== null || points.getPointCode() == ""){
                    return "工序编码不能为空";
                }else if(points.getDescriptions() == null || points.getDescriptions() == ""){
                    return "工序描述不能为空";
                }
            }
        }
        return null;
    }

    @Override
    public String deleteDftdtl(List<Points> dto) {
        if(dto.size() > 0){
            for (int i=0;i<dto.size();i++){
                Points points = dto.get(i);
                Integer pointId = points.getPointId();
                int num = dotMapper.selectPoints(pointId);
                int num2 = operationsMapper.selectPoints(pointId);
                if(num != 0 || num2 != 0){
                    return "工序Id已使用，不允许删除！";
                }else{
                    pointsMapper.deletePoints(points);
                }
            }
        }
        return null;
    }
}
