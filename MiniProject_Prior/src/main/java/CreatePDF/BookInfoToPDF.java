package CreatePDF;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Year;
import java.util.HashMap;

/*
    목표 : iText API를 이용하여 책정보를 PDF 파일에 생성 하는 실습(책 정보는 제목, 저자, 출판사, 년도, 가격, 페이지 수로 되어있다.)

    itext API를 사용하기 위해서는 다음과 같은 의존성을 추가해야한다.

    <dependencies>
        <dependency>
            <groupId>com.itextpdf</groupId>
            <artifactId>itext7-core</artifactId>
            <version>7.1.15</version>
        </dependency>
    </dependencies>
 */
public class BookInfoToPDF {
    public static void main(String[] args) {
        HashMap<String, String> bookInfo = new HashMap<>();
        bookInfo.put("title", "한글 자바");
        bookInfo.put("author", "홍길동");
        bookInfo.put("publisher", "한글 출판사");
        bookInfo.put("year", String.valueOf(Year.now().getValue()));
        bookInfo.put("price", "25000");
        bookInfo.put("pages", "400");

        try {
            /*
                PdfWriter는 PDF 문서를 생성하고 쓰기 위한 객체이다.
                new FileOutputStream()은 PDF 파일을 저장할 파일 출력 스트림을 만든다.
             */
            PdfWriter writer = new PdfWriter(new FileOutputStream("book_information.pdf"));

            /*
                PdfDocumnet는 PDF 문서를 다루는 기본 객체이다.
                wirter를 사용하여 PDF 문서를 생성한다.
             */
            PdfDocument pdf = new PdfDocument(writer);

            // Document는 실제 PDF 문서의 내용을 추가할 수 있는 객체이다.
            Document document = new Document(pdf);

            // 폰트 설정
            PdfFont font = PdfFontFactory.createFont("NanumGothic.ttf", PdfEncodings.IDENTITY_H, true);
            document.setFont(font);

            // bookInfo 안의 모든 키를 가져와 키와 값 쌍을 Paragraph 객체로 생성하고 문서에 추가한다.
            for(String key : bookInfo.keySet()) {
                Paragraph paragraph = new Paragraph(key + " : " + bookInfo.get(key));
                document.add(paragraph);
            }
            document.close();

            System.out.println("book_information.pdf 파일이 생성되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
