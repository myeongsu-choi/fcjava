package PetCare.Model;

/*
    DTO/VO Model -> 데이터를 담는 모델

    MedicalRecord 클래스는 반려동물의 의료 기록에 대한 정보를 저장한다.
 */
public class MedicalRecord {
    /*
        고객의 전화번호, 의료 기록의 날짜, 의료 기록의 내용을 저장한다.
     */
    private String phoneNumber;
    private String date;
    private String content;

    public MedicalRecord() {
    }

    public MedicalRecord(String phoneNumber, String date, String content) {
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.content = content;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
