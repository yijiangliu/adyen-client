package com.github.woki.payments.adyen.client.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.github.woki.payments.adyen.model.Installments;
import com.github.woki.payments.adyen.model.NameBuilder;
import com.github.woki.payments.adyen.model.PaymentRequest;
import com.github.woki.payments.adyen.model.ShopperInteraction;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Willian Oki on 9/23/15.
 */
public class YAMLPaymentRequest {
    private static final Logger LOG = LoggerFactory.getLogger(YAMLPaymentRequest.class);
    private static final String DATE_FORMAT = "yyyy/MM/dd";
    private String merchantAccount;
    private String reference;
    private YAMLAmount amount;
    private YAMLAmount additionalAmount;
    private YAMLCard card;
    private Map<String, String> additionalData = new HashMap<>();
    private Integer installments;
    private YAMLShopper shopper;
    private Integer captureDelayHours;
    private String deliveryDate;
    private String deviceFingerprint;
    private String sessionId;
    private String selectedBrand;
    private String orderReference;
    private String merchantOrderReference;
    private Integer fraudOffset;
    private Integer mcc;
    private String md;
    private String paResponse;
    private YAMLBankAccount bankAccount;
    private YAMLAddress billingAddress;
    private YAMLAddress deliveryAddress;
    private YAMLBrowserInfo browserInfo;
    private YAMLDccQuote dccQuote;
    private String selectedRecurringDetailReference;
    private YAMLMpiData mpiData;
    private YAMLRecurring recurring;

    public PaymentRequest toPaymentRequest() {
        PaymentRequest request = new PaymentRequest();
        request.setMerchantAccount(merchantAccount);
        request.setReference(reference);
        request.setDeviceFingerprint(deviceFingerprint);
        request.setSessionId(sessionId);
        request.setSelectedBrand(selectedBrand);
        request.setOrderReference(orderReference);
        request.setMerchantOrderReference(merchantOrderReference);
        request.setMd(md);
        request.setPaResponse(paResponse);
        request.setSelectedRecurringDetailReference(selectedRecurringDetailReference);
        if (recurring != null) {
            request.setRecurring(recurring.toRecurring());
        }
        if (mpiData != null) {
            request.setMpiData(mpiData.toThreeDSecureData());
        }
        if (dccQuote != null) {
            request.setDccQuote(dccQuote.toForexQuote());
        }
        if (browserInfo != null) {
            request.setBrowserInfo(browserInfo.toBrowserInfo());
        }
        if (billingAddress != null) {
            try {
                request.setBillingAddress(billingAddress.toAddress());
            } catch (IllegalArgumentException e) {
                LOG.warn("billing address discarded; invalid country-code: {}", billingAddress.getCountryCode());
            }
        }
        if (deliveryAddress != null) {
            try {
                request.setDeliveryAddress(deliveryAddress.toAddress());
            } catch (IllegalArgumentException e) {
                LOG.warn("billing address discarded; invalid country-code: {}", deliveryAddress.getCountryCode());
            }
        }
        if (bankAccount != null) {
            request.setBankAccount(bankAccount.toBankAccount());
        }
        if (mcc != null) {
            request.setMcc(mcc);
        }
        if (fraudOffset != null) {
            request.setFraudOffset(fraudOffset);
        }
        if (captureDelayHours != null) {
            request.setCaptureDelayHours(captureDelayHours);
        }
        if (installments != null) {
            request.setInstallments(new Installments(installments));
        }
        if (!additionalData.isEmpty()) {
            request.getAdditionalData().putAll(additionalData);
        }
        if (amount != null) {
            request.setAmount(amount.toAmount());
        }
        if (additionalAmount != null) {
            request.setAdditionalAmount(additionalAmount.toAmount());
        }
        if (card != null) {
            request.setCard(card.toCard());
        }
        if(StringUtils.isNotBlank(deliveryDate)) {
            DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            try {
                formatter.parse(deliveryDate);
                request.setDeliveryDate(deliveryDate);
            } catch (ParseException e) {
                LOG.warn("delivery date {} discarded; not in format YYYY/MM/DD", shopper.getBirth());
            }
        }
        if (shopper != null) {
            request.setShopperEmail(shopper.getEmail());
            request.setShopperIP(shopper.getIp());
            request.setShopperName(NameBuilder.first(shopper.getFirstName()).last(shopper.getLastName()).build());
            request.setShopperReference(shopper.getReference());
            switch (shopper.getInteraction()) {
                case "contauth":
                    request.setShopperInteraction(ShopperInteraction.ContAuth);
                    break;
                case "pos":
                    request.setShopperInteraction(ShopperInteraction.POS);
                    break;
                case "moto":
                    request.setShopperInteraction(ShopperInteraction.Moto);
                    break;
                case "ecommerce":
                    request.setShopperInteraction(ShopperInteraction.Ecommerce);
                    break;
                default:
                    LOG.warn("shopper interaction {} discarded; falling back to e-commerce", shopper.getInteraction());
                    request.setShopperInteraction(ShopperInteraction.Ecommerce);
            }
            if(StringUtils.isNotBlank(shopper.getBirth())) {
                DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
                try {
                    Date birth = formatter.parse(shopper.getBirth());
                    request.setDateOfBirth(birth);
                } catch (ParseException e) {
                    LOG.warn("shopper birth {} discarded; not in format YYYY/MM/DD", shopper.getBirth());
                }
            }
            request.setSocialSecurityNumber(shopper.getSsn());
            request.setShopperLocale(shopper.getLocale());
            request.setShopperStatement(shopper.getStatement());
        }
        return request;
    }

