package com.med.githubrepos.network;

import com.med.githubrepos.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("/search/repositories?q=created:>2017-10-22&sort=stars&order=desc")
    Call<List<Repo>> getRepos();
}
