package com.jm.recycleviewheaderandfooter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/4.
 * Author Name ShiJiaMing
 * Description : RecycleView的包装类
 */
public class WrapRecycleView extends RecyclerView{

    //保存头部view
   private ArrayList<View> mHeaderViews = new ArrayList<>();
    //保存底部的view
    private ArrayList<View> mFooterViews = new ArrayList<>();
    private Adapter mAdapter;

    public WrapRecycleView(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 添加头部的View
     * @param v
     */
    public void addHeaderView(View v) {
        mHeaderViews.add(v);
        // Wrap the adapter if it wasn't already wrapped.
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecyclerAdapter)) {
                mAdapter = new HeaderViewRecyclerAdapter(mHeaderViews, mFooterViews, mAdapter);
            }
        }
    }
    /**
     * 添加底部的View
     * @param v
     */
    public void addFooterView(View v) {
        mFooterViews.add(v);
        // Wrap the adapter if it wasn't already wrapped.
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecyclerAdapter)) {
                mAdapter = new HeaderViewRecyclerAdapter(mHeaderViews, mFooterViews, mAdapter);
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (mHeaderViews.size()>0||mFooterViews.size()>0){
            mAdapter=new HeaderViewRecyclerAdapter(mHeaderViews,mFooterViews,adapter);
        }else {
            mAdapter=adapter;
        }
        super.setAdapter(mAdapter);
    }
}
