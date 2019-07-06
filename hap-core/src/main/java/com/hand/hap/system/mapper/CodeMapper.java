package com.hand.hap.system.mapper;

import java.util.List;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.system.dto.Code;

/**
 * 快速编码Mapper.
 *
 * @author shengyang.zhou@hand-china.com
 * @date 2016/6/9.
 */
public interface CodeMapper extends Mapper<Code> {

    /**
     * 查询code列表
     * @param code
     * @return list
     */
    List<Code> selectCodes(Code code);

    /**
     * 根据codeName获取Code
     * @param codeName
     * @return
     */
    Code getByCodeName(String codeName);
}