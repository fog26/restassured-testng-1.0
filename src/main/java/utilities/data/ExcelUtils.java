package utilities.data;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelUtils {
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public ExcelUtils(String path, String sheetName) {
        try {
            workbook = new XSSFWorkbook(path);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public Object getCellData(int rowNum, int colNum) {
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
    }

    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }
}