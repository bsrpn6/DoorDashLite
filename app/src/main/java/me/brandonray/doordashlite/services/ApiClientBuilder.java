package me.brandonray.doordashlite.services;

import com.google.gson.GsonBuilder;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ApiClientBuilder {
  private static final String BASE_URL = "https://api.doordash.com/";

  private static Retrofit getClient() {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    httpClient.addInterceptor(logging);

    httpClient.addInterceptor(
        new Interceptor() {
          @Override
          public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            Request request =
                original
                    .newBuilder()
                    .header("Content-Type", "multipart/form-data")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
          }
        });

    return new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
        .client(httpClient.build())
        .build();
  }

  static ApiClient getMGClient() {

    return getClient().create(ApiClient.class);
  }
}
