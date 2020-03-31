package com.volkodav4ik;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.IOException;

public class HW18T2 {
    private static final String URL = "https://postman-echo.com/post";
    private static final String BODY = "foo1=bar1&foo2=bar2";

    public static void main(String[] args) throws IOException {
        String stringForParsingFromUrl = getBodyFromPost();
        ResponseFromPostman response = getClassFromJson(stringForParsingFromUrl);
        System.out.println(response.toString());
    }

    private static ResponseFromPostman getClassFromJson(String str) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(str, ResponseFromPostman.class);

    }

    private static String getBodyFromPost() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, BODY);
        Request request = new Request.Builder()
                .url(URL)
                .method("POST", body)
                .build();
        return client.newCall(request).execute().body().string();
    }
}
