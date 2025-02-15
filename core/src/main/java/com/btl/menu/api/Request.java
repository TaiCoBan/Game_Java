package com.btl.menu.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.btl.menu.constant.Constant;

public class Request {

    HttpRequestBuilder httpRequestBuilder;

    public Request() {
        this.httpRequestBuilder = new HttpRequestBuilder();
    }

    public Net.HttpResponse GET(String uri) {
        Net.HttpRequest httpRequest = httpRequestBuilder.newRequest().method(Net.HttpMethods.GET).url(Constant.BASE_API_URL).content("q=libgdx&example=example").build();
        Gdx.net.sendHttpRequest(httpRequest, new ResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {

            }
        });
        return null;
    }

    public Net.HttpResponse POST(String uri, String content) {

        return null;
    }

    Request getRequest() {
        return new Request();
    }
}
