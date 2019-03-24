package com.med.githubrepos.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.med.githubrepos.EndlessRecyclerViewScrollListener;
import com.med.githubrepos.R;
import com.med.githubrepos.Utils;
import com.med.githubrepos.adapter.CustomAdapter;
import com.med.githubrepos.model.Repo;
import com.med.githubrepos.model.ReposResponse;
import com.med.githubrepos.network.GetDataService;
import com.med.githubrepos.network.RetrofitClientInstance;
import java.util.Collections;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage(getString(R.string.loading_message));
        progressDoalog.show();

        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(Collections.<Repo>emptyList());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        generateData(1);

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener((LinearLayoutManager) layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                page++;
                generateData(page);
                System.out.println("hey error :"+page);
            }
        });

    }



    /*Method to generate data */
    private void generateData(int page){

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ReposResponse> call = service.getRepos(Utils.getLastMonthSearchFilter(),page);
        call.enqueue(new Callback<ReposResponse>() {

            @Override
            public void onResponse(Call<ReposResponse> call, Response<ReposResponse> response) {
                progressDoalog.dismiss();

                /*update adapter*/
                adapter.setDataList(response.body().getRepos());
                adapter.notifyDataSetChanged();

            }


            @Override
            public void onFailure(Call<ReposResponse> call, Throwable t) {
                progressDoalog.dismiss();
                System.out.println("error est : "+t.getMessage());
                Toast.makeText(MainActivity.this, getString(R.string.error_message), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
