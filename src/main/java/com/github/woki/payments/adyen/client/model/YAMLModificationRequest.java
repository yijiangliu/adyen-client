package com.github.woki.payments.adyen.client.model;

import java.util.HashMap;
import java.util.Map;
import com.github.woki.payments.adyen.model.ModificationRequest;

/**
 * Created by Willian Oki on 10/6/15.
 */
public class YAMLModificationRequest {
    private Map<String, String> additionalData = new HashMap<>();
    private String authorisationCode;
    private String merchantAccount;
    private YAMLAmount modificationAmount;
    private String originalReference;
    private String reference;

    public ModificationRequest toModificationRequest() {
        ModificationRequest request = new ModificationRequest();
        request.setAuthorisationCode(authorisationCode);
        request.setMerchantAccount(merchantAccount);
        request.setOriginalReference(originalReference);
        request.setReference(reference);
        if (modificationAmount != null) {
            request.setModificationAmount(modificationAmount.toAmount());
        }
        if (!additionalData.isEmpty()) {
            request.getAdditionalData().putAll(additionalData);
        }
        return request;
    }

    public Map<String, String> getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(Map<String, String> additionalData) {
        this.additionalData = additionalData;
    }

    public String getAuthorisationCode() {
        return authorisationCode;
    }

    public void setAuthorisationCode(String authorisationCode) {
        this.authorisationCode = authorisationCode;
    }

    public String getMerchantAccount() {
        return merchantAccount;
    }

    public void setMerchantAccount(String merchantAccount) {
        this.merchantAccount = merchantAccount;
    }

    public YAMLAmount getModificationAmount() {
        return modificationAmount;
    }

    public void setModificationAmount(YAMLAmount modificationAmount) {
        this.modificationAmount = modificationAmount;
    }

    public String getOriginalReference() {
        return originalReference;
    }

    public void setOriginalReference(String originalReference) {
        this.originalReference = originalReference;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
