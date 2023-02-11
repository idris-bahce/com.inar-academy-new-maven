package dataDriven;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class Excell {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\idris\\Desktop\\NewMaven\\ExcelSamples\\Id-Password.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet");

        for (int rows = 0; rows < sheet.getPhysicalNumberOfRows(); rows++) {
            Row eachRow = sheet.getRow(rows);
            for (int columns = 0; columns < eachRow.getLastCellNum(); columns++) {
                Iterator<Cell> cell = eachRow.cellIterator();
                if (eachRow.getCell(columns).getStringCellValue().equalsIgnoreCase("blocked user")) {
                    cell.next();
                    while (cell.hasNext()){
                        System.out.println(cell.next());
                    }
                }
            }
            System.out.println();
        }
    }
}
