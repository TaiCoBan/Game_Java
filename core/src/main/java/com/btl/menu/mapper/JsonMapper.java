package com.btl.menu.mapper;

import com.badlogic.gdx.utils.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

//    public static Json toJson(Object obj) {
//        try {
//            String jsonStr = mapper.writeValueAsString(obj);
//            return new Json(jsonStr);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static <T> T fromJson(Json json, Class<T> clazz) {
//        try {
//            return mapper.readValue(json., clazz);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
