package com.hand.hap.cache.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hand.hap.system.mapper.LovItemMapper;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hand.hap.system.dto.Lov;
import com.hand.hap.system.dto.LovItem;
import com.hand.hap.system.mapper.LovMapper;

/**
 * lov 缓存.
 *
 * @author njq.niu@hand-china.com
 * @date 2016/2/16
 */
public class LovCache extends HashStringRedisCache<Lov> {
    
    private Logger logger = LoggerFactory.getLogger(LovCache.class);
    
    private String lovSql = LovMapper.class.getName() + ".selectAll";
    private String lovItemSql = LovItemMapper.class.getName() + ".selectAll";

    {
        setLoadOnStartUp(true);
        setType(Lov.class);
    }

    @Override
    public Lov getValue(String key) {
        return super.getValue(key);
    }

   
    @Override
    public void setValue(String key, Lov lov) {
        super.setValue(key, lov);
    }

    public void reload(Long lovId) {
        try (SqlSession sqlSession = getSqlSessionFactory().openSession()) {
            Lov lov = sqlSession.selectOne(LovMapper.class.getName() + ".selectByPrimaryKey", lovId);
            List<LovItem> lovItems = sqlSession.selectList(LovItemMapper.class.getName() + ".selectByLovId", lovId);
            lov.setLovItems(lovItems);
            setValue(lov.getCode(), lov);            
        }
    }

    @Override
    protected void initLoad() {
        Map<Long, Lov> tempMap = new HashMap<>(16);
        try (SqlSession sqlSession = getSqlSessionFactory().openSession()) {
            sqlSession.select(lovSql, (resultContext) -> {
                Lov lov = (Lov) resultContext.getResultObject();
                tempMap.put(lov.getLovId(), lov);
            });

            sqlSession.select(lovItemSql, (resultContext) -> {
                LovItem item = (LovItem) resultContext.getResultObject();
                Lov lov = tempMap.get(item.getLovId());
                if (lov != null) {
                    List<LovItem> lovItems = lov.getLovItems();
                    if (lovItems == null) {
                        lovItems = new ArrayList<>();
                        lov.setLovItems(lovItems);
                    }
                    lovItems.add(item);
                }
            });
            tempMap.forEach((k, v) -> {
                setValue(v.getCode(), v);
            });
            tempMap.clear();
        } catch (Throwable e) {
            if (logger.isErrorEnabled()) {
                logger.error("init lov cache error:", e);
            }
        }
    }
}
