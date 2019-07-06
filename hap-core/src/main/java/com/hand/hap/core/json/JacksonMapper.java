/*
 * #{copyright}#
 */
package com.hand.hap.core.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Date;

/**
 * @author njq.niu@hand-china.com
 * @date 2016年3月17日
 */
public class JacksonMapper extends ObjectMapper {

    public JacksonMapper() {
        super();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Date.class, new DateTimeSerializer());
        module.addDeserializer(Date.class, new DateTimeDeserializer());
        module.addDeserializer(String.class, new CustomStringDeserializer());
        this.registerModule(module);
    }
}
