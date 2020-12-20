package com.test.testrxjavaretpofitroom;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface  TruckApiService {

   @GET("https://comixcinema.com/android/base.json")
    Observable<TruckResponse> getTrucks();
   @PUT("https://comixcinema.com/android/base.json/{company}")
  // Call<UpdateDataTruck> updateTruck(@Path("company") String company, @Body UpdateDataTruck truck);
    Call<UpdateDataTruck> updateTruck(@Path("company") String company);
    @PUT("https://comixcinema.com/android/base.json/{idTex}")
    Call<UpdateDataTex> updateTex(@Path("idTex") String id, @Body UpdateDataTex tex);
}
