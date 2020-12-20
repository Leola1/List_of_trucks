package com.test.testrxjavaretpofitroom;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import io.reactivex.rxjava3.core.Observable;

public class Repository {

    private DataDAO dataDao;
    private TruckApiService apiService;

    @Inject
    public Repository(DataDAO dataDao, TruckApiService apiService) {
        this.dataDao = dataDao;
        this.apiService = apiService;
    }


    public Observable<TruckResponse> getData(){
        return apiService.getTrucks();
    }

    public void insertTruck(Truck truck){
        System.out.println("ПРОВЕРКА  Repository insert truck" +truck);
        dataDao.insertData(truck);
    }
    public void insertTex(Tex tex){
        System.out.println("ПРОВЕРКА  Repository insert tex" +tex);
        dataDao.insertTex(tex);
    }
    public void deleteTruck(String dataName){

        System.out.println("ПРОВЕРКА  Repository delete Truck" +dataName);
        dataDao.deleteTruck(dataName);
    }

    public void deleteTex(String dataName){
        System.out.println("ПРОВЕРКА  Repository deleteTex tex" +dataName);
        dataDao.deleteTex(dataName);
    }

  public LiveData<List<Truck>> getFavoritePokemon(){
      System.out.println("ПРОВЕРКА  Repository LiveData Truck" );
      return dataDao.getFavoritePokemons();
   }
    public LiveData<List<Tex>> getFavoriteTex(){
        System.out.println("ПРОВЕРКА  Repository LiveData Tex" );
        return dataDao.getFavoriteTex();}
}
