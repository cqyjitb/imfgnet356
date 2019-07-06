package com.hand.hap.system.service.impl;

import com.hand.hap.account.dto.Role;
import com.hand.hap.account.dto.User;
import com.hand.hap.account.service.IUserService;
import com.hand.hap.cache.impl.ResourceItemCache;
import com.hand.hap.cache.impl.ResourceItemElementCache;
import com.hand.hap.cache.impl.RoleResourceItemCache;
import com.hand.hap.core.IRequest;
import com.hand.hap.function.dto.Resource;
import com.hand.hap.function.dto.ResourceItem;
import com.hand.hap.function.dto.ResourceItemElement;
import com.hand.hap.function.mapper.RoleFunctionMapper;
import com.hand.hap.function.service.IResourceService;
import com.hand.hap.system.service.IAccessService;
import net.logstash.logback.encoder.org.apache.commons.lang.ArrayUtils;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author njq.niu@hand-china.com
 * @author qiang.zeng@hand-china.com
 * @date 2016年3月7日
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AccessServiceImpl implements IAccessService {

    private static final String ACTION_MAINTAIN = "MAINTAIN";

    private Logger logger = LoggerFactory.getLogger(AccessServiceImpl.class);

    private HttpServletRequest request;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private RoleFunctionMapper roleFunctionMapper;

    @Autowired
    private ResourceItemCache resourceItemCache;

    @Autowired
    private RoleResourceItemCache roleResourceItemCache;

    @Autowired
    private ResourceItemElementCache resourceItemElementCache;

    @Autowired
    private IUserService userService;

    @Override
    public boolean accessMaintain() {
        return self().access(ACTION_MAINTAIN);
    }

    @Override
    public boolean access(String accessCode) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length() + 1);
        Resource resource = resourceService.selectResourceByUrl(url);
        boolean accessFlag = false;
        if (resource != null) {
            ResourceItem[] resourceItems = resourceItemCache.getValue(resource.getResourceId().toString());
            //如果页面没有设置权限组件，直接返回
            if (ArrayUtils.isEmpty(resourceItems)) {
                return accessFlag;
            }
            Long[] roleResourceItemIds = roleResourceItemCache.getValue(getRoleId().toString());
            // 如果用户角色没有分配权限组件，直接返回
            if (ArrayUtils.isEmpty(roleResourceItemIds)) {
                return accessFlag;
            }
            //根据权限编码,获取权限组件主键
            Long accessResourceItemId = null;
            for (ResourceItem resourceItem : resourceItems) {
                if (resourceItem.getItemId().equals(accessCode)) {
                    accessResourceItemId = resourceItem.getResourceItemId();
                    break;
                }
            }
            //返回角色权限组件授权情况
            accessFlag = Arrays.asList(roleResourceItemIds).contains(accessResourceItemId);
        }
        return accessFlag;
    }


    @Override
    public String accessData() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length() + 1);
        Resource resource = resourceService.selectResourceByUrl(url);
        Map accessData = new HashMap<>(3);
        if (resource == null) {
            return accessData.toString();
        }
        ResourceItem[] resourceItems = resourceItemCache.getValue(resource.getResourceId().toString());
        //如果页面没有设置权限组件，直接返回
        if (ArrayUtils.isEmpty(resourceItems)) {
            return accessData.toString();
        }
        List<Long> assignElementIds = userService.getAllAssignElementIds(getUserId(), Arrays.asList(getRoleIds()));
        // 如果用户没有分配权限组件，直接返回
        if (CollectionUtils.isEmpty(assignElementIds)) {
            return accessData.toString();
        }
        return buildAccessData(accessData, resourceItems, assignElementIds);
    }

    @Override
    public boolean accessFunction(String functionCode) {
        Long roleId = getRoleId();
        int result = roleFunctionMapper.selectCountByFunctionCode(roleId, functionCode);
        return result > 0;
    }

    @Override
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 构建权限组件授权数据.
     *
     * @param accessData       授权数据
     * @param resourceItems    权限组件集合
     * @param assignElementIds 权限组件分配Id集合
     * @return json格式的授权数据
     */
    private String buildAccessData(Map accessData, ResourceItem[] resourceItems, List<Long> assignElementIds) {
        accessData.put(ResourceItem.TYPE_BUTTON, new ArrayList<>());
        accessData.put(ResourceItem.TYPE_GRID, new ArrayList<>());
        accessData.put(ResourceItem.TYPE_FORM, new ArrayList<>());
        for (ResourceItem resourceItem : resourceItems) {
            ResourceItemElement[] elements = resourceItemElementCache.getValue(resourceItem.getResourceItemId().toString());
            if (ArrayUtils.isNotEmpty(elements)) {
                for (ResourceItemElement element : elements) {
                    if (assignElementIds.contains(element.getElementId())) {
                        buildElements(resourceItem, element, accessData);
                    }
                }
            }
        }
        return JSONObject.fromObject(accessData).toString();
    }

    /**
     * 构建权限组件授权数据.
     *
     * @param resourceItem 权限组件
     * @param element      权限组件元素
     * @param accessData   授权数据
     */
    private void buildElements(ResourceItem resourceItem, ResourceItemElement element, Map accessData) {
        String type = resourceItem.getItemType();
        String itemId = resourceItem.getItemId();
        if (ResourceItem.TYPE_BUTTON.equalsIgnoreCase(type)) {
            buildElement(element.getProperty(), element.getPropertyValue(), accessData, "buttons");
        } else if (ResourceItem.TYPE_FORM.equalsIgnoreCase(type)) {
            List<Map> formList = (List<Map>) accessData.get(ResourceItem.TYPE_FORM);
            Map form = null;
            for (Map map : formList) {
                String id = (String) map.get("id");
                if (id.equals(itemId)) {
                    form = map;
                    break;
                }
            }
            if (form == null) {
                form = new HashMap(3);
                form.put("id", itemId);
                form.put("fields", new ArrayList<>());
                form.put("buttons", new ArrayList<>());
                formList.add(form);
            }
            if (ResourceItemElement.TYPE_FORM_BUTTONS.equalsIgnoreCase(element.getType())) {
                buildElement(element.getProperty(), element.getPropertyValue(), form, "fields");
            } else if (ResourceItemElement.TYPE_FORM_FIELD.equalsIgnoreCase(element.getType())) {
                buildElement(element.getProperty(), element.getPropertyValue(), form, "buttons");
            }
        } else if (ResourceItem.TYPE_GRID.equalsIgnoreCase(type)) {
            List<Map> gridList = (List<Map>) accessData.get(ResourceItem.TYPE_GRID);
            Map grid = null;
            for (Map map : gridList) {
                String id = (String) map.get("id");
                if (id.equals(itemId)) {
                    grid = map;
                    break;
                }
            }
            if (grid == null) {
                grid = new HashMap(4);
                grid.put("id", itemId);
                grid.put("buttons", new ArrayList<>());
                grid.put("columnButtons", new ArrayList<>());
                grid.put("columns", new ArrayList<>());
                gridList.add(grid);
            }
            if (ResourceItemElement.TYPE_GRID_BUTTONS.equalsIgnoreCase(element.getType())) {
                buildElement(element.getProperty(), element.getPropertyValue(), grid, "buttons");
            } else if (ResourceItemElement.TYPE_COLUMN_BUTTONS.equalsIgnoreCase(element.getType())) {
                buildElement(element.getProperty(), element.getPropertyValue(), grid, "columnButtons");
            } else if (ResourceItemElement.TYPE_COLUMN.equalsIgnoreCase(element.getType())) {
                buildElement(element.getProperty(), element.getPropertyValue(), grid, "columns");
            }
        }
    }

    /**
     * 构建权限组件元素授权数据.
     *
     * @param property      属性
     * @param propertyValue 属性值
     * @param accessData    授权数据
     * @param type          权限组件类型
     */
    private void buildElement(String property, String propertyValue, Map accessData, String type) {
        List<Map> mapList = (List<Map>) accessData.get(type);
        Map map = new HashMap(1);
        map.put(property, propertyValue);
        mapList.add(map);
    }

    /**
     * 从session获取用户Id.
     *
     * @return 用户Id
     */
    private Long getUserId() {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Long) session.getAttribute(User.FIELD_USER_ID);
        }
        return null;
    }

    /**
     * 从session获取角色Id.
     *
     * @return 角色Id
     */
    private Long getRoleId() {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Long) session.getAttribute(Role.FIELD_ROLE_ID);
        }
        return null;
    }

    /**
     * 从session获取角色Id集合.
     *
     * @return 角色Id集合
     */
    private Long[] getRoleIds() {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Long[]) session.getAttribute(IRequest.FIELD_ALL_ROLE_ID);
        }
        return null;
    }
}
