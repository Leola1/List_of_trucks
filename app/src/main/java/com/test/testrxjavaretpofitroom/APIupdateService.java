package com.test.testrxjavaretpofitroom;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIupdateService {

        private static Retrofit retrofit = null;
        public static TruckApiService getTransport() {

            if (retrofit==null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://comixcinema.com/android/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

            TruckApiService api = retrofit.create(TruckApiService.class);
            return api;
        }


}
