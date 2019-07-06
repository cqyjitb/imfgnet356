package yj.core.wipcurlzk.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wipcurlzk.dto.Curlzk;
import yj.core.wipcurlzk.mapper.CurlzkMapper;
import yj.core.wipcurlzk.service.ICurlzkService;

import java.util.List;

@Service
@Transactional
public class CurlzkServiceImpl extends BaseServiceImpl<Curlzk> implements ICurlzkService{
    @Autowired
    private CurlzkMapper curlzkMapper;
    @Override
    public Curlzk selectById(Long line_id, String classgrp) {
        return curlzkMapper.selectById(line_id,classgrp);
    }

    @Override
    public int updateZxhbar(Curlzk dto) {
        return curlzkMapper.updateZxhbar(dto);
    }

    @Override
    public List<Curlzk> selectAllLinesforZxhbar(Long line_id) {
        return curlzkMapper.selectAllLinesforZxhbar(line_id);
    }

    @Override
    public Curlzk selectById2(Long line_id) {
        return curlzkMapper.selectById2(line_id);
    }

    @Override
    public List<Curlzk> selectByZpgdbar(String zpgdbar) {
        return curlzkMapper.selectByZpgdbar(zpgdbar);
    }

    @Override
    public List<Curlzk> selectAllLinesForKanbByErdat(String erdat) {
        return curlzkMapper.selectAllLinesForKanbByErdat(erdat);
    }
}