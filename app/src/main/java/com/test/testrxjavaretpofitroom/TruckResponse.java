package com.test.testrxjavaretpofitroom;

import java.util.ArrayList;
import java.util.List;

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


  /*  private Integer count;
    private String next,previous;
    private ArrayList<Truck> results;

    public TruckResponse(Integer count, String next, String previous, ArrayList<Truck> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public ArrayList<Truck> getResults() {
        return results;
    }

    public void setResults(ArrayList<Truck> results) {
        this.results = results;
    }*/
}
