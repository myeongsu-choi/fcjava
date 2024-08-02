import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherExample {
    /*
        목표 : HttpURLConnection API로 Open API를 접속해서 날씨 정보를 가져오기

        OpenWeatherMap API를 사용하여 현재 날씨 정보를 가져오는 예제를 진행하려면 OpenWeatherMap API에 가입하여 API 키를 발급받아야 한다.

        HttpURLConnection은 URL Connection의 하위 클래스로, HTTP 프로토콜을 사용하여 특정 웹 서버와 통신하기 위한
        클래스이며 HttpURLConnection은 HTTP 메서드(예: GET, POST, PUT, DELETE 등)을 지원하며, HTTP 요청과 응답을
        처리할 수 있는 메서드들을 제공한다.
     */
    public static void main(String[] args) {
        String apiKey = "dabfedfd60f8a29b96b58f5170d57c3c";
        String city = "Seoul";
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

        try {
            // URL 객체를 생성하고 HTTP 연결을 연다.
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            // HTTP GET 요청 방식을 설정하고, 서버에 JSON 형식의 응답을 요청한다.
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            // 서버로부터의 응답 코드를 가져온다. '200'은 요청이 성공적으로 처리되었음을 의미한다.
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            /*
                getInputStream()
                    HttpURLConnection의 메서드로, 서버에서 받은 응답 본문을 읽을 수 있는 'InputStream'을 반환한다.
                    이 스트림은 바이트 단위로 데이터를 읽는다.

                InputStreamReader
                    바이트 스트림을 문자 스트림으로 변환하는 클래스이다.
                    InputStream은 데이터를 바이트 단위로 처리하는 반면, InputStreamReader는 데이터를 문자 단위로 처리한다.

                BufferReader
                    문자 스트림을 버퍼링하여 성능을 개선하고, 데이터를 더 효율적으로 읽을 수 있도록 도와준다.
                    버퍼링이란 데이터를 메모리에 임시 저장하여 일괄적으로 읽거나 쓰는 방법이다.
             */
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // in에 저장된 데이터를 한 줄씩 읽어와서 전체 데이터를 하나의 문자열로 결합한다.
            String inputLine;
            StringBuilder content = new StringBuilder();
            while((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            /*
                Gson
                    자바에서 JSON 데이터를 다루는 방법은 여러 가지가 있지만, 일반적으로 라이브러리를 사용해서
                    JSON을 쉽게 다를 수 있다. 대표적인 라이브러리는 Gson이 있다.

                    Gson을 사용하기 위해서는 다음과 같은 의존성을 추가해야 한다.
                    <dependency>
                        <groupId>com.google.code.gson</groupId>
                        <artifactId>gson</artifactId>
                        <version>2.8.9</version>
                    </dependency>
             */

            /*
                1. StringBuilder 객체인 content를 toString으로 문자열로 변환한다.
                2. JsonParser는 Gson 라이브러리에서 제공하는 클래스이다. parseString 메서드는 JSON 문자열을 읽어들여
                    JsonElement 객체로 변환한다.
                3. getAsJsonObject()는 JsonElement를 JsonObject로 변환한다.
             */
            JsonObject weatherData = JsonParser.parseString(content.toString()).getAsJsonObject();

            // JSON 객체에서 "main"이라는 키를 가진 하위 JSON 객체를 추출한다.
            JsonObject mainData = weatherData.getAsJsonObject("main");
            // mainData JSON 객체에서 temp라는 이름의 속성을 추출한다.
            double temp = mainData.get("temp").getAsDouble();

            System.out.println("Seoul's temperature: " + temp + "℃");

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
