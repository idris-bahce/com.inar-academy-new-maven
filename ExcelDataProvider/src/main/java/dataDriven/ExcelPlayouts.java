package dataDriven;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelPlayouts {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\idris\\Desktop\\NewMaven\\ExcelSamples\\Id-Password.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet");
        int indexOfPassword = 0;

        for (int row = 0; row < sheet.getPhysicalNumberOfRows(); row++) {
            Row eachRow = sheet.getRow(row);
            for (int column = 0; column < eachRow.getLastCellNum(); column++) {
                if (eachRow.getCell(column).getStringCellValue().equalsIgnoreCase("password")){
                    indexOfPassword = column;
                }
            }
            System.out.println(eachRow.getCell(indexOfPassword));
        }
    }
}
