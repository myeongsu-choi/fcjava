package ReadExcel;

import org.apache.poi.ss.usermodel.*;
import java.io.*;

/*
    목표 : 자바에서 Apache POI 라이브러리를 이용하여 Excel 파일 읽기/쓰기 실습하기

    Apache POI는 Excel 파일을 다루기 위한 자바 라이브러리이다.
    Maven을 사용하는 경우 pom.xml 파일에 다음과 같은 의존성을 추가해야한다.

    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi</artifactId>
        <version>5.2.0</version>
    </dependency>

    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.2.0</version>
    </dependency>

    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.17.1</version>
    </dependency>
 */
public class ExcelExample {
    public static void main(String[] args) {
        try {
            // FileInputStream을 이용하여 example.xlsx 파일을 연다.
            FileInputStream file = new FileInputStream(new File("example.xlsx"));

            // WorkbookFactory.create() 를 사용하여 파일을 읽고 Workbook 객체를 만든다.
            Workbook workbook = WorkbookFactory.create(file);

            // workbook 에서 첫 번째 시트를 가져온다.
            Sheet sheet = workbook.getSheetAt(0);

            // 각 행의 각 셀을 반복하여 셀의 내용을 문자열로 반환하여 출력한다.
            for(Row row : sheet) {
                for(Cell cell : row) {
                    System.out.print(cell.toString() + "\t");
                }
                System.out.println();
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 파일의 내용과 다르게 나오므로 추가적인 방봅 이용 --> ReadExcel.ExcelExample2
    }
}
