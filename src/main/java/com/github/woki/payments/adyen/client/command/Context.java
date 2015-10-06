package com.github.woki.payments.adyen.client.command;

import com.github.woki.payments.adyen.Client;
import com.github.woki.payments.adyen.model.ModificationRequest;
import com.github.woki.payments.adyen.model.ModificationResponse;
import com.github.woki.payments.adyen.model.PaymentRequest;
import com.github.woki.payments.adyen.model.PaymentResponse;

/**
 * Created by Willian Oki on 9/24/15.
 */
public class Context {
    private PaymentRequest paymentRequest;
    private PaymentResponse paymentResponse;
    private ModificationRequest modificationRequest;
    private ModificationResponse modificationResponse;
    private Client client;

    public PaymentRequest getPaymentRequest() {
        return paymentRequest;
    }

    public void setPaymentRequest(PaymentRequest paymentRequest) {
        this.paymentRequest = paymentRequest;
    }

    public ModificationRequest getModificationRequest() {
        return modificationRequest;
    }

    public void setModificationRequest(ModificationRequest modificationRequest) {
        this.modificationRequest = modificationRequest;
    }

    public PaymentResponse getPaymentResponse() {
        return paymentResponse;
    }

    public void setPaymentResponse(PaymentResponse paymentResponse) {
        this.paymentResponse = paymentResponse;
    }

    public ModificationResponse getModificationResponse() {
        return modificationResponse;
    }

    public void setModificationResponse(ModificationResponse modificationResponse) {
        this.modificationResponse = modificationResponse;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
