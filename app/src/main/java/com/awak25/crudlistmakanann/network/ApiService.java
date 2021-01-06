package com.awak25.crudlistmakanann.network;

import com.awak25.crudlistmakanann.model.ResponseMakanan;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("Api/getMakanan")
    Call<ResponseMakanan> getAllMakanan();
}
