package org.penta.work.jpa.utils;

import org.penta.work.boostrap.port.model.Account;

import java.math.BigDecimal;
import java.util.Map;

import static java.util.Map.entry;

public class DataUtil {
    private static DataUtil single_instance = null;
    public Map<Long, Account> accounts = Map.ofEntries(
            entry(1L, Account.builder().accountBalance(BigDecimal.valueOf(100000L)).accountNo(1L).build()),
            entry(2L, Account.builder().accountBalance(BigDecimal.valueOf(100L)).accountNo(1L).build())
    );

    private DataUtil() {
    }

    public static DataUtil getInstance() {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new DataUtil();
        }
        return single_instance;
    }
}
