package org.penta.work.jpa.read;

import org.penta.work.boostrap.port.model.Account;
import org.penta.work.boostrap.port.outgoing.AccountReader;
import org.penta.work.jpa.repository.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;

public class AccountReadService implements AccountReader {
    private final AccountRepository repository;

    public AccountReadService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = repository.findAll().stream()
                 .map(a ->  Account.builder().id(a.getId()).name(a.getName()).accountNo(a.getAccountNo()).accountBalance(a.getAccountBalance()).build())
                .collect(Collectors.toList());
        return accounts;
    }
}
