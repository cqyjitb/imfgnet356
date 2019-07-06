package com.hand.hap.flexfield.service.impl;

import com.hand.hap.flexfield.dto.FlexModel;
import com.hand.hap.flexfield.dto.FlexModelColumn;
import com.hand.hap.flexfield.dto.FlexRuleSet;
import com.hand.hap.flexfield.mapper.FlexModelColumnMapper;
import com.hand.hap.flexfield.mapper.FlexModelMapper;
import com.hand.hap.flexfield.mapper.FlexRuleSetMapper;
import com.hand.hap.flexfield.service.IFlexModelService;
import com.hand.hap.flexfield.service.IFlexRuleSetService;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlexModelServiceImpl extends BaseServiceImpl<FlexModel> implements IFlexModelService {

    @Autowired
    private FlexModelColumnMapper modelColumnMapper;

    @Autowired
    private FlexModelMapper modelMapper;


    @Autowired
    private IFlexRuleSetService setService;

    @Autowired
    private FlexRuleSetMapper flexRuleSetMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFlexModel(List<FlexModel> models) {
        for (FlexModel flexModel : models) {
            int updateCount = modelMapper.deleteByPrimaryKey(flexModel);
            checkOvn(updateCount,flexModel);
            modelColumnMapper.delete(new FlexModelColumn(flexModel.getModelId()));
        }

        models.stream().forEach(v -> {
            FlexRuleSet flexModelSet = new FlexRuleSet();
            flexModelSet.setModelId(v.getModelId());
            setService.deleteRuleSet(flexRuleSetMapper.select(flexModelSet));
        });

    }
}