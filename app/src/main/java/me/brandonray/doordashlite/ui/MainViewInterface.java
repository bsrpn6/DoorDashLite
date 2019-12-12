package me.brandonray.doordashlite.ui;

import java.util.ArrayList;
import me.brandonray.doordashlite.models.Restaurant;

public interface MainViewInterface {

  void showToast(String s);

  void refreshRestaurantList(ArrayList<Restaurant> restaurants);

  void displayError(String s);
}
