package com.awak25.crudlistmakanann.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.60.132/crudListMakanan/index.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiService service = retrofit.create(ApiService.class);
}
