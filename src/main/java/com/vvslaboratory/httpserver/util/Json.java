package com.vvslaboratory.httpserver.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.io.IOException;

public class Json {

        private static ObjectMapper myObjectMapper = defaultObjectMapper();

        private static ObjectMapper defaultObjectMapper(){
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false); //parsing didn't crash in case of one property missing
            return om;
        }

        public static JsonNode parse(String jsonSrc) throws IOException {     // json String --> json Node
            return myObjectMapper.readTree(jsonSrc);
        }
}
