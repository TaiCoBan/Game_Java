package com.btl.menu.api;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.btl.menu.dto.response.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.btl.menu.constant.Constant.DEBUG;

public class Request {

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public Request(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.httpClient = HttpClient.newHttpClient();
    }

    public <T> ApiResponse<T> sendRequest(
        String method,
        String url,
        Object object
    ) {
        ApiResponse<T> apiResponse = null;
        try {
            HttpRequest.Builder request = HttpRequest.newBuilder();

            // METHOD, BODY
            HttpRequest.BodyPublisher bodyPublisher = (object != null)
                                                          ? HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(object))
                                                          : HttpRequest.BodyPublishers.noBody();
            request.method(method, bodyPublisher);

            // URL
            request.uri(URI.create(url));

            // HEADER
            if (object != null) {
                request.header("Content-Type", "application/json");
            }

            HttpResponse<byte[]> response = httpClient.<byte[]>send(request.build(), HttpResponse.BodyHandlers.ofByteArray());

            apiResponse = objectMapper.readValue(response.body(), ApiResponse.class);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return apiResponse;
    }
}
