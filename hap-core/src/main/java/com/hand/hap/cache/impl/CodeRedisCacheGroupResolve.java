package com.hand.hap.cache.impl;

import com.hand.hap.cache.CacheResolve;
import com.hand.hap.core.components.ApplicationContextHelper;
import com.hand.hap.mybatis.common.query.JoinCode;
import com.hand.hap.system.dto.Code;
import com.hand.hap.system.dto.CodeValue;

/**
 * @author jialong.zuo@hand-china.com
 * @date 2017/6/5.
 */
public class CodeRedisCacheGroupResolve extends CacheResolve {

    SysCodeCache codeCache;

    @Override
    public Object resolve(Object cacheEntity, Object resultMap, String lang) throws NoSuchFieldException, IllegalAccessException {
        if (codeCache == null) {
            codeCache = (SysCodeCache) ApplicationContextHelper.getApplicationContext().getBean("codeCache");
        }
        Object joinKey = getJoinKey(cacheEntity, resultMap);
        if (joinKey == null) {
            return joinKey;
        }
        JoinCode joinCache = (JoinCode) cacheEntity;
        Code result = codeCache.getValue(lang, joinCache.code());
        if (result == null) {
            throw new RuntimeException("JoinCode failed,result is null!");
        }
        for (CodeValue codeValue : result.getCodeValues()) {
            if (codeValue.getValue().equals(joinKey.toString())) {
                return codeValue.getMeaning();
            }
        }
        return null;
    }


}
