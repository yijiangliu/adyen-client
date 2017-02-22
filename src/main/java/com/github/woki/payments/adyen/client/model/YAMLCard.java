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
    private YAMLAddress billingAddress;
    private int issueNumber;
    private int startMonth;
    private int startYear;

    public Card toCard() {
        Card card = CardBuilder.number(number).cvc(String.valueOf(cvc)).expiry(expiryYear, expiryMonth).holder(holder).build();
        if (billingAddress != null) {
            card.setBillingAddress(billingAddress.toAddress());
        }
        if (issueNumber > 0) {
            card.setIssueNumber(issueNumber);
        }
        if (startMonth > 0) {
            card.setStartMonth(startMonth);
        }
        if (startYear > 0) {
            card.setStartYear(startYear);
        }
        return card;
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

    public YAMLAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(YAMLAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }
}
