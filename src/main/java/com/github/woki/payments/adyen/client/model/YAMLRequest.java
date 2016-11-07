package com.github.woki.payments.adyen.client.model;

import java.util.Map;

/**
 * Created by Willian Oki on 9/23/15.
 */
public class YAMLRequest {
    private String type;
    private String endpoint;
    private String proxyConfig;
    private YAMLCredentials credentials;
    private String encryptionKey;
    private Map<String, String> extraParameters;
    private YAMLPaymentRequest paymentRequest;
    private YAMLModificationRequest modificationRequest;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getProxyConfig() {
        return proxyConfig;
    }

    public void setProxyConfig(String proxyConfig) {
        this.proxyConfig = proxyConfig;
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

    public Map<String, String> getExtraParameters() {
        return extraParameters;
    }

    public void setExtraParameters(Map<String, String> extraParams) {
        this.extraParameters = extraParams;
    }

    public YAMLModificationRequest getModificationRequest() {
        return modificationRequest;
    }

    public void setModificationRequest(YAMLModificationRequest modificationRequest) {
        this.modificationRequest = modificationRequest;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }
}
