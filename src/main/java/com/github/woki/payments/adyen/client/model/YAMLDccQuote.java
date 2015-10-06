package com.github.woki.payments.adyen.client.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.github.woki.payments.adyen.model.ForexQuote;
import com.github.woki.payments.adyen.model.ForexQuoteBuilder;

/**
 * Created by Willian Oki on 9/28/15.
 */
public class YAMLDccQuote {
    private static final String TIMESTAMP_FORMAT = "yyyy/MM/dd HH:mm:ssZ";
    private String account;
    private String accountType;
    private YAMLAmount baseAmount;
    private Integer basePoints;
    private YAMLAmount buy;
    private YAMLAmount interbank;
    private String reference;
    private YAMLAmount sell;
    private String signature;
    private String forexSource;
    private String forexType;
    private String validTill;

    public ForexQuote toForexQuote() {
        Date date;
        DateFormat formatter = new SimpleDateFormat(TIMESTAMP_FORMAT);
        try {
            date = formatter.parse(validTill);
        } catch (ParseException e) {
            date = new Date();
        }
        return ForexQuoteBuilder
                .base(forexType, reference, basePoints)
                .validTill(date)
                .account(account, accountType)
                .amounts(baseAmount.toAmount(), interbank.toAmount(), buy.toAmount(), sell.toAmount())
                .source(forexSource)
                .signature(signature)
                .build();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public YAMLAmount getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(YAMLAmount baseAmount) {
        this.baseAmount = baseAmount;
    }

    public Integer getBasePoints() {
        return basePoints;
    }

    public void setBasePoints(Integer basePoints) {
        this.basePoints = basePoints;
    }

    public YAMLAmount getBuy() {
        return buy;
    }

    public void setBuy(YAMLAmount buy) {
        this.buy = buy;
    }

    public YAMLAmount getInterbank() {
        return interbank;
    }

    public void setInterbank(YAMLAmount interbank) {
        this.interbank = interbank;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public YAMLAmount getSell() {
        return sell;
    }

    public void setSell(YAMLAmount sell) {
        this.sell = sell;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getForexSource() {
        return forexSource;
    }

    public void setForexSource(String forexSource) {
        this.forexSource = forexSource;
    }

    public String getForexType() {
        return forexType;
    }

    public void setForexType(String forexType) {
        this.forexType = forexType;
    }

    public String getValidTill() {
        return validTill;
    }

    public void setValidTill(String validTill) {
        this.validTill = validTill;
    }
}
