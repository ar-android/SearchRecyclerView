package com.ahmadrosid.searchrecyclerview.presenter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by ocittwo on 30/08/16.
 */
public interface MainView {
    /**
     * Set RecyclerView for result search hotel
     * @param adapter
     */
    void setRV(RecyclerView.Adapter adapter);

    /**
     * Set visibility progress bar
     * @param visibility
     */
    void setVisibilityProgressBar(int visibility);
}