    public String getMerchantAccount() {
        return merchantAccount;
    }

    public void setMerchantAccount(String merchantAccount) {
        this.merchantAccount = merchantAccount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getInstallments() {
        return installments;
    }

    public void setInstallments(int installments) {
        this.installments = installments;
    }

    public YAMLAmount getAmount() {
        return amount;
    }

    public void setAmount(YAMLAmount amount) {
        this.amount = amount;
    }

    public YAMLCard getCard() {
        return card;
    }

    public void setCard(YAMLCard card) {
        this.card = card;
    }

    public Map<String, String> getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(Map<String, String> additionalData) {
        this.additionalData = additionalData;
    }

    public YAMLShopper getShopper() {
        return shopper;
    }

    public void setShopper(YAMLShopper shopper) {
        this.shopper = shopper;
    }

    public YAMLAmount getAdditionalAmount() {
        return additionalAmount;
    }

    public void setAdditionalAmount(YAMLAmount additionalAmount) {
        this.additionalAmount = additionalAmount;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public Integer getCaptureDelayHours() {
        return captureDelayHours;
    }

    public void setCaptureDelayHours(Integer captureDelayHours) {
        this.captureDelayHours = captureDelayHours;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeviceFingerprint() {
        return deviceFingerprint;
    }

    public void setDeviceFingerprint(String deviceFingerprint) {
        this.deviceFingerprint = deviceFingerprint;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSelectedBrand() {
        return selectedBrand;
    }

    public void setSelectedBrand(String selectedBrand) {
        this.selectedBrand = selectedBrand;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }

    public String getMerchantOrderReference() {
        return merchantOrderReference;
    }

    public void setMerchantOrderReference(String merchantOrderReference) {
        this.merchantOrderReference = merchantOrderReference;
    }

    public Integer getFraudOffset() {
        return fraudOffset;
    }

    public void setFraudOffset(Integer fraudOffset) {
        this.fraudOffset = fraudOffset;
    }

    public Integer getMcc() {
        return mcc;
    }

    public void setMcc(Integer mcc) {
        this.mcc = mcc;
    }

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    public String getPaResponse() {
        return paResponse;
    }

    public void setPaResponse(String paResponse) {
        this.paResponse = paResponse;
    }

    public YAMLBankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(YAMLBankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public YAMLAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(YAMLAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public YAMLAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(YAMLAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public YAMLBrowserInfo getBrowserInfo() {
        return browserInfo;
    }

    public void setBrowserInfo(YAMLBrowserInfo browserInfo) {
        this.browserInfo = browserInfo;
    }

    public YAMLDccQuote getDccQuote() {
        return dccQuote;
    }

    public void setDccQuote(YAMLDccQuote dccQuote) {
        this.dccQuote = dccQuote;
    }

    public String getSelectedRecurringDetailReference() {
        return selectedRecurringDetailReference;
    }

    public void setSelectedRecurringDetailReference(String selectedRecurringDetailReference) {
        this.selectedRecurringDetailReference = selectedRecurringDetailReference;
    }

    public YAMLMpiData getMpiData() {
        return mpiData;
    }

    public void setMpiData(YAMLMpiData mpiData) {
        this.mpiData = mpiData;
    }

    public YAMLRecurring getRecurring() {
        return recurring;
    }

    public void setRecurring(YAMLRecurring recurring) {
        this.recurring = recurring;
    }
}
