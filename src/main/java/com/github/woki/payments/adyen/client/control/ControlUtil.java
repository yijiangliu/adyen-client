package com.github.woki.payments.adyen.client.control;

import javax.validation.constraints.NotNull;
import com.github.woki.payments.adyen.APService;
import com.github.woki.payments.adyen.Client;
import com.github.woki.payments.adyen.client.command.Authorization;
import com.github.woki.payments.adyen.client.command.Authorization3d;
import com.github.woki.payments.adyen.client.command.Cancel;
import com.github.woki.payments.adyen.client.command.CancelOrRefund;
import com.github.woki.payments.adyen.client.command.Capture;
import com.github.woki.payments.adyen.client.command.Command;
import com.github.woki.payments.adyen.client.command.Context;
import com.github.woki.payments.adyen.client.command.Refund;
import com.github.woki.payments.adyen.client.model.YAMLRequest;
import org.apache.commons.cli.Options;
import org.boon.Maps;

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
        return options;
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
        APService service;
        switch (request.getType()) {
            case "authorization":
                service = APService.AUTHORISATION;
                break;
            case "authorization3d":
                service = APService.AUTHORISATION_3D;
                break;
            case "cancel":
                service = APService.CANCEL;
                break;
            case "cancelOrRefund":
                service = APService.CANCEL_OR_REFUND;
                break;
            case "refund":
                service = APService.REFUND;
                break;
            case "capture":
                service = APService.CAPTURE;
                break;
            default:
                throw new IllegalArgumentException("Invalid request type: " + request.getType());
        }
        return Client
                .services(Maps.map(service, request.getServiceUrl()))
                .credentials(request.getCredentials().getUsername(), request.getCredentials().getPassword())
                .extraParameters(request.getExtraParams())
                .build();
    }
}
