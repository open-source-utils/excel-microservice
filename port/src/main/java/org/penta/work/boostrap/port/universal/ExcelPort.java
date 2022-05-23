package org.penta.work.boostrap.port.universal;

import org.penta.work.boostrap.port.model.Account;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

public interface ExcelPort {
    void exportAccountsToExcel(List<Account> listAccounts, ServletOutputStream outputStream) throws IOException;
}
