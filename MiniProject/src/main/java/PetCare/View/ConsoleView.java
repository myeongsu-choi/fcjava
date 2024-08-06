package PetCare.View;

import PetCare.Model.Customer;
import PetCare.Model.MedicalRecord;

import java.util.List;
import java.util.Scanner;

/*
    콘솔 기반의 사용자 인터페이스를 구현한 ConsoleView 클래스이다.
    사용자로부터 입력을 받고, 메시지를 출력하는 역할을 한다.
 */
public class ConsoleView {
    private Scanner scanner = new Scanner(System.in);

    // 신규 고객 정보를 입력 받아 Customer 객체를 생성하고 반환한다.
    public Customer getCustomerInfo() {
        System.out.println("신규 고객 정보를 입력하세요.");
        System.out.print("전화번호 : ");
        String phoneNumber = scanner.nextLine();

        System.out.print("소유주 이름 : ");
        String ownerName = scanner.nextLine();

        System.out.print("동물 이름 : ");
        String petName = scanner.nextLine();

        System.out.print("주소 : ");
        String address = scanner.nextLine();

        System.out.print("종류 : ");
        String species = scanner.nextLine();

        System.out.print("출생년도(yyyy) : ");
        int birthYear = scanner.nextInt();
        scanner.nextLine();

        return new Customer(phoneNumber, ownerName, petName, address, species, birthYear);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public String getPhoneNumber() {
        System.out.print("전화번호를 입력하세요 : ");
        return scanner.nextLine();
    }

    public MedicalRecord getMedicalRecordInfo() {
        System.out.print("진료일을 입력하세요 : ");
        String date = scanner.nextLine();

        System.out.print("진료내용을 입력하세요 : ");
        String content = scanner.nextLine();

        return new MedicalRecord(null, date, content);
    }

    public void printMedicalRecordInfo(Customer customer) {
        List<MedicalRecord> records = customer.getMedicalRecords();
        System.out.println("[" + customer.getPetName() + "]의 진료기록");
        for(MedicalRecord record : records) {
            System.out.println("    진료일: " + record.getDate());
            System.out.println("    진료내용: " + record.getContent());
            System.out.println("    소유주 이름: " + customer.getOwnerName());
            System.out.println("    동물 이름: " + customer.getPetName());
            System.out.println("    주소: " + customer.getAddress());
            System.out.println("    종류: " + customer.getSpecies());
            System.out.println("    출생년도: " + customer.getBirthYear());
        }
    }
}
