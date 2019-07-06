package yj.core.wiplines.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wiplines.dto.Lines;
import yj.core.wiplines.mapper.LinesMapper;
import yj.core.wiplines.service.ILinesService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LinesServiceImpl extends BaseServiceImpl<Lines> implements ILinesService{
    @Autowired
    private LinesMapper linesMapper;
    @Override
    public Lines selectById(Long line_id) {
        return linesMapper.selectById(line_id);
    }

    @Override
    public Lines selectByIdForBlmpcl(Long line_id) {
        return linesMapper.selectById(line_id);
    }

    @Override
    public List<Lines> selectFromPage(Lines dto, IRequest requestContext, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return linesMapper.selectFromPage(dto);
    }

    @Override
    public String selectDescription(Long plineId) {
        return linesMapper.selectDescription(plineId);
    }

    @Override
    public Lines selectUnitCode(String deptId) {
        return linesMapper.selectUnit(deptId);
    }

    @Override
    public String setMessageLines(List<Lines> dto) {
        if(dto.size() > 0){
            for(int i= 0;i< dto.size();i++){
                Lines lines = dto.get(i);
                if(lines.getDeptId() == null || lines.getDeptId() == ""){
                    return "生产车间不能为空";
                }else if(lines.getLineId() == null){
                    return "产线编码不能为空";
                }else if(lines.getDescriptions() == null || lines.getDescriptions() == ""){
                    return "产线描述不能为空";
                }else if(lines.getArbpl() == null || lines.getArbpl() == ""){
                    return "工作中心不能为空";
                }else if(lines.getEnableFlag() == null || lines.getEnableFlag() == ""){
                    return "启动状态不能为空";
                }else if(lines.getOnlinetype() == null || lines.getOnlinetype() == ""){
                    return "上线方式不能为空";
                }else if(lines.getPkgtype()== null || lines.getPkgtype() == ""){
                    return "装箱方式不能为空";
                }else if(lines.getPointNum() == null){
                    return "工序数量不能为空";
                }else if(lines.getStartDate() == null){
                    return "生效日期不能为空";
                }else if(lines.getHeaderphone() != null && lines.getHeaderphone().length() > 11){
                    return "负责人电话长度不能大于11";
                }
                if(lines.getOeerate() != null){
                    if(lines.getOeerate() <=0 || lines.getOeerate()>=1){
                        return "设计OEE必须是大于0且小于1的小数";
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String updateOrInsert(IRequest requestCtx, List<Lines> dto, String userId) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                Lines lines = dto.get(i);
                if(lines.getLineheader() == null || ("".equals(lines.getLineheader()))) {

                }else{
                    String chinese = lines.getLineheader();
                    StringBuffer pybf = new StringBuffer();
                    char[] arr = chinese.toCharArray();
                    HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
                    defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
                    defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
                    for (int j = 0; j < arr.length; j++) {
                        if (arr[j] > 128) {
                            try {
                                pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[j], defaultFormat)[0]);
                            } catch (BadHanyuPinyinOutputFormatCombination e) {
                                e.printStackTrace();
                            }
                        } else {
                            pybf.append(arr[j]);
                        }
                    }
                    String english = pybf.toString().substring(0,1).toUpperCase() + pybf.toString().substring(1);
                    lines.setLineheaderEn(english);
                }
                int num = linesMapper.isExit(lines.getLineId());
                if (num == 1){
                    if(lines.getCreationDate() == null){
                        lines.setCreationDate(new Date());
                        lines.setCreatedBy(Long.valueOf(userId));
                    }
                    lines.setLastUpdatedDate(new Date());
                    lines.setLastUpdatedBy(Long.valueOf(userId));
                    linesMapper.updateLines(lines);
                }else{
                    lines.setCreationDate(new Date());
                    lines.setCreatedBy(Long.valueOf(userId));
                    linesMapper.insertLines(lines);
                }
            }
        }
        return null;
    }

    @Override
    public List<Lines> selectByPlineId(String line_id) {
        return linesMapper.selectByPlineId(line_id);
    }
}