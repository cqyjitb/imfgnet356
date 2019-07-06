package yj.core.wipqcparamlines.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.mapper.CardhMapper;
import yj.core.marc.dto.Marc;
import yj.core.marc.mapper.MarcMapper;
import yj.core.wipcurlzk.dto.Curlzk;
import yj.core.wipcurlzk.mapper.CurlzkMapper;
import yj.core.wiplines.dto.Lines;
import yj.core.wiplines.mapper.LinesMapper;
import yj.core.wipqcparamlines.dto.QcparamLines;
import yj.core.wipqcparamlines.mapper.QcparamLinesMapper;
import yj.core.wipqcparamlines.service.IQcparamLinesService;
import yj.core.zwipq.mapper.ZwipqMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class QcparamLinesServiceImpl extends BaseServiceImpl<QcparamLines> implements IQcparamLinesService {
    @Autowired
    private QcparamLinesMapper qcparamLinesMapper;
    @Autowired
    private LinesMapper linesMapper;
    @Autowired
    private CurlzkMapper curlzkMapper;
    @Autowired
    private CardhMapper cardhMapper;
    @Autowired
    private MarcMapper marcMapper;
    @Autowired
    private ZwipqMapper zwipqMapper;

    @Override
    public QcparamLines selectForJj(Long line_id, String werks) {
        return qcparamLinesMapper.selectForJj(line_id,werks);
    }

    @Override
    public QcparamLines selectForYz(Long line_id, String werks) {
        return qcparamLinesMapper.selectForYz(line_id,werks);
    }

    @Override
    public List<QcparamLines> selectByScale(String deptId, Long lineId) {
        List<QcparamLines> qcparamLines = new ArrayList<QcparamLines>();
        QcparamLines qcparamLine = new QcparamLines();
        Marc marc = new Marc();
        Integer zsxnum;
        String name = linesMapper.selectByUnitCode(deptId);
        List<Lines> lines = linesMapper.selectLov(deptId,lineId);
        if(lines.size() > 0){
            for(int i=0;i<lines.size();i++){
                Curlzk curlzk = curlzkMapper.selectById(lines.get(i).getLineId(),null);
                if(curlzk != null){
                    Cardh cardh = cardhMapper.selectByBarcode(curlzk.getZpgdbar());
                    if(cardh != null){
                        marc = marcMapper.selectByMatnr(cardh.getMatnr());
                        zsxnum = zwipqMapper.selectByLineIdMatnr2(lines.get(i).getLineId(),cardh.getMatnr());
                        qcparamLine = qcparamLinesMapper.selectByLineId(lines.get(i).getLineId());
                        if(qcparamLine != null){
                            Double wipSqty = qcparamLine.getWipSqty();
                            qcparamLine.setDeptId(deptId);
                            qcparamLine.setName(name);
                            qcparamLine.setMatnr2(cardh.getMatnr());
                            qcparamLine.setMaktx(marc.getMaktx());
                            qcparamLine.setZsxnum((zsxnum));
                            if(wipSqty == null || wipSqty == 0){
                                qcparamLine.setScale("0%");
                            }else {
                                qcparamLine.setScale(Math.round(((zsxnum - wipSqty)/wipSqty)*100) + "%");
                            }
                            qcparamLine.setGmein("ST");
                            if(wipSqty != 0 || zsxnum != 0){
                                qcparamLines.add(qcparamLine);
                            }
                        }
                    }
                }
            }
        }
        return qcparamLines;
    }

    @Override
    public List<QcparamLines> selectFromPage(IRequest requestContext, QcparamLines dto, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return qcparamLinesMapper.selectQcparamLines(dto);
    }

    @Override
    public String setMessage(List<QcparamLines> dto) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                if(dto.get(i).getLineId() == null){
                    return "产线ID不能为空";
                }else if (dto.get(i).getFirstclassQty() == null){
                    return "第一类不合格件数不能为空";
                }else if (dto.get(i).getSecondclassQty() == null){
                    return "第二类不合格件数不能为空";
                }else if (dto.get(i).getThirdclassQty() == null){
                    return "第三类不合格件数不能为空";
                }else if (dto.get(i).getFourthclassQty() == null) {
                    return "第四类不合格件数不能为空";
                }else if (dto.get(i).getDftrateAlarm() == null || dto.get(i).getDftrateAlarm() == "") {
                    return "不合格率超限报警不能为空";
                }else if (dto.get(i).getUpperLimits() == null){
                    return "不合格率报警上限不能为空";
                }else if (dto.get(i).getDefaultCastdept() == null || dto.get(i).getDefaultCastdept() == ""){
                    return "缺省责任机加部门不能为空";
                }else if (dto.get(i).getDefaultCastdept() == null || dto.get(i).getDefaultCastdept() == ""){
                    return "缺省责任压铸部门不能为空";
                }else if ("1".equals(dto.get(i).getDftrateAlarm())){
                    if (dto.get(i).getLineqcResponsible() == null || dto.get(i).getLineqcResponsible() == ""){
                        return "不合格率超限报警，报警通知产线质检组长不能为空";
                    }else if (dto.get(i).getDeptqcResponsible() == null || dto.get(i).getDeptqcResponsible() == ""){
                        return "不合格率超限报警，报警通知部门质量部长不能为空";
                    }else if (dto.get(i).getShopqcResponsible() == null || dto.get(i).getShopqcResponsible() == ""){
                        return "不合格率超限报警，报警通知车间质量主任不能为空";
                    }else if (dto.get(i).getEngrqcResponsible() == null || dto.get(i).getEngrqcResponsible() == ""){
                        return "不合格率超限报警，报警通知质量工程师不能为空";
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String updateOrInsert(IRequest requestContext, List<QcparamLines> dto, Long userId) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                QcparamLines qcparamLines = dto.get(i);
                List<QcparamLines> list = qcparamLinesMapper.selectByLineId2(qcparamLines.getLineId());
                if(list.size() == 0){
                    qcparamLines.setCreatedBy(userId);
                    qcparamLines.setCreationDate(new Date());
                    qcparamLinesMapper.insertQcparamLines(qcparamLines);
                }else{
                    qcparamLines.setLastUpdatedBy(userId);
                    qcparamLines.setLastUpdateDate(new Date());
                    qcparamLinesMapper.updateQcparamLines(qcparamLines);
                }
            }
        }
        return null;
    }

    @Override
    public void deleteQcparamLines(List<QcparamLines> dto) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                qcparamLinesMapper.deleteQcparamLines(dto.get(i));
            }
        }
    }
}