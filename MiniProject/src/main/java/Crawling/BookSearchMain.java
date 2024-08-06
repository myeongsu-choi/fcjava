package Crawling;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/*
    목표 : 도서 검색 및 정보 PDF 생성 미니 프로젝트

    카카오 책 REST API를 이용하여 도서제목을 입력하여 도서정보를 추출한 후 PDF 파일에 도서목록 즉 제목, 저자, 출판사, 이미지를 저장하는 미니 프로젝트를 개발한다.

    요구사항 >>
        1. 카카오 책 검색 API르 사용하여 도서 정보를 검색할 수 있는 기능을 구현한다.
        2. 검색된 도서 정보를 PDF 파일로 저장하는 기능을 구현한다.
        3. 사용자로부터 검색할 도서의 제목을 입력 받아, 검색 결과를 PDF 파일로 저장한다.
        4. 프로젝트는 콘솔 환경에서 실행한다.

    구현 시 필요한 라이브러리 >>
        1. HTTP 요청 라이브러리 : OkHttp 혹은 Apache HttpClient
        2. JSON 파싱 라이브러리 : Jackson, Gson 등
        3. PDF 생성 라이브러리 : iText, Apache PDFBox 등
        
        <dependency>
            <groupId>com.itextpdf</groupId>
            <artifactId>itext7-core</artifactId>
            <version>7.1.16</version>
        </dependency>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.9</version>
        </dependency>
        <dependency>
            <groupId>com.squareup.okhttp3</groupId>
            <artifactId>okhttp</artifactId>
            <version>4.9.3</version>
        </dependency>
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>4.5.13</version>
        </dependency>
        
        다음과 같은 의존성 부여
 */
public class BookSearchMain {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("도서 제목을 입력하세요 : ");
            String bookTitle = scanner.nextLine();
            
            /*
                KakaoBookAPI 클래스의 searchBooks 메서드를 호출하여 사용자가 입력한 도서 제목으로 도서 정보를 검색
             */
            List<Book> books = KakaoBookAPI.searchBooks(bookTitle);

            if(books.isEmpty()) {
                System.out.println("검색 결과가 업습니다.");
            }
            else {
                for(Book book : books) {
                    System.out.println(book.toString());
                }
            }

            String fileName = "도서제목.pdf";
            /*
                PdfGenerator 클래스의 generateBookListPdf 메서드를 호출하여 PDF 파일 생성
             */
            PdfGenerator.generateBookListPdf(books, fileName);
            System.out.println(fileName + " 파일이 생성되었습니다.");
        } catch (IOException e) {
            System.err.println("에러가 발생했습니다 : " + e.getMessage());
        }
    }
}
