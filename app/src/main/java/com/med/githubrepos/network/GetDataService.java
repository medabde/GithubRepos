package com.med.githubrepos.network;


import com.med.githubrepos.model.ReposResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("/search/repositories?q=created:>2017-10-22&sort=stars&order=desc")
    Call<ReposResponse> getRepos(@Query("q") String filter,@Query("page") int page);
}
