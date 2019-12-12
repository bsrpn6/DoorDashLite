package me.brandonray.doordashlite.services;

import io.reactivex.Observable;
import java.util.ArrayList;
import me.brandonray.doordashlite.models.Restaurant;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DoorDashApi {
  @GET("/v2/restaurant/")
  Observable<ArrayList<Restaurant>> getRestaurants(
      @Query("lat") double lat,
      @Query("lng") double lng,
      @Query("offset") int offset,
      @Query("limit") int limit);
}
