package org.penta.work.boostrap.port.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class Account {

    private Long id;

    private Long accountNo;

    private String name;

    private BigDecimal accountBalance;

    public boolean withdrawAmount(BigDecimal withdrawalAmount) {
        if (accountBalance.compareTo(withdrawalAmount) < 0) {
            return false;
        }
        accountBalance = accountBalance.subtract(withdrawalAmount);
        return true;
    }

    public void depositAmount(BigDecimal depositAmount) {
        accountBalance = accountBalance.add(depositAmount);
    }
}