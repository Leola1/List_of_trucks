package com.test.testrxjavaretpofitroom.Transport;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_table")
    public class Truck {

        @PrimaryKey(autoGenerate = true)
        private int id;
    private Integer idTruck;
    private String company;

    public Truck(String company, Integer idTruck) {
        this.company = company;
        this.idTruck = idTruck;
    }

  public Integer getIdTruck() {
        System.out.println("ПРОВЕРКА  Truck getIdTruck " +idTruck);
        return idTruck;
    }

    public void setIdTruck(Integer idTruck) {
        this.idTruck = idTruck;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
