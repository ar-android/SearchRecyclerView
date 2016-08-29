package com.ahmadrosid.searchrecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by ocittwo on 30/08/16.
 */
public abstract class RvAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    protected int mLayout;
    Class<VH> mViewHolderClass;
    Class<T> mModelClass;
    ArrayList<T> mData;

    public RvAdapter(int mLayout,
                     Class<VH> mViewHolderClass,
                     Class<T> mModelClass,
                     ArrayList<T> mData) {
        this.mLayout = mLayout;
        this.mViewHolderClass = mViewHolderClass;
        this.mModelClass = mModelClass;
        this.mData = mData;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup view = (ViewGroup)
                LayoutInflater.from(parent.getContext())
                        .inflate(mLayout, parent, false);
        try {
            Constructor<VH> constructor = mViewHolderClass.getConstructor(View.class);
            return constructor.newInstance(view);
        }catch (NoSuchMethodException e){
            throw new RuntimeException(e);
        }catch (InvocationTargetException e){
            throw new RuntimeException(e);
        }catch (InstantiationException e){
            throw new RuntimeException(e);
        }catch (IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        T model = getItem(position);
        bindView(holder, model, position);
    }

    abstract protected void bindView(VH holder, T model, int position);

    private T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}