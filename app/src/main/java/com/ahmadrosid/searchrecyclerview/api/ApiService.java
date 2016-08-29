package com.ahmadrosid.searchrecyclerview.api;

import com.ahmadrosid.searchrecyclerview.model.InputItem;
import com.ahmadrosid.searchrecyclerview.model.ItemHotels;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ocittwo on 30/08/16.
 */
public interface ApiService {

    @POST("search/hotel")
    Call<List<ItemHotels>> searchHotel(@Body InputItem item);
}
