package org.penta.work.boostrap.rest.api;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.penta.work.boostrap.port.RequestAccounts;
import org.penta.work.boostrap.port.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private RequestAccounts requestAccounts;

    public AccountController(RequestAccounts requestAccounts) {
        this.requestAccounts = requestAccounts;
    }

    @GetMapping(value = "/active")
    ResponseEntity activeAccounts() {
        List<Account> accounts = requestAccounts.getActiveAccounts();
        return new ResponseEntity<>(accounts,
                HttpStatus.OK);
    }


    @GetMapping(value = "/add")
    ResponseEntity add() {
        Account account = Account.builder().name("Shakti").accountNo(500L).accountBalance(BigDecimal.valueOf(111L)).build();
        boolean result = requestAccounts.addAccount(account);
        return new ResponseEntity<>(result,
                HttpStatus.OK);
    }

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=accounts_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        ServletOutputStream outputStream = response.getOutputStream();
        requestAccounts.exportAccountsToExcel(outputStream);
    }

}
