package me.brandonray.doordashlite;

import android.app.Application;
import me.brandonray.doordashlite.dagger.ApplicationComponent;
import me.brandonray.doordashlite.dagger.ApplicationModule;
import me.brandonray.doordashlite.dagger.DaggerApplicationComponent;

public class DoorDashLiteApplication extends Application {

  private ApplicationComponent applicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    applicationComponent = createApplicationComponent();
  }

  ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }

  private ApplicationComponent createApplicationComponent() {
    return DaggerApplicationComponent.builder().applicationModule(new ApplicationModule()).build();
  }
}
