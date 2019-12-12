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
  //
  //  @Override
  //  public int getCount() {
  //    return restaurants.size();
  //  }
  //
  //  @Override
  //  public Restaurant getItem(int i) {
  //    return this.restaurants.get(i);
  //  }
  //
  //  @Override
  //  public long getItemId(int i) {
  //    return i;
  //  }
  //
  //  @TargetApi(Build.VERSION_CODES.KITKAT)
  //  @Override
  //  public View getView(final int i, View view, ViewGroup viewGroup) {
  //    if (view == null) {
  //      view = LayoutInflater.from(c).inflate(R.layout.restaurant_list, viewGroup, false);
  //    }
  //
  //    ImageView restaurantImage = view.findViewById(R.id.restaurantImage);
  //    TextView restaurantName = view.findViewById(R.id.restaurantName);
  //    TextView restaurantDescription = view.findViewById(R.id.restaurantDescription);
  //    TextView restaurantStatus = view.findViewById(R.id.restaurantStatus);
  //
  //    final Restaurant restaurant = this.restaurants.get(i);
  //
  //    Picasso.get().load(restaurant.getCover_img_url()).into(restaurantImage);
  //    restaurantName.setText(restaurant.getName());
  //    restaurantDescription.setText(restaurant.getDescription());
  //    restaurantStatus.setText(restaurant.getStatus());
  //
  //    view.setOnClickListener(new View.OnClickListener() {
  //      @Override
  //      public void onClick(View view) {
  //        Toast.makeText(c, "You clicked on " + restaurant.getName(), Toast.LENGTH_SHORT).show();
  //      }
  //    });
  //
  //    return view;
  //  }
}
