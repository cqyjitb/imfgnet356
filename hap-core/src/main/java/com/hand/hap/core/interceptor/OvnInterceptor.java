/*
 * #{copyright}#
 */
package com.hand.hap.core.interceptor;

import java.util.Map;
import java.util.Properties;

import com.hand.hap.core.AppContextInitListener;
import com.hand.hap.core.BaseConstants;
import com.hand.hap.core.components.ApplicationContextHelper;
import com.hand.hap.mybatis.common.Criteria;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.hand.hap.core.components.DatabaseExceptionTranslator;
import com.hand.hap.system.dto.BaseDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 插入、更新以后,自动设置 objectversionnumber。<br>
 * 更新、删除 失败以后，抛出异常的动作，要在具体执行操作的地方自己检测。<br>
 * BaseServiceImpl 已经支持
 *
 * @author shengyang.zhou@hand-china.com
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class OvnInterceptor implements Interceptor {


    private DatabaseExceptionTranslator databaseExceptionTranslator;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Object domain = args[1];

        Object result = null;
        try {
            result = invocation.proceed();
        } catch (Exception e) {
            if(null == databaseExceptionTranslator) {
                databaseExceptionTranslator = ApplicationContextHelper.getApplicationContext().getBean(DatabaseExceptionTranslator.class);
            }
            throw databaseExceptionTranslator.translateException(e, domain);
        }

        if(domain instanceof MapperMethod.ParamMap){
            Map map = ((Map) domain);
            if (map.containsKey(BaseConstants.OPTIONS_DTO)) {
                domain = ((Map) domain).get(BaseConstants.OPTIONS_DTO);
            }
        }
        MappedStatement mappedStatement = (MappedStatement) args[0];
        if (!(domain instanceof BaseDTO)) {
            return result;
        }
        BaseDTO baseDTO = (BaseDTO) domain;
        Long ovn = baseDTO.getObjectVersionNumber();
        if (mappedStatement.getSqlCommandType() == SqlCommandType.INSERT) {
            baseDTO.setObjectVersionNumber(1L);
            return result;
        }
        if (ovn == null) {
            return result;
        }
        if (mappedStatement.getSqlCommandType() == SqlCommandType.UPDATE) {
            baseDTO.setObjectVersionNumber(ovn + 1L);
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
