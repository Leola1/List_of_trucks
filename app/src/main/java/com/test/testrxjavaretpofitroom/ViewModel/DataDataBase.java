package com.test.testrxjavaretpofitroom.ViewModel;

import com.test.testrxjavaretpofitroom.Transport.Tex;
import com.test.testrxjavaretpofitroom.Transport.Truck;
import com.test.testrxjavaretpofitroom.ViewModel.DataDAO;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Truck.class, Tex.class},version = 1)
public abstract class DataDataBase  extends RoomDatabase {

        public abstract DataDAO dataDao();

}
