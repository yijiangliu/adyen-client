package com.github.woki.payments.adyen.client.command;

import javax.validation.constraints.NotNull;
import com.github.woki.payments.adyen.model.ModificationRequest;
import com.github.woki.payments.adyen.model.ModificationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Refund implements Command {
    private static Logger LOG = LoggerFactory.getLogger(Refund.class);
    private Context context;

    public Refund(@NotNull Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void execute() {
        if (context.getModificationRequest() == null || context.getClient() == null) {
            throw new IllegalArgumentException("Invalid context: missing ModificationRequest and/or Client");
        }
        ModificationRequest req = context.getModificationRequest();
        ModificationResponse res = context.getClient().refund(req);
        context.setModificationResponse(res);
        LOG.info("\n>>{}\n<<{}", req, res);
    }
}
