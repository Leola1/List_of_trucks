package com.test.testrxjavaretpofitroom.Transport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateDataTex {
    @SerializedName("idTex")
    @Expose
    private transient int idTex;

    @SerializedName("description")
    @Expose
    private transient String description;

    public UpdateDataTex(String description, Integer idTex) {
        this.description = description;
        this.idTex = idTex;
    }
    public UpdateDataTex(String description) {
        this.description = description;

    }
    public Integer getIdTex() {
        System.out.println("ПРОВЕРКА  UpdateDataTex getIdTex " +idTex);

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


}
