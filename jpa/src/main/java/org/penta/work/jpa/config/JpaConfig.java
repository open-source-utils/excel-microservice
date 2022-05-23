package org.penta.work.jpa.config;

import org.penta.work.boostrap.port.incoming.AccountWriter;
import org.penta.work.boostrap.port.outgoing.AccountReader;
import org.penta.work.jpa.read.AccountReadService;
import org.penta.work.jpa.repository.AccountRepository;
import org.penta.work.jpa.write.AccountWriteService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("org.penta.work.jpa.entity")
@EnableJpaRepositories("org.penta.work.jpa.repository")
public class JpaConfig {
    @Bean
    public AccountReader getAccountReadService(AccountRepository repository) {
        return new AccountReadService(repository);
    }
    @Bean
    public AccountWriter getAccountWriteService(AccountRepository repository) {
        return new AccountWriteService(repository);
    }

}
