package dataDriven;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class DataTest {
    public static void main(String[] args) throws IOException {
     test();
    }
    public static void test() throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\idris\\Desktop\\NewMaven\\ExcelSamples\\Book2.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);
        int colCount = row.getLastCellNum();
        Object[][] data = new Object[rowCount - 1][colCount];
        for (int i = 0; i < rowCount - 1; i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                System.out.println(row.getCell(j));
            }
        }
    }
}
