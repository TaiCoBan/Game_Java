package com.btl.menu.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.Json;
import com.btl.menu.dto.response.Account;
import com.btl.menu.dto.response.ApiResponse;

public class ResponseListener implements Net.HttpResponseListener {
    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        String response = httpResponse.getResultAsString();
        Gdx.app.log("Handle response", response);

        Json json = new Json();
        json.setIgnoreUnknownFields(true);
        // Xác định kiểu cho trường "result" (giả sử là đối tượng User)
        json.setElementType(ApiResponse.class, "result", Account.class);

        ApiResponse apiResponse = json.fromJson(ApiResponse.class, response);
        if(apiResponse != null) {
            if(apiResponse.getResult() instanceof Account) {
                Gdx.app.log("account", ((Account) apiResponse.getResult()).getEmail());
            }
            Gdx.app.log("Handle response", apiResponse.toString());
//            Gdx.app.log("Handle response", apiResponse.getCode() + " " +
//                                               (apiResponse.getMessage() != null ? apiResponse.getMessage() : "") +
//                                               " " + (apiResponse.getResult() != null ? apiResponse.getResult().toString() : ""));
        } else {
            Gdx.app.error("Handle response", "ApiResponse is null");
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
