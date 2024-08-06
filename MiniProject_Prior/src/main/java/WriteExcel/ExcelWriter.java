package WriteExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExcelWriter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Member> members = new ArrayList<>();

        while (true) {
            System.out.print("이름을 입력하세요:");
            String name = scanner.nextLine();
            if (name.equals("quit")) {
                break;
            }

            System.out.print("나이를 입력하세요:");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("생년월일을 입력하세요:");
            String birthdate = scanner.nextLine();

            System.out.print("전화번호를 입력하세요:");
            String phone = scanner.nextLine();

            System.out.print("주소를 입력하세요:");
            String address = scanner.nextLine();

            System.out.print("결혼여부를 입력하세요 (true/false):");
            boolean isMarried = scanner.nextBoolean();
            scanner.nextLine();

            Member member = new Member(name, age, birthdate, phone, address, isMarried);
            members.add(member);
        }
        scanner.close();

        try {
            // 엑셀 워크북을 생성한다.
            XSSFWorkbook workbook = new XSSFWorkbook();

            // '회원 정보'라는 이름의 새로운 시트를 생성한다.
            Sheet sheet = workbook.createSheet("회원 정보");

            // 첫 번째 행을 생성한다. 첫 번째 행의 셀마다 값을 설정한다.
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("이름");
            headerRow.createCell(1).setCellValue("나이");
            headerRow.createCell(2).setCellValue("생년월일");
            headerRow.createCell(3).setCellValue("전화번호");
            headerRow.createCell(4).setCellValue("주소");
            headerRow.createCell(5).setCellValue("결혼여부");

            // members 리스트의 크기만큼 새로운 행을 만들고 셀마다의 값을 설정한다.
            for (int i = 0; i < members.size(); i++) {
                Member member = members.get(i);
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(member.getName());
                row.createCell(1).setCellValue(member.getAge());
                row.createCell(2).setCellValue(member.getBirthdate());
                row.createCell(3).setCellValue(member.getPhone());
                row.createCell(4).setCellValue(member.getAddress());
                Cell marriedCell = row.createCell(5);
                marriedCell.setCellValue(member.isMarried());
            }

            // 엑셀 파일을 저장한다.
            String filename = "members.xlsx";
            FileOutputStream outputStream = new FileOutputStream(new File(filename));
            workbook.write(outputStream);
            workbook.close();
            System.out.println("엑셀 파일이 저장되었습니다: " + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

