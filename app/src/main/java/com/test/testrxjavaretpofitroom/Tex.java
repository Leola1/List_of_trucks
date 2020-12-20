package com.test.testrxjavaretpofitroom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tex_table")
public class Tex {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Integer idTex;
    private String description;

    public Tex(String description, Integer idTex) {
        this.description = description;
        this.idTex = idTex;
    }

    public Integer getIdTex() {
        System.out.println("ПРОВЕРКА  Truck getIdTruck " +idTex);

        return idTex;
    }

    public void setIdTex(Integer idTex) {
        this.idTex = idTex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
