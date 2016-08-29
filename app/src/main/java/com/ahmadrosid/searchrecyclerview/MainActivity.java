package com.ahmadrosid.searchrecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.ahmadrosid.searchrecyclerview.presenter.MainView;
import com.ahmadrosid.searchrecyclerview.presenter.PresenterMain;

public class MainActivity extends AppCompatActivity implements MainView, SearchView.OnQueryTextListener {

    private RecyclerView rv_list_hotel;
    private ProgressBar progressBar;
    private PresenterMain presenterMain;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeView();
        initializePresenter();
    }

    private void initializeView() {
        rv_list_hotel = (RecyclerView) findViewById(R.id.rv_list_hotel);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void initializePresenter() {
        context = this;
        presenterMain = new PresenterMain(context, this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setRV(RecyclerView.Adapter adapter) {
        rv_list_hotel.setLayoutManager(new LinearLayoutManager(this));
        rv_list_hotel.setAdapter(adapter);
    }

    @Override
    public void setVisibilityProgressBar(int visibility) {
        switch (visibility) {
            case View.GONE:
                progressBar.setVisibility(visibility);
                rv_list_hotel.setVisibility(View.VISIBLE);
                rv_list_hotel.scrollToPosition(0);
                break;
            case View.VISIBLE:
                progressBar.setVisibility(visibility);
                rv_list_hotel.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.length() > 3) {
            presenterMain.loadData(newText);
        }
        return true;
    }
}
