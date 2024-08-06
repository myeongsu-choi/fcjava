package Crawling;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KakaoBookAPI {
    private static final String API_KEY = "9c780b3c307a6bbc1391e9822baca738";
    private static final String API_BASE_URL = "https://dapi.kakao.com/v3/search/book";
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    public static List<Book> searchBooks(String bookTitle) throws IOException {
        /*
            Java를 검색하게 되면
            https://dapi.kakao.com/v3/search/book?query=java 의 url을 사용하여 정보를 요청한다.

            인증을 위해 API_KEY도 함께 보낸다.
         */
        HttpUrl.Builder urlBuilder = HttpUrl.parse(API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("query", bookTitle);
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .addHeader("Authorization", "KakaoAK " + API_KEY)
                .build();

        // 요청을 실행 -> 응답 받음
        try(Response response = client.newCall(request).execute()) {
            // 응답이 성공적이지 않다면 예외를 던진다.
            if(!response.isSuccessful()) throw new IOException("Request failed : " + response);

            // 서버로부터 받은 JSON 응답을 JsonObject로 변환
            JsonObject jsonResponse = gson.fromJson(response.body().charStream(), JsonObject.class);
            // JsonObject에서 documents라는 키에 해당하는 값들을 JsonArray로 가져온다.
            JsonArray documents = jsonResponse.getAsJsonArray("documents");

            List<Book> books = new ArrayList<>();
            for(JsonElement document : documents) {
                // JsonElement를 JsonObject로 변환하고 Book 객체를 생성한다.
                JsonObject bookJson = document.getAsJsonObject();
                Book book = new Book(
                        bookJson.get("title").getAsString(),
                        bookJson.get("authors").getAsJsonArray().toString(),
                        bookJson.get("publisher").getAsString(),
                        bookJson.get("thumbnail").getAsString()
                );
                books.add(book);
            }
            return books;
        }
    }
}
