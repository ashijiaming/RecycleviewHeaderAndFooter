package com.jm.recycleviewheaderandfooter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/4.
 * Author Name ShiJiaMing
 * Description :包装的HeaderAdapter类
 */

public class HeaderViewRecyclerAdapter extends Adapter {

    private Adapter mAdapter;
    private ArrayList<View> mHeaderViews;
    private ArrayList<View> mFooterViews;
    private static final int STATE_HEADER=1;
    private static final int STATE_FOOTER=-1;
    private int headerIndex=0;
    private int footerIndex=0;
    public HeaderViewRecyclerAdapter(ArrayList<View> headerViews, ArrayList<View> footerViews,
                                     Adapter adapter) {
        mAdapter=adapter;
        if (headerViews==null){
            mHeaderViews=new ArrayList<>();
        }else {
            mHeaderViews=headerViews;
        }
        if (footerViews==null){
            mFooterViews=new ArrayList<>();
        }else {
            mFooterViews=footerViews;
        }
    }

    @Override
    public int getItemViewType(int position) {
        //判断当前条目是什么类型的---决定渲染什么视图给什么数据
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {//表示头部
            return STATE_HEADER;
        }
        //数据项Item部分
        // Adapter
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.getItemViewType(adjPosition);
            }
        }
        //footer部分
        return STATE_FOOTER;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==STATE_HEADER){//header
            return new HeaderViewHolder(mHeaderViews.get(headerIndex++));
        }else if(viewType==STATE_FOOTER){//footer
            return new HeaderViewHolder(mFooterViews.get(footerIndex++));
        }
        // Footer (off-limits positions will throw an IndexOutOfBoundsException)
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       //需要划分三个区域
        int numHeaders = getHeadersCount();

        if (position < numHeaders) {//表示头部
            return ;
        }
        //adapter body
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mAdapter.onBindViewHolder(holder, adjPosition);
                return ;
            }
        }
        //表示底部
    }

    @Override
    public int getItemCount() {
        if (mAdapter != null) {
            return getFootersCount() + getHeadersCount() + mAdapter.getItemCount();
        } else {
            return getFootersCount() + getHeadersCount();
        }
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFooterViews.size();
    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View view) {
            super(view);
        }
    }

}
