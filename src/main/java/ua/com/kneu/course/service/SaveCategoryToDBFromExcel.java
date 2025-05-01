package ua.com.kneu.course.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ua.com.kneu.course.entity.Categories;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class SaveCategoryToDBFromExcel {

    public List<Categories> saveListCategoryToDbFromExcel(InputStream is) {
        List<Categories> categories = new ArrayList<>();
        try (Workbook wb = new HSSFWorkbook(is)) {
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> ri = sheet.iterator();
            while (ri.hasNext()) {
                Row row = ri.next();
                String name = row.getCell(0).getStringCellValue();
                String description = row.getCell(1).getStringCellValue();
                String image = row.getCell(2).getStringCellValue();
//                BigDecimal price = new BigDecimal(row.getCell(3).getNumericCellValue());

                categories.add(new Categories(name, description, image));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }


    public List<Categories> saveListCategoryToDbFromExcel2(InputStream is) {
        List<Categories> categories = new ArrayList<>();

        try (XSSFWorkbook wb = new XSSFWorkbook(is)) {
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> ri = sheet.rowIterator();

            while (ri.hasNext()) {
                XSSFRow row = (XSSFRow) ri.next();

                String name = row.getCell(0).getStringCellValue();
                String description = row.getCell(1).getStringCellValue();
                String image = row.getCell(2).getStringCellValue();
//                BigDecimal price = new BigDecimal(row.getCell(3).getNumericCellValue());
//                Date date = row.getCell(3).getDateCellValue();

//                System.out.println(date);
//                Date d = new Date();
//                System.out.println(d);
                categories.add(new Categories(name, description, image));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file", e);
        }

        return categories;
    }

}