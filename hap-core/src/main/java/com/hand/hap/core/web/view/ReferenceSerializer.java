package com.hand.hap.core.web.view;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 
 * @author njq.niu@hand-china.com
 *
 */
public class ReferenceSerializer extends JsonSerializer<ReferenceType> {

    @Override
    public void serialize(ReferenceType type, JsonGenerator jgen, SerializerProvider prov) throws IOException, JsonProcessingException {
        if (type.reference != null)
            jgen.writeRawValue(type.reference);
    }
}
