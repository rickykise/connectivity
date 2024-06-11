package ai.fassto.connectivity.common.utility;

import ai.fassto.connectivity.common.configuration.AnnotationExclusionStrategy;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;


public class MapperUtil {
    private static ObjectMapper objectMapper = null;
    private static Gson gsonForArgument;

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }

        return objectMapper;
    }

    public static String toJson(Object object) {
        ObjectMapper objectMapper = getObjectMapper();
        String json = "";

        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static Gson getGsonForArgument() {
        if (gsonForArgument == null) {
            gsonForArgument = new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create();
        }

        return gsonForArgument;
    }

    public static String argsToJson(Object object) {
        return getGsonForArgument().toJson(object);
    }

    public static <T> T toObject(Object object, Class<T> clazz) {

        ObjectMapper objectMapper = getObjectMapper();
        T obj = null;
        try {
            obj = objectMapper.readValue(toJson(object), clazz);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return obj;
    }

    public static <T> T toObject(String json, Class<T> clazz) {

        ObjectMapper objectMapper = getObjectMapper();
        T obj = null;
        try {
            obj = objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return obj;
    }
}
