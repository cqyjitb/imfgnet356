package com.hand.hap.intergration.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.intergration.dto.HapInterfaceHeader;
import com.hand.hap.intergration.dto.HapInterfaceLine;
import com.hand.hap.intergration.mapper.HapInterfaceLineMapper;
import com.hand.hap.intergration.service.IHapInterfaceLineService;
import com.hand.hap.cache.impl.ApiConfigCache;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by user on 2016/7/26.
 *           xiangyu.qi on 2016/11/11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HapInterfaceLineServiceImpl extends BaseServiceImpl<HapInterfaceLine> implements IHapInterfaceLineService {

    private final Logger logger = LoggerFactory.getLogger(HapInterfaceLineServiceImpl.class);
    @Autowired
    private HapInterfaceLineMapper hmsLineMapper;

    @Autowired
    private ApiConfigCache apiCache;

    @Override
    public List<HapInterfaceLine> getLineAndLineTl(IRequest request, HapInterfaceLine lineAndLineTlDTO) {
        return hmsLineMapper.getLineAndLineTl(lineAndLineTlDTO);
    }


    @Override
    public List<HapInterfaceLine> getLinesByHeaderId(IRequest request,HapInterfaceLine lineAndLineTlDTO,int page,int pagesize) {

        PageHelper.startPage(page, pagesize);
        List<HapInterfaceLine> list = hmsLineMapper.getLinesByHeaderId(lineAndLineTlDTO);
        return list;
    }

    @Override
    public int insertLine(IRequest request, HapInterfaceLine hmsInterfaceLine) {

        int result = hmsLineMapper.insertSelective(hmsInterfaceLine);

        if (result > 0) {
            apiCache.reload(hmsInterfaceLine.getLineId());
        }

        return result;
    }

    @Override
    public int updateLine(IRequest request, HapInterfaceLine hmsInterfaceLine) {

        int result = hmsLineMapper.updateByPrimaryKeySelective(hmsInterfaceLine);
        checkOvn(result,hmsInterfaceLine);
        if (result > 0 ) {
            apiCache.reload();
        }

        return result;
    }

    @Override
    public int batchDelete(List<HapInterfaceLine> list){
        int result = 0;
        for(HapInterfaceLine line : list) {
            result = hmsLineMapper.deleteByPrimaryKey(line);
            checkOvn(result,line);
        }
        if(result >0) {
            apiCache.reload();
        }
        return result;
    }

    @Override
    public int batchDeleteByHeaders(IRequest request, List<HapInterfaceHeader> lists) {

        int result = 0;

        for(HapInterfaceHeader index : lists){
            HapInterfaceLine line = new HapInterfaceLine();
            line.setHeaderId(index.getHeaderId());

            hmsLineMapper.deleteTlByHeaderId(line);
            result = hmsLineMapper.deleteByHeaderId(line);
            checkOvn(result,line);
        }
        if(result >0) {
            apiCache.reload();
        }
        return result;
    }


}
