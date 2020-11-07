package com.vvslaboratory.httpserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.io.IOException;

public class Json {

        private static ObjectMapper myObjectMapper = defaultObjectMapper();

        private static ObjectMapper defaultObjectMapper() {
            ObjectMapper om = new ObjectMapper();
            //Deserializare
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false); //parsing didn't crash in case of one property missing
            return om;
        }

        //Parse
        public static JsonNode parse(String jsonSrc) throws IOException {     // json String --> json Node
            return myObjectMapper.readTree(jsonSrc);
        }
    /**
     * From Json to Configuration file
     */

        public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {  /// generic method
            return myObjectMapper.treeToValue(node, clazz);
        }

    /**
     * Getting Configuration file to a Json node
     */

        public static JsonNode toJson(Object obj){   // genereaza Json node
            return myObjectMapper.valueToTree(obj);
        }


    public static  String stringify(Json node) throws JsonProcessingException {
        return generateJson(node, false);
    }

    public static  String stringifyPretty (Json node) throws JsonProcessingException {
        return generateJson(node, true);
    }

    /**
     * Json node in a String format
     */

    private static String generateJson(Object o, boolean pretty) throws JsonProcessingException {         // metoda auxiliara
            ObjectWriter objectWriter = myObjectMapper.writer();
            if(pretty){
                objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
            }
           return objectWriter.writeValueAsString(o);
        }
}









