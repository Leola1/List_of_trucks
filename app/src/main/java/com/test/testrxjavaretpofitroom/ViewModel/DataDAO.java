package com.test.testrxjavaretpofitroom.ViewModel;

import com.test.testrxjavaretpofitroom.Transport.Tex;
import com.test.testrxjavaretpofitroom.Transport.Truck;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


@Dao
public interface DataDAO {

        @Insert
        void insertData(Truck truck);

        @Insert
        void insertTex(Tex tex);

        @Query("DELETE FROM favorite_table WHERE company = :pokemonName")
        void deleteTruck(String pokemonName);

        @Query("DELETE FROM tex_table WHERE description = :pokemonName")
        void deleteTex(String pokemonName);

        @Query("SELECT * FROM favorite_table")
       LiveData<List<Truck>> getFavoriteTruck();
        @Query("SELECT * FROM tex_table")
        LiveData<List<Tex>> getFavoriteTex();
}
