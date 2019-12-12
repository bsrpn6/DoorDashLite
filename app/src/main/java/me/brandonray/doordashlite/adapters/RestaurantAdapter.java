package me.brandonray.doordashlite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import me.brandonray.doordashlite.R;
import me.brandonray.doordashlite.models.Restaurant;
import me.brandonray.doordashlite.viewholders.RestaurantViewHolder;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {

  Context c;
  ArrayList<Restaurant> restaurants;

  public RestaurantAdapter(Context c, ArrayList<Restaurant> restaurants) {
    this.c = c;
    this.restaurants = restaurants;
  }

  @NonNull
  @Override
  public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(c).inflate(R.layout.restaurant_list, parent, false);
    return new RestaurantViewHolder(c, view);
  }

  @Override
  public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
    Restaurant restaurant = this.restaurants.get(position);
    holder.bindRestaurant(restaurant);
  }

  @Override
  public int getItemCount() {
    return this.restaurants.size();
  }
}
