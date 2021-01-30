package com.bookbin.coconeobank.Retrofit;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitInterface {
    @GET("v6/39c856a4e220a9d20689ea15/latest/{currency}")
    Call<JsonObject> getExchangeCurrency(@Path("currency") String currency);
}
