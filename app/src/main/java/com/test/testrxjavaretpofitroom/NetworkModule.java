package com.test.testrxjavaretpofitroom;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
@Module
@InstallIn(ApplicationComponent.class)
public class NetworkModule {
    @Provides
    @Singleton
    public static TruckApiService provideApiService(){

        return  new Retrofit.Builder()
               // .baseUrl(" https://pokeapi.co/api/v2/")

                .baseUrl("https://comixcinema.com/android/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(TruckApiService.class);
    }
}
