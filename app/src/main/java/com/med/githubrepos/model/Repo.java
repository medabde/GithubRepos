package com.med.githubrepos.model;

import com.google.gson.annotations.SerializedName;

public class Repo {

    private String name;

    private String description;

    @SerializedName("stargazers_count")
    private int stars;

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
