package Kakao_REST_OpenAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    목표 : 카카오 REST API를 사용하여 주소를 입력 받아 해당 주소의 위도와 경도 정보를 출력하는 자바 미니 프로젝트를 개발한다.

    요구사항 >>
        1. 카카오 개발자 계정을 생성하고(https://developers.kakao.com), 새 어플리케이션을 만들어 앱 키를 발급받는다.
        2. Maven을 사용하는 자바 프로젝트를 생성하고, ApacheHttpClient와 Google Gson 라이브러리를 추가한다.
        3. 주소를 입력 받아 위도와 경도를 반환하는I 클 getAddressCoordinate 메소드를 포함하는 KakaoAPI 클래스를 작성한다.
        4. 키보드로부터 주소를 입력 받아 getAddressCoordinate 메소드를 호출하고 결과를 출력하는 메인 클래스를 작성한다.

   ApacheHttpClient와 Google Gson 라이브러리를 추가한다.
   <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpclient</artifactId>
        <version>4.5.13</version>
   </dependency>
   <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.6</version>
   </dependency>
 */

public class KakaoMapMain {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("주소를 입력하세요:");
            String address = reader.readLine();

            double[] coordinates = KakaoAPI.getAddressCoordinate(address);

            if(coordinates != null) {
                System.out.println("주소 : " + address);
                System.out.println("위도 : " + coordinates[0]);
                System.out.println("경도 : " + coordinates[1]);
            }
            else {
                System.out.println("주소를 찾을 수 없습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
