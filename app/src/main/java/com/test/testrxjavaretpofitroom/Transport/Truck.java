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
      /*  private String name;

        private String url;

        public Truck(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }*/

}
