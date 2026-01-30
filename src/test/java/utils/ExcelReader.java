package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static Object[][] read(String resourcePath, String sheetName) {
        try (InputStream is = ExcelReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) throw new RuntimeException("Excel not found: " + resourcePath);

            Workbook wb = new XSSFWorkbook(is);
            Sheet sheet = wb.getSheet(sheetName);
            if (sheet == null) throw new RuntimeException("Sheet not found: " + sheetName);

            int lastRow = sheet.getLastRowNum();
            if (lastRow < 1) throw new RuntimeException("No data in sheet: " + sheetName);

            Row header = sheet.getRow(0);
            int cols = header.getLastCellNum();

            List<Object[]> rows = new ArrayList<>();
            for (int r = 1; r <= lastRow; r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;

                Object[] data = new Object[cols];
                for (int c = 0; c < cols; c++) {
                    Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    data[c] = asString(cell);
                }
                rows.add(data);
            }

            Object[][] out = new Object[rows.size()][];
            for (int i = 0; i < rows.size(); i++) out[i] = rows.get(i);
            return out;

        } catch (Exception e) {
            throw new RuntimeException("Excel read failed: " + e.getMessage(), e);
        }
    }

    private static String asString(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }
}
