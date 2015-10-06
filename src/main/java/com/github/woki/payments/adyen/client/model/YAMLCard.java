package com.github.woki.payments.adyen.client.model;

import com.github.woki.payments.adyen.model.Card;
import com.github.woki.payments.adyen.model.CardBuilder;

/**
 * Created by Willian Oki on 9/23/15.
 */
public class YAMLCard {
    private String number;
    private int cvc;
    private String holder;
    private int expiryMonth;
    private int expiryYear;

    public Card toCard() {
        return CardBuilder.number(number).cvc(String.valueOf(cvc)).expiry(expiryYear, expiryMonth).holder(holder).build();
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }
}
