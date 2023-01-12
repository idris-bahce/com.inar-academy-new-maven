package FrameWork;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven {
    public ArrayList<String> getData(String dataName) throws IOException {
        ArrayList<String> data = new ArrayList<String>();
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\idris\\Desktop\\NewMaven\\ExcelSamples\\Book1.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("demo")){
                XSSFSheet sheet = workbook.getSheetAt(i);

                Iterator<Row> rows = sheet.iterator();
                Row firstRow = rows.next();
                Iterator<Cell> cell = firstRow.cellIterator();
                int k = 0;
                int column = 0;
                while (cell.hasNext()){
                    Cell value = cell.next();
                    if (value.getStringCellValue().equalsIgnoreCase("test cases")){
                        column = k;
                    }
                    k++;
                }

                while (rows.hasNext()){
                    Row row = rows.next();
                    if (row.getCell(column).getStringCellValue().equalsIgnoreCase(dataName)){
                        Iterator<Cell> cv = row.cellIterator();
                        while (cv.hasNext()){
                            data.add(cv.next().getStringCellValue());
                        }
                    }
                }
            }

        }
        return data;
    }
}
