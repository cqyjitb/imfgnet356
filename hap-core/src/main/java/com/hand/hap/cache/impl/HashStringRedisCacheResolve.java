package com.hand.hap.cache.impl;

import com.hand.hap.cache.Cache;
import com.hand.hap.cache.CacheManager;
import com.hand.hap.cache.CacheResolve;
import com.hand.hap.core.components.ApplicationContextHelper;
import com.hand.hap.mybatis.common.query.JoinCache;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Author jialong.zuo@hand-china.com on 2017/6/1.
 */
public class HashStringRedisCacheResolve extends CacheResolve {
    CacheManager cacheManager;
    @Override
    public Object resolve(Object cacheEntity, Object resultMap, String lang) throws NoSuchFieldException, IllegalAccessException {
       if(cacheManager == null) {
            cacheManager = ApplicationContextHelper.getApplicationContext().getBean(CacheManager.class);
       }
        Object joinKey = getJoinKey(cacheEntity,resultMap);
        if(joinKey == null){
            return joinKey;
        }
        JoinCache joinCache = (JoinCache) cacheEntity;
        Cache cache = cacheManager.getCache(joinCache.cacheName());
        Object result = cache.getValue(joinKey.toString());
        if(result == null){
            throw new RuntimeException("查询关联属性"+joinCache.joinColumn()+"失败！   关联字段: "+joinCache.joinKey()+"  对应值: "+joinKey);
        }
        Field field = result.getClass().getDeclaredField(joinCache.joinColumn());
        field.setAccessible(true);
        return field.get(result);
    }

}
