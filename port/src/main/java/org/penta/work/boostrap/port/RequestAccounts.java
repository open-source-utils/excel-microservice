package org.penta.work.boostrap.port;

import org.penta.work.boostrap.port.model.Account;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

public interface RequestAccounts {
    List<Account> getActiveAccounts();
    boolean addAccount(Account account);
    void exportAccountsToExcel(ServletOutputStream outputStream);
}
