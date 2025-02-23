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
    private final ObjectMapper objectMapper;
    private final String cacheKey;
    private final TypeReference<ApiResponse<T>> typeReference;

    public ResponseListener(Game game,
                            LocalStorageService localStorageService,
                            ObjectMapper objectMapper,
                            String cacheKey,
                            TypeReference<ApiResponse<T>> typeReference) {
        this.game = game;
        this.localStorageService = localStorageService;
        this.objectMapper = objectMapper;
        this.cacheKey = cacheKey;
        this.typeReference = typeReference;
    }

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        handleResponse(httpResponse);
    }

    private void handleResponse(Net.HttpResponse httpResponse) {
        ApiResponse<T> apiResponse = null;
        try {
            apiResponse = objectMapper.readValue(httpResponse.getResultAsString(), typeReference);
        } catch (JsonProcessingException e) {
            Gdx.app.error("JSON Error", e.getMessage());
        }

        int statusCode = apiResponse.getCode();

        switch (statusCode) {
            case 200, 201: {
                cacheResponse(apiResponse);
                break;
            }
            case 400, 403, 404: {
                break;
            }
        }

        Gdx.app.log("RESPONSE MESSAGE", apiResponse.getCode() + " " + apiResponse.getMessage());
    }

    private void cacheResponse(ApiResponse<T> apiResponse) {
        T result = apiResponse.getResult();
        localStorageService.put(cacheKey, result);
    }

    @Override
    public void failed(Throwable throwable) {
        Gdx.app.error("Error", throwable.getMessage());
    }

    @Override
    public void cancelled() {
        Gdx.app.error("Error", "Cancelled");
    }
}
