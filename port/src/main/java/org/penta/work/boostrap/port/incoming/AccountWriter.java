package org.penta.work.boostrap.port.incoming;

import org.penta.work.boostrap.port.model.Account;

import java.util.List;

public interface AccountWriter {
    boolean addAccount(Account account);
}