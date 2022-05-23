package org.penta.work.boostrap.port.outgoing;

import org.penta.work.boostrap.port.model.Account;

import java.util.List;

public interface AccountReader {
    List<Account> findAll();
}