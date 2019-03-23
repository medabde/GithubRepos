package com.med.githubrepos.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReposResponse {

    @SerializedName("items")
    private List<Repo> repos;

    public List<Repo> getRepos() {
        return repos;
    }

    public void setRepos(List<Repo> repos) {
        this.repos = repos;
    }
}
