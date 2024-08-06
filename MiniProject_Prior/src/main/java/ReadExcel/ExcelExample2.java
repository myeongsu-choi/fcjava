package ReadExcel;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelExample2 {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream(new File("example.xlsx"));
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                for(Cell cell : row) {
                    switch (cell.getCellType()) {

                        // 셀의 내용이 숫자 타입일 때
                        case NUMERIC:
                            // 셀의 내용이 날짜 형식일 때
                            if(DateUtil.isCellDateFormatted(cell)) {
                                Date dateValue = cell.getDateCellValue();
                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                String formattedDate = dateFormat.format(dateValue);
                                System.out.print(formattedDate + "\t");
                            }
                            // 셀의 내용이 숫자 형식일 때
                            else {
                                double numericValue = cell.getNumericCellValue();

                                // 셀의 내용이 정수일 때
                                if(numericValue == Math.floor(numericValue)) {
                                    int intValue = (int)numericValue;
                                    System.out.print(intValue + "\t");
                                }
                                else {
                                    System.out.print(numericValue + "\t");
                                }
                            }
                            break;

                        // 셀의 내용이 문자열 타입일 때
                        case STRING:
                            String stringValue = cell.getStringCellValue();
                            System.out.print(stringValue + "\t");
                            break;

                         // 셀의 내용이 부울 타입일 때
                        case BOOLEAN:
                            boolean booleanValue = cell.getBooleanCellValue();
                            System.out.print(booleanValue + "\t");
                            break;

                         // 셀의 내용이 수식 타입일 때
                        case FORMULA:
                            String formulaValue = cell.getCellFormula();
                            System.out.print(formulaValue + "\t");
                            break;

                         // 셀의 내용이 비었을 때
                        case BLANK:
                            System.out.print("\t");
                            break;
                        
                        // 그 외
                        default:
                            System.out.print("\t");
                            break;
                    }
                }
                System.out.println();
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
