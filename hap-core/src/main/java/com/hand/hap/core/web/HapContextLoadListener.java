/*
 * Copyright Hand China Co.,Ltd.
 */

package com.hand.hap.core.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.ibatis.javassist.CannotCompileException;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.NotFoundException;

import com.hand.hap.extensible.base.DtoExtensionManager;
import com.hand.hap.mybatis.util.OGNL;

/**
 * @author shengyang.zhou@hand-china.com
 */
public class HapContextLoadListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            DtoExtensionManager.scanExtendConfig();
            dynamicCreateOGNL();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * dynamic create a class OGNL, extends com.hand.hap.mybatis.util.OGNL
     * <p>
     * purpose: can use
     * 
     * <pre>
     * &#64;OGNL@xx()
     * </pre>
     * 
     * in ognl expression
     * <p>
     * short for
     * 
     * <pre>
     * &#64;com.hand.hap.mybatis.util.OGNL@xx()
     * </pre>
     * 
     * @throws NotFoundException
     * @throws CannotCompileException
     */
    private void dynamicCreateOGNL() throws NotFoundException, CannotCompileException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get(OGNL.class.getName());
        CtClass newOgnl = classPool.makeClass("OGNL", ctClass);
        newOgnl.toClass();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
