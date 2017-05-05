package com.jm.recycleviewheaderandfooter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 * Author Name ShiJiaMing
 * Description : 数据项的adapter
 */

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroViewHolder> {

    private List<String> mList;

    public HeroAdapter(List<String> list) {
        if (list==null){
            mList=new ArrayList<>();
        }else {
            this.mList = list;
        }
    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item, parent, false);
        HeroViewHolder viewHolder = new HeroViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HeroViewHolder holder, int position) {
        holder.textView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public HeroViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.tv_item_content);
        }
    }
}
