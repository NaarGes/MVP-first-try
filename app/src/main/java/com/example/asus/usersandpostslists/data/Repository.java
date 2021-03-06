package com.example.asus.usersandpostslists.data;

import com.example.asus.usersandpostslists.data.local.model.Post;
import com.example.asus.usersandpostslists.data.local.model.User;
import com.example.asus.usersandpostslists.data.remote.ApiClient;
import com.example.asus.usersandpostslists.data.remote.ApiResult;
import com.example.asus.usersandpostslists.data.remote.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// repository is used for preparing data
// it will decide to prepare online or offline

public class Repository {

    private ApiService service;


    public Repository() {

        service = ApiClient.getRetrofitInstance().create(ApiService.class);
    }

    public void getUsers(final ApiResult<List<User>> callback) {

        retrofit2.Call<List<User>> call = service.getAllUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                callback.onFail();
            }
        });
    }

    public void getPosts(final ApiResult<List<Post>> callback, int userID) {

        retrofit2.Call<List<Post>> call = service.getUserPosts(userID);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                callback.onFail();
            }
        });
    }
}
