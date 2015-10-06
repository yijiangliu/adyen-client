package com.github.woki.payments.adyen.client.model;

import com.github.woki.payments.adyen.model.BrowserInfo;

/**
 * Created by Willian Oki on 9/28/15.
 */
public class YAMLBrowserInfo {
    private String accept;
    private String userAgent;

    public BrowserInfo toBrowserInfo() {
        return new BrowserInfo(userAgent, accept);
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
