package org.penta.work.boostrap.rest.api;

import org.penta.work.boostrap.port.RequestAccounts;
import org.penta.work.boostrap.port.model.Account;
import org.penta.work.boostrap.rest.api.common.VersionedApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@VersionedApiController
public class AccountController {

    private final RequestAccounts requestAccounts;

    public AccountController(RequestAccounts requestAccounts) {
        this.requestAccounts = requestAccounts;
    }

    @GetMapping(value = "/account/active")
    ResponseEntity activeAccounts() {
        List<Account> accounts = requestAccounts.getActiveAccounts();
        return new ResponseEntity<>(accounts,
                HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    ResponseEntity add(@RequestBody Account account) {
        boolean result = requestAccounts.addAccount(account);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
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
