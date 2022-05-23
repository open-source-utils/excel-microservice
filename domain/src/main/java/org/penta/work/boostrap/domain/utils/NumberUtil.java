package org.penta.work.boostrap.domain.utils;

import java.util.UUID;

public class NumberUtil {
    public static String generateAccountNumber(){
        return UUID.randomUUID().toString();
    }
}
