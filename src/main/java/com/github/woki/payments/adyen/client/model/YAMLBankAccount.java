package com.github.woki.payments.adyen.client.model;

import com.github.woki.payments.adyen.model.BankAccount;
import com.github.woki.payments.adyen.model.BankAccountBuilder;

/**
 * Created by Willian Oki on 9/28/15.
 */
public class YAMLBankAccount {
    private String number;
    private String bankLocationId;
    private String bankName;
    private String bic;
    private String countryCode;
    private String iban;
    private String ownerName;
    private String bankCity;
    private String taxId;

    public BankAccount toBankAccount() {
        return BankAccountBuilder
                .accountNumber(number)
                .locationId(bankLocationId)
                .bankName(bankName)
                .bankCity(bankCity)
                .bic(bic)
                .countryCode(countryCode)
                .iban(iban)
                .owner(ownerName)
                .taxId(taxId)
                .build();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBankLocationId() {
        return bankLocationId;
    }

    public void setBankLocationId(String bankLocationId) {
        this.bankLocationId = bankLocationId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }
}
