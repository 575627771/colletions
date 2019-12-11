package com.xiaohong.wu.collections.http;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * http 请求的封装  基于连接池
 * https://square.github.io/okhttp/
 * @author Wolf.2
 * @version 1.0
 * @date 2019/11/11 14:29
 **/
public class HttpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final MediaType APPLICATION_JSON_UTF8 =  MediaType.parse("application/json;charset=UTF-8");

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build();

    /**
     * 同步 get请求
     *
     * @param url 请求地址
     * @return 响应数据
     * @throws IOException 异常
     */
    public static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try (Response execute = client.newCall(request).execute()) {
            return Objects.requireNonNull(execute.body()).string();
        }
    }

    /**
     * 异步 get 请求
     *
     * @param url 地址
     */
    public static void asynGet(String url) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody body = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("");
                    }
                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }
                    System.out.println(body.string());
                }

            }
        });
    }


    /**
     * 同步 post http 请求
     *
     * @param url   地址
     * @param param 请求参数
     * @return 响应
     * @throws IOException 异常
     */
    public static String post(String url, Map<String, String> param,MediaType mediaType) throws IOException {
        RequestBody requestBody = RequestBody.create(JSON.toJSONString(param), mediaType);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        }
    }

    /**
     * 异步 http post 请求
     * @param url 地址
     * @param param 参数
     */
    public static void asynPost(String url,Map<String,String> param,MediaType mediaType){
        RequestBody body = RequestBody.create(JSON.toJSONString(param),mediaType);
        Request request = new Request.Builder().url(url).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //可以进行一个失败重试
            }
        });
    }




    public static void main(String[] args) throws IOException {

        String response = HttpUtil.get("https://raw.github.com/square/okhttp/master/README.md");
        System.out.println(response);
    }
}
