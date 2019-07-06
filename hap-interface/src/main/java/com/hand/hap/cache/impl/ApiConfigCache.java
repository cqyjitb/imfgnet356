package com.hand.hap.cache.impl;

import com.hand.hap.intergration.dto.HapInterfaceHeader;
import com.hand.hap.intergration.mapper.HapInterfaceHeaderMapper;
import com.hand.hap.intergration.mapper.HapInterfaceLineMapper;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by user on 2016/8/1.
 */
public class ApiConfigCache<T>  extends HashStringRedisCache<HapInterfaceHeader> {

    private static final Logger logger = LoggerFactory.getLogger(ApiConfigCache.class);
    private String apiSql = HapInterfaceHeaderMapper.class.getName() + ".getAllHeaderAndLine";
    private String lineSql = HapInterfaceLineMapper.class.getName() + ".getHeaderLineByLineId";

    {
        setLoadOnStartUp(true);
        setType(HapInterfaceHeader.class);
    }

    @Override
    public HapInterfaceHeader getValue(String key) {
        return super.getValue(key);
    }

    @Override
    public void setValue(String key, HapInterfaceHeader headerAndLineDTO) {
        super.setValue(key, headerAndLineDTO);
    }


    public void initLoad() {

        try (SqlSession sqlSession = getSqlSessionFactory().openSession()) {
            sqlSession.select(apiSql, (resultContext) -> {
                HapInterfaceHeader headerAndLineDTO = (HapInterfaceHeader) resultContext.getResultObject();
                logger.info("cache result:{}", headerAndLineDTO.getInterfaceCode() + headerAndLineDTO.getLineCode());
                setValue(headerAndLineDTO.getInterfaceCode() + headerAndLineDTO.getLineCode(), headerAndLineDTO);
            });

        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("init api cache error:", e);
            }
        }

    }


    public void reload(Object lineId) {
        logger.info("test  lineId:{}", lineId);
        try (SqlSession sqlSession = getSqlSessionFactory().openSession()) {
            HapInterfaceHeader headerAndLineDTO = sqlSession.selectOne(lineSql, lineId);
            if(headerAndLineDTO != null )
                setValue(headerAndLineDTO.getInterfaceCode() +HapInterfaceHeader.CACHE_SEPARATOR+ headerAndLineDTO.getLineCode(), headerAndLineDTO);

        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("reload api cache error:", e);
            }
        }


    }


}
