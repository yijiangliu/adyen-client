package com.github.woki.payments.adyen.client.command;

/**
 * Created by Willian Oki on 9/24/15.
 */
public interface Command {
    Context getContext();
    void execute();
}
