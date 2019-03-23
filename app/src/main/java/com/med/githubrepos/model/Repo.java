package com.med.githubrepos.model;

import com.google.gson.annotations.SerializedName;

public class Repo {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("stargazers_count")
    private int stars;

    @SerializedName("owner")
    private User owner;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStars() {
        return stars;
    }

    public User getOwner() {
        return owner;
    }

}
