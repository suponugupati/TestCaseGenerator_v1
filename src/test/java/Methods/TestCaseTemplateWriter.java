package Methods;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static Methods.GlobalData.*;

public class TestCaseTemplateWriter {
    public void modifyTestCaseTemplate() throws IOException {


        try (FileInputStream fis = new FileInputStream(templateFilePath);
             Workbook templateWorkbook = WorkbookFactory.create(fis);
             Workbook modifiedWorkbook = new XSSFWorkbook()) {

            // Open the first sheet of the template workbook
            Sheet templateSheet = templateWorkbook.getSheetAt(0);

            // Create a new sheet in the modified workbook
            Sheet modifiedSheet = modifiedWorkbook.createSheet();

            // Copy the content from the template sheet to the modified sheet
            copySheet(templateSheet, modifiedSheet);

            // Modify the desired cell with new content
            Row row = modifiedSheet.getRow(1);
            if (row != null) {
                Cell cell = row.getCell(0);
                if (cell != null) {
                    cell.setCellValue("New test case content");
                }
            }

            // Set the sheet name
            String timestamp = String.valueOf(System.currentTimeMillis());
            String sheetName = userStory + "_" + timestamp;
            modifiedWorkbook.setSheetName(0, sheetName);

            // Create a new file name with user story name and timestamp
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String newFileName = userStory + "_" + LocalDateTime.now().format(formatter) + ".xlsx";

            // Save the modified workbook to a new file
            try (FileOutputStream fos = new FileOutputStream(newFileName)) {
                modifiedWorkbook.write(fos);
            }

            System.out.println("Modified test case template saved successfully!");


        } catch (IOException e) {
                     e.printStackTrace();
        }
    }

    public void copySheet(Sheet sourceSheet, Sheet targetSheet) {
        for (Row sourceRow : sourceSheet) {
            Row targetRow = targetSheet.createRow(sourceRow.getRowNum());
            for (Cell sourceCell : sourceRow) {
                Cell targetCell = targetRow.createCell(sourceCell.getColumnIndex());
                targetCell.setCellValue(sourceCell.getStringCellValue());
            }
        }
    }
}
