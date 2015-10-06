package com.github.woki.payments.adyen.client.model;

import java.util.Currency;
import com.github.woki.payments.adyen.model.Amount;

/**
 * Created by Willian Oki on 9/23/15.
 */
public class YAMLAmount {
    private String currencyCode;
    private long value;

    public Amount toAmount() {
        try {
            return new Amount(Currency.getInstance(currencyCode), value);
        } catch (NullPointerException | IllegalArgumentException e) {
            return new Amount(Currency.getInstance("USD"), 0L);
        }
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
