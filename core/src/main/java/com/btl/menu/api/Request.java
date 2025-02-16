package com.btl.menu.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.Base64Coder;
import com.btl.menu.entity.Account;
import com.btl.menu.service.LocalStorageService;

import static com.btl.menu.constant.Constant.*;

public class Request {

    HttpRequestBuilder httpRequestBuilder;

    public Request() {
        this.httpRequestBuilder = new HttpRequestBuilder();
    }

    public Net.HttpResponse GET(String uri) {
        Gdx.app.log("(GET request) URI: ", uri);
        Net.HttpRequest httpRequest = httpRequestBuilder.newRequest().method(Net.HttpMethods.GET).url(BASE_API_URL).content("q=libgdx&example=example").build();
        Gdx.net.sendHttpRequest(httpRequest, new ResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {

            }
        });
        return null;
    }

    public void POST(String url, Object object) {
        Gdx.app.log("POST", url);

        String username = "acacacac";
        String password = "acacacac";

        String auth = username + ":" + password;
        String encodedAuth = "Basic " + Base64Coder.encodeString(auth);

        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest request = requestBuilder.newRequest()
                                      .method(Net.HttpMethods.GET)
                                      .url(TEST_URL)
                                      .header("Authorization", encodedAuth)
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
