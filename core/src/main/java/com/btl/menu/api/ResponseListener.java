package com.btl.menu.api;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.btl.menu.dto.response.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class ResponseListener implements Net.HttpResponseListener {

  private final Game game;
  private final TypeReference<?> typeRef;

  public ResponseListener(Game game, TypeReference<?> typeRef) {
    this.game = game;
    this.typeRef = typeRef;
  }

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        String response = httpResponse.getResultAsString();

        try {
            ApiResponse<?> apiResponse = objectMapper.readValue(response, ApiResponse.class);
            var result = apiResponse.getResult();
            if (result instanceof ArrayList<?>) {
                System.out.println(((ArrayList<?>) result).get(0).getClass().getSimpleName());
            }

            Gdx.app.log("ApiResponse", objectMapper.writeValueAsString(apiResponse));
            Gdx.app.log("ApiResponse result", objectMapper.writeValueAsString(apiResponse.getResult()));
            Gdx.app.log("Result", objectMapper.writeValueAsString(result));

        } catch (JsonProcessingException e) {
            Gdx.app.error("JSON Error", e.getMessage());
        } catch (Exception e) {
            Gdx.app.error("Error", e.getMessage());
        }
    }

  @Override
  public void failed(Throwable throwable) {
    Gdx.app.error("Failed", throwable.toString());
  }

  @Override
  public void cancelled() {
    Gdx.app.debug("Cancelled", "Request cancelled");
  }
}
