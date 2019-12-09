package me.brandonray.doordashlite;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import me.brandonray.doordashlite.adapters.RestaurantListAdapter;
import me.brandonray.doordashlite.models.Restaurant;
import me.brandonray.doordashlite.services.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  ListView listView;
  String TAG = "MainActivity";
  ArrayList<Restaurant> restaurants = new ArrayList<>();
  RestaurantListAdapter restaurantListAdapter;
  ProgressBar progressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    progressBar = findViewById(R.id.progressBar);
    getdata();
  }

  public void getdata() {
    // TODO - Extract this logic out of MainActivity and add subscriber method
    try {
      new ApiService()
          .getRestaurantList(
              new Callback<ArrayList<Restaurant>>() {
                @Override
                public void onResponse(
                    Call<ArrayList<Restaurant>> call, Response<ArrayList<Restaurant>> response) {
                  Log.d(TAG, "onResponse: response..." + response);
                  restaurants = response.body();
                  progressBar.setVisibility(View.GONE);
                  createListView();
                }

                @Override
                public void onFailure(Call<ArrayList<Restaurant>> call, Throwable t) {
                  // TODO - Add logic for when service call fails to let user know that application
                  // launch unsuccessful
                  Toast.makeText(
                          MainActivity.this,
                          "Something went wrong...Error message: " + t.getMessage(),
                          Toast.LENGTH_LONG)
                      .show();
                  Log.d(TAG, "onFailure: response...");
                }
              });

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void createListView() {
    restaurantListAdapter = new RestaurantListAdapter(getApplicationContext(), restaurants);
    listView = findViewById(R.id.listRestaurants);
    listView.setAdapter(restaurantListAdapter);
  }
}
