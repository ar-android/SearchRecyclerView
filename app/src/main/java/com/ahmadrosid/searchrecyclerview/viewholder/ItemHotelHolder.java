package com.ahmadrosid.searchrecyclerview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ahmadrosid.searchrecyclerview.R;
import com.ahmadrosid.searchrecyclerview.model.ItemHotels;

/**
 * Created by ocittwo on 30/08/16.
 */
public class ItemHotelHolder extends RecyclerView.ViewHolder{

    public TextView city;
    public TextView hotel;

    public ItemHotelHolder(View itemView) {
        super(itemView);
        city = (TextView) itemView.findViewById(R.id.city);
        hotel = (TextView) itemView.findViewById(R.id.hotel);
    }

    /**
     * Set data to itemviews
     * @param data
     */
    public void bind(ItemHotels data){
        city.setText(data.city);
        hotel.setText(data.hotel);
    }
}
