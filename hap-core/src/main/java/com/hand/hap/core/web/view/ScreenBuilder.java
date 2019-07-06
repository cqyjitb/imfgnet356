package com.hand.hap.core.web.view;

import com.hand.hap.core.web.view.ui.ViewTag;

/**
 * 
 * @author njq.niu@hand-china.com
 *
 */
public class ScreenBuilder {

    public static String build(XMap xmap, ViewContext context) throws Exception {
        context = context.createCloneInstance();
        ViewTag tag = context.getScreenTagFactory().getTag(xmap.getNamespaceURI(), xmap.getName());
        //TODO ： 更好的处理方式
        tag = tag.getClass().newInstance();
        if (tag == null) {
            throw new Exception("Tag <" + xmap.getName() + "> not found!");
        }
        tag.init(xmap, context);
        return tag.execute(xmap, context);
    }
}
