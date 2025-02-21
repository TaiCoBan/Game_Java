package com.btl.menu.api;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.btl.menu.dto.response.ApiResponse;
import com.btl.menu.entity.Account;
import com.btl.menu.service.LocalStorageService;
import com.fasterxml.jackson.core.type.TypeReference;

import static com.btl.menu.constant.Constant.*;

public class Request {
    private final Game game;
    private final HttpRequestBuilder httpRequestBuilder;
    private final LocalStorageService localStorageService;

    public Request(Game game, LocalStorageService localStorageService) {
        this.game = game;
        this.httpRequestBuilder = new HttpRequestBuilder();
        this.localStorageService = localStorageService;
    }

    public <T> void sendRequest(String method, String url, Object object, TypeReference<ApiResponse<T>> typeReference) {
        String cacheKey = method + ":" + url;
        // Kiểm tra cache trước khi gửi request
        T cachedResult = localStorageService.get(cacheKey, new TypeReference<T>() {});
        if (cachedResult != null) {
            // Xử lý dữ liệu từ cache
            return;
        }

        Gdx.app.log("Request", "[" + method + "] " + url);
        Net.HttpRequest request = httpRequestBuilder.newRequest()
                                      .method(method)
                                      .url(url)
                                      .build();

        if (object != null) {
            Json json = new Json();
            json.setOutputType(JsonWriter.OutputType.json);
            String requestBody = json.toJson(object);
            request.setHeader("Content-Type", "application/json");
            request.setContent(requestBody);
        }

        Gdx.net.sendHttpRequest(request, new ResponseListener<>(game, localStorageService, cacheKey, typeReference));
    }
}
