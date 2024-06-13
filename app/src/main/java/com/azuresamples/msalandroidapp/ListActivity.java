package com.azuresamples.msalandroidapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azuresamples.msalandroidapp.network.ApiClient;
import com.azuresamples.msalandroidapp.network.ApiService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import model.FilesItem;
import model.FilesResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity implements ListAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private TextView tvName;
    private TextView tvUrl;
    private Stack<String> nameRoute;
    private ListAdapter myAdapter;
    private FilesResponseModel itemList;
    private FilesResponseModel currentFileList;
    private Stack<FilesResponseModel> navigationStack;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.recyclerView);
        tvName = findViewById(R.id.tvNameItem);
        tvUrl = findViewById(R.id.tvUrlItem);
        nameRoute = new Stack<>();
        nameRoute.push("root");
        getSupportActionBar().setTitle(nameRoute.peek());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        navigationStack = new Stack<>();
        apiService = ApiClient.getClient().create(ApiService.class);

        SharedPreferences sharedPref = getBaseContext().getSharedPreferences(getString(R.string.saved_key), Context.MODE_PRIVATE);
        String token = sharedPref.getString(getString(R.string.saved_key), null);
        Log.i("SingleAccountMode", token);
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        loadFiles("root");
    }

    private void getList() {
        Log.i("SingleAccountMode", "se presiona");

    }

    @Override
    public void onItemClick(FilesItem item) {
        navigationStack.push(currentFileList);
        nameRoute.push(item.name);
        getSupportActionBar().setTitle(nameRoute.peek());
        if(item.folder != null){
            loadFiles(item.getId());
        } else {
            recyclerView.setVisibility(View.GONE);
            tvName.setVisibility(View.VISIBLE);
            tvUrl.setVisibility(View.VISIBLE);
            tvName.setText(item.name);
            tvUrl.setText(item.webUrl);

        }
    }

    @Override
    public void onBackPressed() {
        if (!navigationStack.isEmpty()) {
            recyclerView.setVisibility(View.VISIBLE);
            tvName.setVisibility(View.GONE);
            tvUrl.setVisibility(View.GONE);
            nameRoute.pop();
            getSupportActionBar().setTitle(nameRoute.peek());
            currentFileList = navigationStack.pop();
            myAdapter.updateFileList(currentFileList);

        } else {
            super.onBackPressed();
        }
    }

    private void loadFiles(String directoryId) {
        SharedPreferences sharedPref = getBaseContext().getSharedPreferences(getString(R.string.saved_key), Context.MODE_PRIVATE);
        String token = sharedPref.getString(getString(R.string.saved_key), null);
        Call<FilesResponseModel> call = apiService.GET_FOLDER_CHILDREN(token, directoryId);
        call.enqueue(new Callback<FilesResponseModel>() {
            @Override
            public void onResponse(Call<FilesResponseModel> call, Response<FilesResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    currentFileList = response.body();
                    myAdapter = new ListAdapter(currentFileList, ListActivity.this);
                    recyclerView.setAdapter(myAdapter);
                } else {
                    Log.e("Retrofit", "code: " + response.code());
                    Log.e("Retrofit", "message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<FilesResponseModel> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

