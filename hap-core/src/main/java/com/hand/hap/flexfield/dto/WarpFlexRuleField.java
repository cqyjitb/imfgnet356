package com.hand.hap.flexfield.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hand.hap.cache.Cache;
import com.hand.hap.cache.impl.LovCache;
import com.hand.hap.cache.impl.SysCodeCache;
import com.hand.hap.core.components.ApplicationContextHelper;
import com.hand.hap.core.impl.RequestHelper;
import com.hand.hap.system.dto.Code;
import com.hand.hap.system.dto.Lov;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/8.
 */
public class WarpFlexRuleField {

    private List<FlexRuleField> fields;

    private int totoal;

    private Long rowNumber;


    private static ObjectMapper mapper = new ObjectMapper();

    public List<FlexRuleField> getFields() {
        return fields;
    }

    public void setFields(List<FlexRuleField> fields) {
        this.fields = fields;
    }

    public int getTotoal() {
        return totoal;
    }

    public void setTotoal(int totoal) {
        this.totoal = totoal;
    }

    public Long getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Long rowNumber) {
        this.rowNumber = rowNumber;
    }

    public WarpFlexRuleField warpField(List<FlexRuleField> flexRuleFields) {
        setTotoal(flexRuleFields.size());
        setFields(flexRuleFields);
        setRowNumber(flexRuleFields.get(0).getFieldColumnNumber());
        return this;
    }


}
