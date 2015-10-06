package com.github.woki.payments.adyen.client.model;

import java.util.Map;

/**
 * Created by Willian Oki on 9/23/15.
 */
public class YAMLRequest {
    private String type;
    private String serviceUrl;
    private YAMLCredentials credentials;
    private Map<String, String> extraParams;
    private YAMLPaymentRequest paymentRequest;
    private YAMLModificationRequest modificationRequest;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public YAMLCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(YAMLCredentials credentials) {
        this.credentials = credentials;
    }

    public YAMLPaymentRequest getPaymentRequest() {
        return paymentRequest;
    }

    public void setPaymentRequest(YAMLPaymentRequest paymentRequest) {
        this.paymentRequest = paymentRequest;
    }

    public Map<String, String> getExtraParams() {
        return extraParams;
    }

    public void setExtraParams(Map<String, String> extraParams) {
        this.extraParams = extraParams;
    }

    public YAMLModificationRequest getModificationRequest() {
        return modificationRequest;
    }

    public void setModificationRequest(YAMLModificationRequest modificationRequest) {
        this.modificationRequest = modificationRequest;
    }
}
