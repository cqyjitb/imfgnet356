package com.hand.hap.activiti.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.impl.RequestHelper;
import com.hand.hap.hr.mapper.PositionMapper;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.data.impl.MybatisGroupDataManager;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.hand.hap.activiti.util.ActivitiUtils;
import com.hand.hap.hr.dto.Position;
import com.hand.hap.message.IMessageConsumer;
import com.hand.hap.message.TopicMonitor;

/**
 * @author shengyang.zhou@hand-china.com
 */
@TopicMonitor(channel = "position.change")
public class CustomGroupDataManager extends MybatisGroupDataManager
        implements IMessageConsumer<Position>, InitializingBean {

    @Autowired
    private PositionMapper positionMapper;

    private Map<String, Map<String, GroupEntity>> multiLangGroupCache = new HashMap<>();

    @Autowired
    private SpringProcessEngineConfiguration pec;

    public CustomGroupDataManager() {
        super(null);
    }

    @Override
    public List<Group> findGroupsByUser(String userId) {
        List<Position> positions = positionMapper.getPositionByEmployeeCode(userId);
        List<Group> gs = new ArrayList<>();
        for (Position position : positions) {
            gs.add(ActivitiUtils.toActivitiGroup(position));
        }
        return gs;
    }

    /**
     * 这个方法使用非常频繁，做缓存支持
     * 
     * @param entityId
     * @return
     */
    @Override
    public GroupEntity findById(String entityId) {
        IRequest request = RequestHelper.getCurrentRequest(true);
        Map<String, GroupEntity> groupCache = multiLangGroupCache.get(request.getLocale());
        if (groupCache == null) {
            groupCache = new HashMap<>();
            multiLangGroupCache.put(request.getLocale(), groupCache);
        }
        GroupEntity groupEntity = groupCache.get(entityId);
        if (groupEntity == null) {
            Position position = positionMapper.getPositionByCode(entityId);
            groupEntity = ActivitiUtils.toActivitiGroup(position);
            groupCache.put(entityId, groupEntity);
        }
        return groupEntity;
    }

    @Override
    public void onMessage(Position message, String pattern) {
        multiLangGroupCache.forEach((k, v) -> v.remove(message.getPositionCode()));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.processEngineConfiguration = pec;
    }
}
