package me.brandonray.doordashlite.services;

import java.util.ArrayList;
import me.brandonray.doordashlite.models.Restaurant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClient {
  @GET("/v2/restaurant/")
  Call<ArrayList<Restaurant>> getRestaurants(
      @Query("lat") double lat,
      @Query("lng") double lng,
      @Query("offset") int offset,
      @Query("limit") int limit);
}
