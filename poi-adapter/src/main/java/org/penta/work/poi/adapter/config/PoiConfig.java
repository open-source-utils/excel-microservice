package org.penta.work.poi.adapter.config;

import org.penta.work.boostrap.port.universal.ExcelPort;
import org.penta.work.poi.adapter.service.AccountExcelExporter;
import org.springframework.context.annotation.Bean;
public class PoiConfig {
    @Bean
    public ExcelPort getAccountExcelExporter() {
        return new AccountExcelExporter();
    }

}
