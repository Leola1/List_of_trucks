package com.test.testrxjavaretpofitroom;


import android.app.Application;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

    @Module
    @InstallIn(ApplicationComponent.class)
    public class DataBaseModul {

        @Provides
        @Singleton
        public static DataDataBase provideDB(Application application){
            return Room.databaseBuilder(application,DataDataBase.class,"Favorite Database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()

                    .build();
        }

        @Provides
        @Singleton
        public static DataDAO providePokeDao(DataDataBase truckDB){
            return truckDB.dataDao();
        }


}
