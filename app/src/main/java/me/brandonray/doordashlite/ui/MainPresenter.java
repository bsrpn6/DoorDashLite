package me.brandonray.doordashlite.ui;

import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import me.brandonray.doordashlite.models.Restaurant;
import me.brandonray.doordashlite.services.DoorDashClient;

public class MainPresenter implements MainPresenterInterface {

  MainViewInterface mainViewInterface;
  private String TAG = "MainPresenter";

  public MainPresenter(MainViewInterface mainViewInterface) {
    this.mainViewInterface = mainViewInterface;
  }

  @Override
  public void getRestaurants() {
    getRestaurantsObservable().subscribeWith(getRestaurantObserver());
  }

  public Observable<ArrayList<Restaurant>> getRestaurantsObservable() {
    return DoorDashClient.getDoorDashApi()
        .getRestaurants(37.422740, -122.139956, 0, 50)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  private Observer<ArrayList<Restaurant>> getRestaurantObserver() {
    return new Observer<ArrayList<Restaurant>>() {
      @Override
      public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe");
      }

      @Override
      public void onNext(ArrayList<Restaurant> restaurants) {
        Log.d(TAG, "onNext");
        mainViewInterface.refreshRestaurantList(restaurants);
      }

      @Override
      public void onError(Throwable e) {
        Log.d(TAG, "onError");
        mainViewInterface.displayError("Error loading restaurants.");
      }

      @Override
      public void onComplete() {
        Log.d(TAG, "onComplete");
      }
    };
  }
}
