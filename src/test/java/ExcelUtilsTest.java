import org.testng.annotations.Test;
import utilities.data.ExcelUtils;

public class ExcelUtilsTest {

    private static final String path = "src/main/resources/ExcelData/Book1.xlsx";

    @Test
    public void test1() {
        ExcelUtils excelDocument = new ExcelUtils(path, "Sheet1");
        int num = excelDocument.getRowCount();

        for (int i = 1; i < num; i++) {
            System.out.println("fist name: " + excelDocument.getCellData(i,0));
            System.out.println("last name: " + excelDocument.getCellData(i,1));
            System.out.println("subject id: " + excelDocument.getCellData(i,2));
            System.out.println("***********************************************");
        }
    }
}
