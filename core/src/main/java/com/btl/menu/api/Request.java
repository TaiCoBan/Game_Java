package com.btl.menu.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.btl.menu.entity.Account;
import com.btl.menu.service.LocalStorageService;

import static com.btl.menu.constant.Constant.*;

public class Request {

    private static final HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();

    public static void sendRequest(String method, String url, Object object) {
        Gdx.app.log("(send request)", "["+method+"]" + " " + url);

        Net.HttpRequest request = httpRequestBuilder
                                      .newRequest()
                                      .method(method)
                                      .url(url)
                                      .build();

        Gdx.net.sendHttpRequest(request, new ResponseListener());
    }

    public static void sendAuthRequest(String method, String url, Object object) {
        Gdx.app.log("(send auth request)", "["+method+"]" + " " + url);

        String username = "user1234";
        String password = "user1234";

        Net.HttpRequest request = httpRequestBuilder
                                      .newRequest()
                                      .method(method)
                                      .url(url)
                                      .basicAuthentication(username, password)
                                      .build();

        Gdx.net.sendHttpRequest(request, new ResponseListener());
    }
}
