package PetCare.Model;

import java.util.ArrayList;
import java.util.List;

/*
    DTO/VO Model -> 데이터를 담는 모델

    Customer 클래스는 고객과 고객의 반려동물에 대한 정보를 저장한다.
 */
public class Customer {
    /*
        고객의 전화번호, 이름, 반려동물의 이름, 주소, 반려동물의 종, 반려동물의 출생년도, 반려동물의 진료기록을 저장한다.
     */
    private String phoneNumber;
    private String ownerName;
    private String petName;
    private String address;
    private String species;
    private int birthYear;
    private List<MedicalRecord> medicalRecords;

    public Customer() {
    }

    public Customer(String phoneNumber, String ownerName, String petName, String address, String species, int birthYear) {
        this.phoneNumber = phoneNumber;
        this.ownerName = ownerName;
        this.petName = petName;
        this.address = address;
        this.species = species;
        this.birthYear = birthYear;
        this.medicalRecords = new ArrayList<>();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void addMedicalRecords(MedicalRecord records) {
        medicalRecords.add(records);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", petName='" + petName + '\'' +
                ", address='" + address + '\'' +
                ", species='" + species + '\'' +
                ", birthYear=" + birthYear +
                ", medicalRecords=" + medicalRecords +
                '}';
    }
}
