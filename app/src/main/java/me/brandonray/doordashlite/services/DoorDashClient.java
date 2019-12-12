package me.brandonray.doordashlite.services;

import com.google.gson.GsonBuilder;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DoorDashClient {
  private static final String BASE_URL = "https://api.doordash.com/";

  public static Retrofit getClient() {
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
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(httpClient.build())
        .build();
  }

  public static DoorDashApi getDoorDashApi() {

    return getClient().create(DoorDashApi.class);
  }
}
