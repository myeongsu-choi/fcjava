import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/*
    목표 : https://sum.su.or.kr:8888/bible/today 여기에 접속해서 말씀을 크롤링 해보자.

    Jsoup은 자바 라이브러리로서 HTML 문서를 읽고 파싱하여 웹 크롤링, 웹 스크레이핑, 데이터 추출 등의 작업을
    수행할 수 있다. Jsoup API를 사용하려면 HTML 문서에서 원하는 요소를 쉽게 선택하고 조작할 수 있다.

    Jsoup을 사용하기 위해서는 밑의 의존성을 pom.xml에 부여해주어야 한다.
    <dependency>
        <groupId>org.jsoup</groupId>
        <artifactId>jsoup</artifactId>
        <version>1.14.3</version>
    </dependency>
 */
public class JsoupExample {
    public static void main(String[] args) {
        String url = "https://sum.su.or.kr:8888/bible/today";

        try {
            // url에 HTTP GET 요청을 보내고, 응답으로 HTML 문서를 Document 객체로 반환한다.
            Document document = Jsoup.connect(url).get();

            // HTML 문서에서 ID가 "bible_text"인 요소와 "bibleinfo_box"인 요소를 선택하고 출력한다.
            Element bibleText = document.getElementById("bible_text");
            Element bibleInfoBox = document.getElementById("bibleinfo_box");

            System.out.println("Bible Text: " + bibleText.text());
            System.out.println("Bible Info Box: " + bibleInfoBox.text());

            // CSS 클래스가 "num"인 모든 요소와 "info"인 모든 요소를 저장하고 출력한다.
            Elements numElements = document.select(".num");
            Elements infoElements = document.select(".info");

            for(int i = 0; i < numElements.size(); i++) {
                System.out.println(numElements.get(i).text() + " : " + infoElements.get(i).text());
            }
        // 파일을 읽거나 네트워크를 통해 데이터를 주고받는 과정에서 생길 수 있는 문제를 처리한다.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
