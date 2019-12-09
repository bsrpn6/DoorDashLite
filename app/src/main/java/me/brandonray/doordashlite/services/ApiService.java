package me.brandonray.doordashlite.services;

import java.util.ArrayList;
import me.brandonray.doordashlite.models.Restaurant;
import retrofit2.Call;
import retrofit2.Callback;

public class ApiService {

  public void getRestaurantList(Callback<ArrayList<Restaurant>> callback) {

    ApiClient service = ApiClientBuilder.getMGClient();

    Call<ArrayList<Restaurant>> result = service.getRestaurants(37.422740, -122.139956, 0, 50);

    result.enqueue(callback);
  }
}
