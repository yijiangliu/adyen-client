package com.github.woki.payments.adyen.client.control;

import com.github.woki.payments.adyen.Client;
import com.github.woki.payments.adyen.client.command.*;
import com.github.woki.payments.adyen.client.model.YAMLRequest;
import org.apache.commons.cli.Options;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by Willian Oki on 9/24/15.
 */
public final class ControlUtil {
    private ControlUtil() {
        // utility
    }

    public static Options buildOptions() {
        Options options = new Options();
        options.addOption("f", "request-file", true, "the request file path");
        options.addOption("h", "help", false, "print this message");
        options.addOption("a", "auto-merchref", true, "auto generate a merchant reference; available reference types: timestamp, uuid");
        options.addOption("r", "merchref", true, "replace request file's paymentRequest.reference / modificationRequest.reference");
        options.addOption("o", "orig-ref", true, "replace request file's modificationRequest.originalReference");
        options.addOption("v", "value", true, "replace request file's modificationRequest.modificationAmount");
        return options;
    }

    public static String makeMerchantReference(String type) {
        switch (type) {
            case "uuid":
                return UUID.randomUUID().toString();
            case "timestamp":
            default:
                return String.valueOf(System.currentTimeMillis());
        }
    }

    public static Command getCommand(@NotNull YAMLRequest request) {
        switch (request.getType()) {
            case "authorization":
                return new Authorization(getContext(request));
            case "authorization3d":
                return new Authorization3d(getContext(request));
            case "cancel":
                return new Cancel(getContext(request));
            case "cancelOrRefund":
                return new CancelOrRefund(getContext(request));
            case "refund":
                return new Refund(getContext(request));
            case "capture":
                return new Capture(getContext(request));
            default:
                throw new IllegalArgumentException("Invalid request type: " + request.getType());
        }
    }

    private static Context getContext(YAMLRequest request) {
        Context context = new Context();
        context.setClient(getClient(request));
        switch (request.getType()) {
            case "authorization":
            case "authorization3d":
                context.setPaymentRequest(request.getPaymentRequest().toPaymentRequest());
                break;
            case "cancel":
            case "cancelOrRefund":
            case "refund":
            case "capture":
                context.setModificationRequest(request.getModificationRequest().toModificationRequest());
                break;
            default:
                throw new IllegalArgumentException("Invalid request type: " + request.getType());
        }
        return context;
    }

    private static Client getClient(YAMLRequest request) {
        return Client
                .endpoint(request.getEndpoint())
                .credentials(request.getCredentials().getUsername(), request.getCredentials().getPassword())
                .proxyConfig(request.getProxyConfig())
                .extraParameters(request.getExtraParameters())
                .build();
    }
}
