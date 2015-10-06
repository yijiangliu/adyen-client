package com.github.woki.payments.adyen.client.command;

import javax.validation.constraints.NotNull;
import com.github.woki.payments.adyen.model.PaymentRequest;
import com.github.woki.payments.adyen.model.PaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Authorization3d implements Command {
    private static Logger LOG = LoggerFactory.getLogger(Authorization3d.class);
    private Context context;

    public Authorization3d(@NotNull Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void execute() {
        if (context.getPaymentRequest() == null || context.getClient() == null) {
            throw new IllegalArgumentException("Invalid context: missing PaymentRequest and/or Client");
        }
        PaymentRequest req = context.getPaymentRequest();
        PaymentResponse res = context.getClient().authorise3ds(req);
        context.setPaymentResponse(res);
        LOG.info("\n>>{}\n<<{}", req, res);
    }
}
