package com.btl.menu.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.Json;
import com.btl.menu.dto.response.ApiResponse;
import com.btl.menu.entity.Account;

public class ResponseListener implements Net.HttpResponseListener {
    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        Json json = new Json();
        Gdx.app.log("Handle response", json.prettyPrint(httpResponse.getResultAsString()));
        ApiResponse apiResponse = json.fromJson(ApiResponse.class, httpResponse.getResultAsString());
        Gdx.app.log("Handle response", apiResponse.getCode() + " " + apiResponse.getMessage() + " " + apiResponse.getResult().toString());
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
