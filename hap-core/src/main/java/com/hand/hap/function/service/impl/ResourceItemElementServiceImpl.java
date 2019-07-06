package com.hand.hap.function.service.impl;

import com.hand.hap.cache.impl.ResourceItemElementCache;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.function.dto.ResourceItemElement;
import com.hand.hap.function.mapper.ResourceItemAssignMapper;
import com.hand.hap.function.mapper.ResourceItemElementMapper;
import com.hand.hap.function.service.IResourceItemElementService;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限组件元素服务接口实现.
 *
 * @author qiang.zeng
 */
@Service
public class ResourceItemElementServiceImpl extends BaseServiceImpl<ResourceItemElement> implements IResourceItemElementService {
    @Autowired
    private ResourceItemElementMapper elementMapper;
    @Autowired
    private ResourceItemElementCache elementCache;
    @Autowired
    private ResourceItemAssignMapper itemAssignMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResourceItemElement insertSelective(IRequest request, @StdWho ResourceItemElement element) {
        if (null == element) {
            return null;
        }
        super.insertSelective(request, element);
        elementCache.load(element.getResourceItemId());
        return element;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResourceItemElement updateByPrimaryKey(IRequest request, ResourceItemElement element) {
        if (null == element) {
            return null;
        }
        super.updateByPrimaryKey(request, element);
        elementCache.load(element.getResourceItemId());
        return element;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ResourceItemElement> batchUpdate(IRequest request, List<ResourceItemElement> elementList) {
        if (CollectionUtils.isEmpty(elementList)) {
            return elementList;
        }
        for (ResourceItemElement element : elementList) {
            if (element.getElementId() == null) {
                self().insertSelective(request, element);
            } else {
                self().updateByPrimaryKey(request, element);
            }
        }
        return elementList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDelete(IRequest requestContext, List<ResourceItemElement> elementList) {
        int result = 0;
        if (CollectionUtils.isEmpty(elementList)) {
            return result;
        }
        for (ResourceItemElement element : elementList) {
            int updateCount = elementMapper.deleteByPrimaryKey(element);
            checkOvn(updateCount, element);
            itemAssignMapper.deleteByElementId(element.getElementId());
            result++;
        }
        elementCache.load(elementList.get(0).getResourceItemId());
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ResourceItemElement> selectByResourceItemId(IRequest requestContext, ResourceItemElement element) {
        return elementMapper.selectByResourceItemId(element);
    }
}