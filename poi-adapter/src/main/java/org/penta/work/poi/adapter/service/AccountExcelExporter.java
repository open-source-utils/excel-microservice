package org.penta.work.poi.adapter.service;

import java.io.IOException;
import java.util.List;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.penta.work.boostrap.port.model.Account;
import org.penta.work.boostrap.port.outgoing.ExcelPort;

public class AccountExcelExporter implements ExcelPort {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Account> listAccounts;
     
//    public AccountExcelExporter(List<Account> listAccounts) {
//        this.listAccounts = listAccounts;
//        workbook = new XSSFWorkbook();
//    }
//
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Accounts");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "Account ID", style);      
        createCell(row, 1, "Account No.", style);       
        createCell(row, 2, "Name", style);    
        createCell(row, 3, "Account Balance", style);
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Account account : listAccounts) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++,  String.valueOf(account.getId()), style);
            createCell(row, columnCount++, String.valueOf(account.getAccountNo()), style);
            createCell(row, columnCount++, account.getName(), style);
            createCell(row, columnCount++,  String.valueOf(account.getAccountBalance().toString()), style);
             
        }
    }

    @Override
    public void exportAccountsToExcel(List<Account> listAccounts, ServletOutputStream outputStream) throws IOException {
        this.listAccounts = listAccounts;
        this.workbook = new XSSFWorkbook();
        writeHeaderLine();
        writeDataLines();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }


}