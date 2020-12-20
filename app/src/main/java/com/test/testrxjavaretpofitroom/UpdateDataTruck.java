package com.test.testrxjavaretpofitroom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateDataTruck {
    @SerializedName("idTruck")
    @Expose
    private transient Integer idTruck;

    @SerializedName("company")
    @Expose
    private transient String company;

   /* public UpdateDataTruck(String company, Integer idTruck) {
        this.company = company;
        this.idTruck = idTruck;
    }*/

    public Integer getIdTruck() {
        System.out.println("ПРОВЕРКА  UpdateDataTruck getIdTruck " +idTruck);
        return idTruck;
    }

    public void setIdTruck(Integer idTruck) {
        this.idTruck = idTruck;
    }

    public String getCompany() {
        System.out.println("ПРОВЕРКА  UpdateDataTruck getCompany " +company);
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


}
