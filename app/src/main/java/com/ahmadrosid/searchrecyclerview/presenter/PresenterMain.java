package com.ahmadrosid.searchrecyclerview.presenter;

import android.content.Context;
import android.view.View;

import com.ahmadrosid.searchrecyclerview.R;
import com.ahmadrosid.searchrecyclerview.adapter.RvAdapter;
import com.ahmadrosid.searchrecyclerview.api.ApiBuilder;
import com.ahmadrosid.searchrecyclerview.api.ApiService;
import com.ahmadrosid.searchrecyclerview.model.InputItem;
import com.ahmadrosid.searchrecyclerview.model.ItemHotels;
import com.ahmadrosid.searchrecyclerview.viewholder.ItemHotelHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ocittwo on 30/08/16.
 */
public class PresenterMain {

    private final MainView mainView;
    private final Context context;
    private ArrayList<ItemHotels> models;
    private RvAdapter<ItemHotels, ItemHotelHolder> adapter;

    public PresenterMain(Context context, MainView mainView) {
        this.context = context;
        this.mainView = mainView;
    }

    /**
     * Prosessing wuery and store data to recyclerview
     * @param query
     */
    public void loadData(String query) {
        mainView.setVisibilityProgressBar(View.VISIBLE);
        ApiService service = ApiBuilder.call();
        service.searchHotel(new InputItem(query)).enqueue(new Callback<List<ItemHotels>>() {
            @Override
            public void onResponse(Call<List<ItemHotels>> call, Response<List<ItemHotels>> response) {
                mainView.setVisibilityProgressBar(View.GONE);
                adapter = new RvAdapter<ItemHotels, ItemHotelHolder>(R.layout.item_hotels, ItemHotelHolder.class, ItemHotels.class, models) {
                    @Override
                    protected void bindView(ItemHotelHolder holder, ItemHotels model, int position) {
                        holder.bind(model);
                    }
                };
                mainView.setRV(adapter);
            }

            @Override
            public void onFailure(Call<List<ItemHotels>> call, Throwable t) {
                mainView.setVisibilityProgressBar(View.GONE);
            }
        });
    }
}
