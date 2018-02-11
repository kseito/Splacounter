package kztproject.jp.splacounter.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kztproject.jp.splacounter.BuildConfig;
import kztproject.jp.splacounter.api.MiniatureGardenService;
import kztproject.jp.splacounter.api.TodoistService;
import kztproject.jp.splacounter.database.AppDatabase;
import kztproject.jp.splacounter.database.RewardDao;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    MiniatureGardenService provideMiniatureGardenService() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.MINIATURE_GARDEN_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MiniatureGardenService.class);
    }

    @Provides
    @Singleton
    TodoistService provideTodoistService() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.TODOIST_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TodoistService.class);
    }

    @Provides
    @Singleton
    AppDatabase providesAppDatabase() {
        return Room.databaseBuilder(context, AppDatabase.class, "splacounter")
                .build();
    }

    @Provides
    @Singleton
    RewardDao providesRewardDao(AppDatabase appDatabase) {
        return appDatabase.rewardDao();
    }
}
