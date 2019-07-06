package com.hand.hap.security.permission.service.impl;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.github.pagehelper.SqlUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.hand.hap.cache.Cache;
import com.hand.hap.cache.CacheManager;
import com.hand.hap.cache.impl.DataPermissionRuleCache;
import com.hand.hap.core.BaseConstants;
import com.hand.hap.security.permission.dto.*;
import com.hand.hap.security.permission.mapper.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author jialong.zuo@hand-china.com on 2017/9/11.
 */
@Component
public class DataPermissionCacheContainer {

    private static Logger logger = LoggerFactory.getLogger(DataPermissionCacheContainer.class);

    public final String CACHE_DELETE = "delete";
    public final String CACHE_UPDATE = "update";

    @Autowired
    DataPermissionTableMapper dataPermissionTableMapper;

    @Autowired
    DataPermissionTableRuleMapper dataPermissionTableRuleMapper;

    @Autowired
    DataPermissionRuleMapper dataPermissionRuleMapper;

    @Autowired
    DataPermissionRuleDetailMapper dataPermissionRuleDetailMapper;

    @Autowired
    DataPermissionRuleAssignMapper dataPermissionRuleAssignMapper;


    @Autowired
    private CacheManager cacheManager;

    private ConcurrentHashMap<String, List<DataPermissionTableRule>> maskTableRuleMaps = new ConcurrentHashMap();

    private LoadingCache<String, Map> maskRuleMaps;

    public void initContainer() {
        List<DataPermissionTable> dataMaskTables = dataPermissionTableMapper.selectAll();
        dataMaskTables.forEach(v -> {
            DataPermissionTableRule dataMaskTableRule = new DataPermissionTableRule();
            dataMaskTableRule.setTableId(v.getTableId());
            maskTableRuleMaps.put(v.getTableName().toUpperCase(),
                    Optional.ofNullable(dataPermissionTableRuleMapper.select(dataMaskTableRule)).orElseGet(() -> new ArrayList()));

        });

        maskRuleMaps = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<String, Map>() {
                            public Map load(String key) {
                                return loadDataMaskRule(key);
                            }
                        });
    }

    private Map loadDataMaskRule(String ruleCode) {
        Cache ruleCache = cacheManager.getCache(BaseConstants.CACHE_DATA_PERMISSION_RULE);

        return (Map) Optional.ofNullable(ruleCache.getValue(ruleCode)).orElseGet(() -> new HashMap<>());
    }

    public void removeMaskTableRuleMap(String tableName) {
        maskTableRuleMaps.remove(tableName.toUpperCase());
    }

    public void updateMaskTableRuleMap(DataPermissionTableRule dataMaskTableRule, String action) {
        if (StringUtils.equals(action, CACHE_UPDATE)) {
            List<DataPermissionTableRule> list = Optional.ofNullable(maskTableRuleMaps.
                    get(dataMaskTableRule.getTableName().toUpperCase())).orElseGet(() -> {
                List list1 = new ArrayList<>();
                maskTableRuleMaps.put(dataMaskTableRule.getTableName().toUpperCase(), list1);
                return list1;
            });
            boolean key = true;
            Iterator<DataPermissionTableRule> iterator = list.iterator();
            while (key) {
                if (iterator.hasNext() && iterator.next().getTableRuleId().equals(dataMaskTableRule.getTableRuleId())) {
                    iterator.remove();
                    list.add(dataMaskTableRule);
                    key = false;
                    continue;
                }
                if ((!iterator.hasNext()) && key == true) {
                    list.add(dataMaskTableRule);
                    key = false;
                }
            }

        } else if (StringUtils.equals(action, CACHE_DELETE)) {
            List<DataPermissionTableRule> list = maskTableRuleMaps.get(dataMaskTableRule.getTableName().toUpperCase());
            Iterator<DataPermissionTableRule> iterator = list.iterator();
            boolean key = true;
            while (key) {
                if (!iterator.hasNext()) {
                    key = false;
                    continue;
                }
                if (iterator.hasNext() && iterator.next().getTableRuleId().equals(dataMaskTableRule.getTableRuleId())) {
                    iterator.remove();
                    key = false;
                }
            }
        }
    }

    public void removeMaskRuleMap(Long ruleId) {
        Cache ruleCache = cacheManager.getCache(BaseConstants.CACHE_DATA_PERMISSION_RULE);
        ruleCache.remove(ruleId.toString());
        maskRuleMaps.invalidate(ruleId.toString());
    }

    public void updateMaskRuleMap(Long ruleId) {
        DataPermissionRule man = new DataPermissionRule();
        man.setRuleId(ruleId);
        DataPermissionRule maskRuleManage = dataPermissionRuleMapper.select(man).get(0);

        Map<String, Map> ruleMaps = new HashMap<>();
        Map<String, DataPermissionRule> tempDataMaskRuleManage = new HashMap<>();
        Map<String, DataPermissionRuleDetail> tempDataMaskRuleManageDetail = new HashMap<>();
        Map<String, DataPermissionRuleAssign> tempDataMaskRuleAssign = new HashMap<>();

        tempDataMaskRuleManage.put(maskRuleManage.getRuleId().toString(), maskRuleManage);
        DataPermissionRuleDetail detail = new DataPermissionRuleDetail();
        detail.setRuleId(maskRuleManage.getRuleId());
        List<DataPermissionRuleDetail> detailTemp = dataPermissionRuleDetailMapper.select(detail);
        detailTemp.forEach(v -> {
            tempDataMaskRuleManageDetail.put(v.getDetailId().toString(), v);
            DataPermissionRuleAssign assign = new DataPermissionRuleAssign();
            assign.setDetailId(v.getDetailId());
            dataPermissionRuleAssignMapper.select(assign).forEach(u -> {
                tempDataMaskRuleAssign.put(u.getAssignId().toString(), u);
            });
        });

        Cache ruleCache = cacheManager.getCache(BaseConstants.CACHE_DATA_PERMISSION_RULE);
        DataPermissionRuleCache cache = (DataPermissionRuleCache) ruleCache;
        cache.doCreateCacheMap(tempDataMaskRuleManage, tempDataMaskRuleManageDetail, tempDataMaskRuleAssign, ruleMaps);
        ruleMaps.forEach((k, v) -> {
            cache.setValue(k, v);
        });
        maskRuleMaps.refresh(maskRuleManage.getRuleId().toString());
    }


    public List getTableRule(String tableName) {
        return Optional.ofNullable(maskTableRuleMaps.get(tableName.toUpperCase())).orElseGet(() -> new ArrayList<>());
    }

    public List getRuleDetailSet(String ruleId, String maskRange, String maskRangeValue) throws ExecutionException {

        Map rules = maskRuleMaps.get(ruleId);

        if (rules.containsKey(maskRange)) {
            Map ruleDetail = (Map) rules.get(maskRange);
            if (ruleDetail.containsKey(maskRangeValue)) {
                List ruleAssign = (List) ruleDetail.get(maskRangeValue);
                if (ruleAssign.size() != 0) {
                    return ruleAssign;
                }
            }
        }
        return new ArrayList();
    }

    public boolean needPermission(String sql) {
        if (maskTableRuleMaps.size() == 0) {
            return false;
        }
        List<String> tables = SqlUtil.getPermissionTables(sql);
        if (tables.size() == 0) {
            return true;
        }
        for (String table : tables) {
            if (table == null) {
                logger.error("{} has null permission table name", sql);
                continue;
            }
            if (maskTableRuleMaps.containsKey(table)) {
                return true;
            }
        }
        return false;
    }
}