package com.jm.recycleviewheaderandfooter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private WrapRecycleView wrapRecycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wrapRecycleView = (WrapRecycleView) findViewById(R.id.recycle_view);


        View headerView = LayoutInflater.from(this).inflate(R.layout.header_view, null, false);
        View footerView = LayoutInflater.from(this).inflate(R.layout.footer_view, null, false);

        wrapRecycleView.addHeaderView(headerView);
        wrapRecycleView.addHeaderView(footerView);
        wrapRecycleView.addFooterView(footerView);


        ArrayList<String> list = new ArrayList<>();
        for (int i=0;i<20;i++){
            list.add("王者荣耀   "+i);
        }

        HeroAdapter adapter = new HeroAdapter(list);
        wrapRecycleView.setLayoutManager(new LinearLayoutManager(this));
        wrapRecycleView.setAdapter(adapter);


    }
}
