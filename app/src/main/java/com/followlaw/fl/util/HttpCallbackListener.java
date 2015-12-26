package com.followlaw.fl.util;

/**
 * Created by ubuntu on 12/26/15.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
