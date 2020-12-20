package com.test.testrxjavaretpofitroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//@Database(entities = {Truck.class},version = 2,exportSchema = false)
@Database(entities = {Truck.class,Tex.class},version = 1)
public abstract class DataDataBase  extends RoomDatabase {

        public abstract DataDAO dataDao();

}
