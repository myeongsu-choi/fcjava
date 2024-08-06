package CreatePDF;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

/*
    목표 : iText API를 이용하여 키보드로부터 책 정보를 입력 받아서 PDF 파일에 생성하는 실습
 */
public class PDFFile {
    public static void main(String[] args) throws MalformedURLException, IOException {
        String dest = "book_table.pdf";
        new PDFFile().createPdf(dest);
    }

    /*
        throws MalformedURLExceptio, IOException :MalformedURLException, IOException이라는 두가지 예외를 발생시킬 수 있음을 명시한다.

        MalformedURLException은 URL 형식이 올바르지 않을 때 발생하고 IOException은 입출력 작업에서 문제가 생겼을 때 발생한다.
     */
    public void createPdf(String dest) throws MalformedURLException, IOException {
        List<Map<String, String>> books = createDummyData();

        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        PdfFont headerFont = null;
        PdfFont bodyFont = null;
        try {
            headerFont = PdfFontFactory.createFont("NanumGothicExtraBold.ttf", "Identity-H", true);
            bodyFont = PdfFontFactory.createFont("NanumGothic.ttf", "Identity-H", true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // columnWidths 배열은 테이블의 각 열의 너비 비율을 정의한다. 총 6개의 열이 있고, 각 열의 너비 비율은 1, 2, 2, 2, 2, 2로 설정되어 있다.
        float[] columnWidths = {1, 2, 2, 2, 2, 2};
        // Table 객체를 생성하고 UnitValue.createPercentArray(columnWidths)를 통해 열 너비를 설정한다.
        Table table = new Table(UnitValue.createPercentArray(columnWidths));

        // Cell 객체를 생성하여 테이블의 헤더 셀을 만든다. 각 셀에는 Paragraph 객체를 추가하여 표시할 텍스트를 설정한다.
        Cell headerCell1 = new Cell().add(new Paragraph("순번")).setFont(headerFont);
        Cell headerCell2 = new Cell().add(new Paragraph("제목")).setFont(headerFont);
        Cell headerCell3 = new Cell().add(new Paragraph("저자")).setFont(headerFont);
        Cell headerCell4 = new Cell().add(new Paragraph("출판사")).setFont(headerFont);
        Cell headerCell5 = new Cell().add(new Paragraph("출판일")).setFont(headerFont);
        Cell headerCell6 = new Cell().add(new Paragraph("이미지")).setFont(headerFont);
        table.addHeaderCell(headerCell1);
        table.addHeaderCell(headerCell2);
        table.addHeaderCell(headerCell3);
        table.addHeaderCell(headerCell4);
        table.addHeaderCell(headerCell5);
        table.addHeaderCell(headerCell6);

        int rowNum = 1;
        for(Map<String, String> book : books) {
            String title = book.get("title");
            String authors = book.get("authors");
            String publisher = book.get("publisher");
            String publishedDate = book.get("publishedDate");
            String thumbnail = book.get("thumbnail");

            // 책의 순번을 담은 셀
            Cell rowNumCell = new Cell().add(new Paragraph(String.valueOf(rowNum))).setFont(bodyFont);
            table.addCell(rowNumCell);

            // 책의 제목을 담은 셀
            Cell titleCell = new Cell().add(new Paragraph(title)).setFont(bodyFont);
            table.addCell(titleCell);

            // 책의 저자를 담은 셀
            Cell authorsCell = new Cell().add(new Paragraph(authors)).setFont(bodyFont);
            table.addCell(authorsCell);

            // 책의 출판사를 담은 셀
            Cell publisherCell = new Cell().add(new Paragraph(publisher)).setFont(bodyFont);
            table.addCell(publisherCell);

            // 책의 출판일을 담은 셀
            Cell publishedDateCell = new Cell().add(new Paragraph(publishedDate)).setFont(bodyFont);
            table.addCell(publishedDateCell);

            // 책의 이미지를 담은 셀
            ImageData imageData = ImageDataFactory.create(new File(thumbnail).toURI().toURL());
            Image img = new Image(imageData);
            Cell imageCell = new Cell().add(img.setAutoScale(true));
            table.addCell(imageCell);
            rowNum++;
        }
        document.add(table);
        document.close();
    }
    private List<Map<String, String>> createDummyData() {
        List<Map<String, String>> books = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("책 개수를 입력하세요:");
        int bookCount = scanner.nextInt();
        scanner.nextLine();

        for(int i = 1; i <= bookCount; i++) {
            Map<String, String> book = new HashMap<>();

            System.out.printf("\n[ %d번째 책 정보 입력 ]\n", i);
            System.out.printf("제목: ");
            book.put("title", scanner.nextLine());

            System.out.print("저자: ");
            book.put("authors", scanner.nextLine());

            System.out.print("출판사: ");
            book.put("publisher", scanner.nextLine());

            System.out.print("출판일(YYYY-MM-DD): ");
            book.put("publishedDate", scanner.nextLine());

            System.out.print("썸네일 url: ");
            book.put("thumbnail", scanner.nextLine());

            books.add(book);
        }
        scanner.close();
        return books;
    }
}
