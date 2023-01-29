package FrameWork;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;


public class GetIdAndPassword {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\idris\\Desktop\\NewMaven\\ExcelSamples\\Id-Password.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sayfa1");
        int rowNum = sheet.getLastRowNum();

        for (int i = 0; i < rowNum; i++) {
            Row row = sheet.getRow(i);
            if (row!=null) {
                if (row.getCell(0).getStringCellValue().equalsIgnoreCase("blocked user")) {
                    for (int j = 1; j < row.getPhysicalNumberOfCells(); j++) {
                        System.out.println(row.getCell(j).getStringCellValue());
                    }
                }
            }
        }


    }
}
