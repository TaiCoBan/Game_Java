package com.btl.menu.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.Base64Coder;
import com.btl.menu.entity.Account;
import com.btl.menu.service.LocalStorageService;

import static com.btl.menu.constant.Constant.*;

public class Request {

    private static final HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();

    public static void sendRequest(String method, String url, Object object) {
        Gdx.app.log("(send request)", "["+method+"]" + " " + url);

        String username = "acacacac";
        String password = "acacacac";

        Net.HttpRequest request = httpRequestBuilder
                                      .newRequest()
                                      .method(method)
                                      .url(url)
                                      .basicAuthentication(username, password)
                                      .jsonContent(object)
                                      .build();

        Gdx.net.sendHttpRequest(request, new ResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                int statusCode = httpResponse.getStatus().getStatusCode();
                if (statusCode == 200) {
                    String response = httpResponse.getResultAsString();
                    Gdx.app.log("HTTP", "Response: " + response);
                } else {
                    Gdx.app.log("HTTP", "Error: " + statusCode);
                }
            }
        });
    }
}
