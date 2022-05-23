package org.penta.work.boostrap.domain;

import lombok.Builder;
import static org.penta.work.boostrap.domain.utils.NumberUtil.generateAccountNumber;
import org.penta.work.boostrap.port.RequestAccounts;
import org.penta.work.boostrap.port.incoming.AccountWriter;
import org.penta.work.boostrap.port.model.Account;
import org.penta.work.boostrap.port.outgoing.AccountReader;
import org.penta.work.boostrap.port.universal.ExcelPort;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

@Builder
public class AccountDomain implements RequestAccounts {

    private AccountReader accountReader;
    private AccountWriter accountWriter;
    private ExcelPort excelPort;

    public AccountDomain(AccountReader accountReader, AccountWriter accountWriter, ExcelPort excelPort) {
        this.accountReader = accountReader;
        this.accountWriter = accountWriter;
        this.excelPort = excelPort;

    }

    @Override
    public List<Account> getActiveAccounts() {
        return accountReader.findAll();
    }

    @Override
    public boolean addAccount(Account account) {
        account.setAccountNo(generateAccountNumber());
        return accountWriter.addAccount(account);
    }

    @Override
    public void exportAccountsToExcel(ServletOutputStream outputStream) {
        List<Account> accounts = accountReader.findAll();
        try {
            excelPort.exportAccountsToExcel(accounts, outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
