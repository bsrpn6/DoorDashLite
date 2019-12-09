package me.brandonray.doordashlite.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import me.brandonray.doordashlite.R;
import me.brandonray.doordashlite.models.Restaurant;

public class RestaurantListAdapter extends BaseAdapter {

  Context c;
  ArrayList<Restaurant> restaurants;

  public RestaurantListAdapter(Context c, ArrayList<Restaurant> restaurants) {
    this.c = c;
    this.restaurants = restaurants;
  }

  @Override
  public int getCount() {
    return restaurants.size();
  }

  @Override
  public Restaurant getItem(int i) {
    return this.restaurants.get(i);
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

  @TargetApi(Build.VERSION_CODES.KITKAT)
  @Override
  public View getView(final int i, View view, ViewGroup viewGroup) {
    if (view == null) {
      view = LayoutInflater.from(c).inflate(R.layout.restaurant_list, viewGroup, false);
    }

    ImageView restaurantImage = view.findViewById(R.id.restaurantImage);
    TextView restaurantName = view.findViewById(R.id.restaurantName);
    TextView restaurantDescription = view.findViewById(R.id.restaurantDescription);
    TextView restaurantStatus = view.findViewById(R.id.restaurantStatus);

    Restaurant restaurant = this.restaurants.get(i);

    Picasso.get().load(restaurant.getCover_img_url()).into(restaurantImage);
    restaurantName.setText(restaurant.getName());
    restaurantDescription.setText(restaurant.getDescription());
    restaurantStatus.setText(restaurant.getStatus());

    return view;
  }
}
