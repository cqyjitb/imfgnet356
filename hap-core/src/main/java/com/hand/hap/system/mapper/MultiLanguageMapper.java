/*
 * #{copyright}#
 */

package com.hand.hap.system.mapper;

import java.util.List;
import java.util.Map;

import com.hand.hap.system.dto.MultiLanguageField;

/**
 * 
 * @author njq.niu@hand-china.com
 *
 *         2016年3月30日
 */
public interface MultiLanguageMapper {

    List<MultiLanguageField> select(Map<String, String> map);
}