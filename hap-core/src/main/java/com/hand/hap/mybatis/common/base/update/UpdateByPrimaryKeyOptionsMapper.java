package com.hand.hap.mybatis.common.base.update;

import com.hand.hap.core.BaseConstants;
import com.hand.hap.mybatis.common.Criteria;
import com.hand.hap.mybatis.provider.base.BaseUpdateProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * Created by jialong.zuo@hand-china.com on 2017/5/22.
 */
public interface UpdateByPrimaryKeyOptionsMapper<T> {

    /**
     * 根据主键更新选定字段的值
     *
     * @param record
     * @return
     */
    @UpdateProvider(type = BaseUpdateProvider.class, method = "dynamicSQL")
    int updateByPrimaryKeyOptions(@Param(BaseConstants.OPTIONS_DTO)T record, @Param(BaseConstants.OPTIONS_CRITERIA)Criteria criteria);
}
