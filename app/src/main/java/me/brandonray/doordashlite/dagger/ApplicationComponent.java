package me.brandonray.doordashlite.dagger;

import dagger.Component;
import javax.inject.Singleton;
import me.brandonray.doordashlite.MainActivity;

@Singleton
@Component(modules = ApplicationModule.class)
public
interface ApplicationComponent {
    void inject(MainActivity mainActivity);
}
