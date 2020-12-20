package com.test.testrxjavaretpofitroom;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
   private LiveData<List<Truck>> favoritePokemonList = null;
    private LiveData<List<Tex>> favoriteListTex = null;

    @ViewModelInject
    public DataViewModel(Repository repository) {
        this.repository = repository;
       favoritePokemonList = repository.getFavoritePokemon();
        favoriteListTex = repository.getFavoriteTex();
    }



  public MutableLiveData<ArrayList<Truck>> getTruckList() {
       return truckList;}

    public MutableLiveData<ArrayList<Tex>> getTexList() {
        return texList;
    }


  //  public static ArrayList<List> allList;
  public void getDataBaseTransport(){
    //     allList = new ArrayList();

        repository.getData()
                .subscribeOn(Schedulers.io())
                .map(new Function<TruckResponse, ArrayList<Truck>>() {
                    @Override
                    public ArrayList<Truck> apply(TruckResponse pokemonResponse) throws Throwable {
                        ArrayList<Truck> list = pokemonResponse.getTruck();
                   //     System.out.println("ПРОВЕРКА  DataViewModel getPokemon list" +list);
                        System.out.println("ПРОВЕРКА  DataViewModel getPokemon list.getCompany "
                                +list.get(0).getIdTruck());
                     /*for(Truck pokemon : list){
                            String url = pokemon.getCompany();
                            pokemon.setCompany(url); }*/

                        return list;
                    }
                })

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {truckList.setValue(result);
                          //  allViewModelTruck(result);
                },
                        error-> Log.e(TAG, "getPokemons: " + error.getMessage() ));

        repository.getData()
                .subscribeOn(Schedulers.io())

                .map(new Function<TruckResponse, ArrayList<Tex>>() {
                    @Override
                    public ArrayList<Tex> apply(TruckResponse pokemonResponse) throws Throwable {
                        ArrayList<Tex> list = pokemonResponse.getTex();
                       // System.out.println("ПРОВЕРКА  DataViewModel getPokemon list" +list);
                        System.out.println("ПРОВЕРКА  DataViewModel getPokemon list.getIdTex "
                                +list.get(0).getIdTex());

                        return list;
                    }
                })

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {texList.setValue(result);

                      //    allViewModelTex(result);
                        },
                        error-> Log.e(TAG, "getPokemons: " + error.getMessage() ));


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

    public LiveData<List<Truck>> getFavoritePokemonList() {
        return favoritePokemonList;
    }
    public LiveData<List<Tex>> getFavoriteListTex() {
        return favoriteListTex;
    }

    public void getFavoritePokemon(){
        favoritePokemonList = repository.getFavoritePokemon();
    }
    public void getFavoriteTex(){
        favoriteListTex = repository.getFavoriteTex();
    }
}
