package com.volkodav4ik;

/*1) Программно послать HEAD запрос для файла доступного по прямой ссылке, например:
        https://dl.dropboxusercontent.com/s/vxnydq4xjkmefrp/CLUVAL.java
        Скачать сначала первую половину этого файла, а потом докачать вторую.
        Range:bytes=0-500*/

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.*;
import java.util.Objects;

public class HW18T1 {
    private static final String URL = "https://dl.dropboxusercontent.com/s/vxnydq4xjkmefrp/CLUVAL.java";
    private static final String HEADER_NAME_OF_LENGTH = "Content-Length";
    private static final String FILE_NAME = "fileFromDropBox.txt";

    public static void main(String[] args) throws IOException {
        int countOfSymbols = getContentLength();
        int halfOfFile = countOfSymbols / 2;
        File file = new File(FILE_NAME);
        writeTextFromUrlToFile(getInfoFromFile(0, halfOfFile), file);
        writeTextFromUrlToFile(getInfoFromFile(halfOfFile + 1, countOfSymbols), file);
    }

    private static void writeTextFromUrlToFile(String text, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(text);
        } catch (IOException e) {
            System.out.println("File " + FILE_NAME + " didn't found");
        }
    }

    private static String getInfoFromFile(int begin, int end) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(URL)
                .method("GET", null)
                .addHeader("Range", "bytes=" + begin + "-" + end)
                .build();
        return client.newCall(request).execute().body().string();
    }

    private static int getContentLength() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(URL)
                .method("HEAD", null)
                .build();
        return Integer.parseInt(Objects.requireNonNull(client.newCall(request).execute().header(HEADER_NAME_OF_LENGTH)));
    }
}
