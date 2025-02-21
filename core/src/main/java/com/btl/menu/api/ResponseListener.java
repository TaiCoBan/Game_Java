package com.btl.menu.api;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.btl.menu.dto.response.ApiResponse;
import com.btl.menu.service.LocalStorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class ResponseListener<T> implements Net.HttpResponseListener {
    private final Game game;
    private final LocalStorageService localStorageService;
    private final String cacheKey;
    private final TypeReference<ApiResponse<T>> typeReference;

    public ResponseListener(Game game, LocalStorageService localStorageService, String cacheKey, TypeReference<ApiResponse<T>> typeReference) {
        this.game = game;
        this.localStorageService = localStorageService;
        this.cacheKey = cacheKey;
        this.typeReference = typeReference;
    }

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        String response = httpResponse.getResultAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ApiResponse<T> apiResponse = objectMapper.readValue(response, typeReference);
            T result = apiResponse.getResult();
            localStorageService.put(cacheKey, result);
            // Xử lý result ở đây (ví dụ: cập nhật UI)
            Gdx.app.log("ApiResponse", apiResponse.toString());
            Gdx.app.log("ApiResponse result", apiResponse.getResult().getClass().getSimpleName() + apiResponse.getResult());
            Gdx.app.log("ApiResponse T", result.getClass().getSimpleName() + result);
        } catch (JsonProcessingException e) {
            Gdx.app.error("JSON Error", e.getMessage());
        } catch (Exception e) {
            Gdx.app.error("Error", e.getMessage());
        }
    }

    @Override
    public void failed(Throwable t) {
        Gdx.app.error("Failed", t.toString());
    }

    @Override
    public void cancelled() {
        Gdx.app.debug("Cancelled", "Request cancelled");
    }
}
