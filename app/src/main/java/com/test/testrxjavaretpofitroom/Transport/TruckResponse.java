package com.test.testrxjavaretpofitroom.Transport;

import com.test.testrxjavaretpofitroom.Transport.Tex;
import com.test.testrxjavaretpofitroom.Transport.Truck;

import java.util.ArrayList;

public class TruckResponse {

    private ArrayList<Truck> truck = null;
    private ArrayList<Tex> tex = null;
    public TruckResponse( ArrayList<Truck> truck,  ArrayList<Tex> tex) {
        this.truck = truck;
        this.tex = tex;

    }

    public ArrayList<Truck> getTruck() {
        System.out.println("ПРОВЕРКА  TruckResponse getTruck " +truck);
        return truck;
    }

    public void setTruck(ArrayList<Truck> truck) {
        this.truck = truck;
    }

    public ArrayList<Tex> getTex() {
        return tex;
    }

    public void setTex(ArrayList<Tex> tex) {
        this.tex = tex;
    }


}
