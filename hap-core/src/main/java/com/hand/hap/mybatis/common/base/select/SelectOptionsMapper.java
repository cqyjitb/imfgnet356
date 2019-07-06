package com.hand.hap.mybatis.common.base.select;

import com.hand.hap.core.BaseConstants;
import com.hand.hap.mybatis.common.Criteria;
import com.hand.hap.mybatis.provider.base.BaseSelectProvider;
import com.hand.hap.system.dto.BaseDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author njq.niu@hand-china.com
 */
public interface SelectOptionsMapper<T> {

    /**
     * 按照主键有条件查询.
     *
     * @param record
     * @return dto
     */
    @SelectProvider(type = BaseSelectProvider.class, method = "selectOptionsByPrimaryKey")
    T selectOptionsByPrimaryKey(T record);


    /**
     * 有条件查询.
     *
     * @param record
     * @param criteria
     * @return list
     */
    @SelectProvider(type = BaseSelectProvider.class, method = "selectOptions")
    List<T> selectOptions(@Param(BaseConstants.OPTIONS_DTO) T record, @Param(BaseConstants.OPTIONS_CRITERIA) Criteria criteria);
}
