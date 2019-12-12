package me.brandonray.doordashlite;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.ArrayList;
import me.brandonray.doordashlite.adapters.RestaurantAdapter;
import me.brandonray.doordashlite.models.Restaurant;
import me.brandonray.doordashlite.ui.MainPresenter;
import me.brandonray.doordashlite.ui.MainViewInterface;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

  String TAG = "MainActivity";

  MainPresenter mainPresenter;
  SwipeRefreshLayout pullToRefresh;
  RecyclerView recyclerView;
  ArrayList<Restaurant> restaurants = new ArrayList<>();
  RestaurantAdapter restaurantListAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ((DoorDashLiteApplication) getApplication())
        .getApplicationComponent()
        .inject(MainActivity.this);

    createPullToRefreshLayout();
    createListView();
    setupMVP();
  }

  @Override
  protected void onResume() {
    super.onResume();
    getData();
  }

  public void getData() {
    pullToRefresh.setRefreshing(true);
    mainPresenter.getRestaurants();
  }

  private void setupMVP() {
    mainPresenter = new MainPresenter(this);
  }

  private void createPullToRefreshLayout() {
    pullToRefresh = findViewById(R.id.swipeRefresh);
    pullToRefresh.setOnRefreshListener(
        new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
            getData();
          }
        });
  }

  public void createListView() {
    restaurantListAdapter = new RestaurantAdapter(getApplicationContext(), restaurants);
    recyclerView = findViewById(R.id.listRestaurants);
    recyclerView.setAdapter(restaurantListAdapter);
  }

  @Override
  public void showToast(String s) {
    Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
  }

  @Override
  public void refreshRestaurantList(ArrayList<Restaurant> restaurantArrayList) {
    restaurants.clear();
    restaurants.addAll(restaurantArrayList);
    pullToRefresh.setRefreshing(false);
    restaurantListAdapter.notifyDataSetChanged();
  }

  @Override
  public void displayError(String s) {
    showToast(s);
  }
}
