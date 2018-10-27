package com.jonasvieira.iddog.Data.Feed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Feed {
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("list")
    @Expose
    private List<String> list = null;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
