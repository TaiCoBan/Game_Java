package com.btl.menu.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;

public class ResponseListener implements Net.HttpResponseListener {
    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        Gdx.app.log("(default) handleHttpResponse", httpResponse.toString());
    }

    @Override
    public void failed(Throwable throwable) {
        Gdx.app.error("(default) failed", throwable.toString());
    }

    @Override
    public void cancelled() {
        Gdx.app.debug("(default) cancelled", "cancelled");
    }
}
