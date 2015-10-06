package com.github.woki.payments.adyen.client.model;

import com.github.woki.payments.adyen.model.ContractType;
import com.github.woki.payments.adyen.model.Recurring;

/**
 * Created by Willian Oki on 10/6/15.
 */
@SuppressWarnings("unused")
public class YAMLRecurring {
    public enum Contract {
        ONECLICK, RECURRING, PAYOUT
    }
    private Contract contract;
    private String recurringDetailName;

    public Recurring toRecurring() {
        return new Recurring(ContractType.valueOf(contract.name()), recurringDetailName);
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getRecurringDetailName() {
        return recurringDetailName;
    }

    public void setRecurringDetailName(String recurringDetailName) {
        this.recurringDetailName = recurringDetailName;
    }
}
