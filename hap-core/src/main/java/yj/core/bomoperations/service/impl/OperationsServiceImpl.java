package yj.core.bomoperations.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.bomoperations.dto.Operations;
import yj.core.bomoperations.mapper.OperationsMapper;
import yj.core.bomoperations.service.IOperationsService;
import yj.core.bomroutings.mapper.RoutingsMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OperationsServiceImpl extends BaseServiceImpl<Operations> implements IOperationsService{
    @Autowired
    private OperationsMapper operationsMapper;
    @Autowired
    private RoutingsMapper routingsMapper;

    @Override
    public List<Operations> selectFromPage(Integer routingId, IRequest requestContext, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return operationsMapper.selectFromPage(routingId);
    }

    @Override
    public String updateOrInsert(IRequest requestCtx, List<Operations> dto, String userId) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                Operations operations = dto.get(i);
                if(operations.getRoutingId() == 0){
                    operations.setRoutingId(routingsMapper.selectMaxRoutings());
                }
                if(operations.getOperationId() != 0){
                    operations.setLastUpdatedDate(df.format(new Date()));
                    operations.setLastUpdatedBy(userId);
                    operationsMapper.updateOperations(operations);
                }else{
                    operations.setCreationDate(df.format(new Date()));
                    operations.setCreatedBy(userId);
                    operationsMapper.insertOperations(operations);
                }
            }
        }
        return null;
    }

    @Override
    public String setMessageOperations(List<Operations> dto) {
        if(dto.size() > 0){
            for(int i= 0;i< dto.size();i++){
                Operations operations = dto.get(i);
                if(operations.getLineId() == null){
                    return "产线ID不能为空";
                }else if(operations.getPointId() == null){
                    return "工序编码不能为空";
                }else if(operations.getOperationNum()== null){
                    return "工艺序号不能为空";
                }else if(operations.getOperationCode() == null || operations.getOperationCode() == ""){
                    return "工艺编码不能为空";
                }else if(operations.getDescriptions() == null || operations.getDescriptions() == ""){
                    return "工艺描述不能为空";
                }else if(operations.getStartDate() == null ){
                    return "生效日期不能为空";
                }
            }
        }
        return null;
    }

    @Override
    public void deleteOperations(List<Operations> dto) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                Operations operations = dto.get(i);
                operationsMapper.deleteOperations(operations.getOperationId(),null);
            }
        }

    }
}
