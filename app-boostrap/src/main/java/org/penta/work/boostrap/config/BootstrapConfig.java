package org.penta.work.boostrap.config;

import org.penta.work.boostrap.domain.AccountDomain;
import org.penta.work.boostrap.port.RequestAccounts;
import org.penta.work.boostrap.port.incoming.AccountWriter;
import org.penta.work.boostrap.port.outgoing.AccountReader;
import org.penta.work.boostrap.port.outgoing.ExcelPort;
import org.penta.work.jpa.config.JpaConfig;
import org.penta.work.poi.adapter.config.PoiConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({JpaConfig.class, PoiConfig.class})
public class BootstrapConfig {
    @Bean
    public RequestAccounts getRequestAccounts( AccountReader accountReader, AccountWriter accountWriter, ExcelPort excelPort) {
        return new AccountDomain(accountReader, accountWriter, excelPort);
    }

}