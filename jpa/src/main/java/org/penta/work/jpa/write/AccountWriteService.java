package org.penta.work.jpa.write;

import org.penta.work.boostrap.port.incoming.AccountWriter;
import org.penta.work.boostrap.port.model.Account;
import org.penta.work.jpa.entity.AccountEntity;
import org.penta.work.jpa.repository.AccountRepository;
import org.springframework.beans.BeanUtils;


public class AccountWriteService implements AccountWriter {
    private  AccountRepository repository;

    public AccountWriteService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean addAccount(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        BeanUtils.copyProperties(account, accountEntity);
        return repository.save(accountEntity).getId()>0;
    }
}
