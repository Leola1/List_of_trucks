package com.test.testrxjavaretpofitroom.ViewModel;

import android.util.Log;

import com.test.testrxjavaretpofitroom.Transport.Tex;
import com.test.testrxjavaretpofitroom.Transport.Truck;
import com.test.testrxjavaretpofitroom.Transport.TruckResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DataViewModel extends ViewModel {
    private static final String TAG = "PokemonViewModel";

    private Repository repository;
    private MutableLiveData<ArrayList<Truck>> truckList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Tex>> texList = new MutableLiveData<>();
    private MutableLiveData<Collection> all = new MutableLiveData<>();
   private LiveData<List<Truck>> favoriteTruckList = null;
    private LiveData<List<Tex>> favoriteListTex = null;

    @ViewModelInject
    public DataViewModel(Repository repository) {
        this.repository = repository;
       favoriteTruckList = repository.getFavoriteTruck();
        favoriteListTex = repository.getFavoriteTex();
    }



  public MutableLiveData<ArrayList<Truck>> getTruckList() {
       return truckList;}

    public MutableLiveData<ArrayList<Tex>> getTexList() {
        return texList;
    }



  public void getDataBaseTransport(){

        repository.getData()
                .subscribeOn(Schedulers.io())
                .map(new Function<TruckResponse, ArrayList<Truck>>() {
                    @Override
                    public ArrayList<Truck> apply(TruckResponse truckResponse) throws Throwable {
                        ArrayList<Truck> list = truckResponse.getTruck();
                   //     System.out.println("ПРОВЕРКА  DataViewModel get list" +list);
                        System.out.println("ПРОВЕРКА  DataViewModel get list.getCompany "
                                +list.get(0).getIdTruck());

                        return list;
                    }
                })

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {truckList.setValue(result);

                },
                        error-> Log.e(TAG, "Error getTruck: " + error.getMessage() ));

        repository.getData()
                .subscribeOn(Schedulers.io())

                .map(new Function<TruckResponse, ArrayList<Tex>>() {
                    @Override
                    public ArrayList<Tex> apply(TruckResponse texResponse) throws Throwable {
                        ArrayList<Tex> list = texResponse.getTex();
                       // System.out.println("ПРОВЕРКА  DataViewModel get list" +list);
                        System.out.println("ПРОВЕРКА  DataViewModel get list.getIdTex "
                                +list.get(0).getIdTex());

                        return list;
                    }
                })

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {texList.setValue(result);

                      //    allViewModelTex(result);
                        },
                        error-> Log.e(TAG, "getTruck: " + error.getMessage() ));


    }

//действия после загрузки
    public void insertTruck(Truck truck){
        System.out.println("ПРОВЕРКА DataViewModel, insertTruck " +truck);
        repository.insertTruck(truck);
    }
    public void deleteTruck(String dataName){
        repository.deleteTruck(dataName);
    }
    public void insertTex(Tex tex){
        System.out.println("ПРОВЕРКА DataViewModel, insertTex " +tex);
        repository.insertTex(tex);
    }
    public void deleteTex(String dataName){
        repository.deleteTex(dataName);
    }

    public LiveData<List<Truck>> getFavoriteList() {
        return favoriteTruckList;
    }
    public LiveData<List<Tex>> getFavoriteListTex() {
        return favoriteListTex;
    }

    public void getFavoriteTruck(){
        favoriteTruckList = repository.getFavoriteTruck();
    }
    public void getFavoriteTex(){
        favoriteListTex = repository.getFavoriteTex();
    }
}
