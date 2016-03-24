package com.zzhoujay.advanceadaptersimple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.zzhoujay.advanceadapter.DynamicAdapter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        if (recyclerView != null) {
            recyclerView.setLayoutManager(layoutManager);
        }

        assert recyclerView != null;

        final NormalAdapter normalAdapter = new NormalAdapter(20);
        final DynamicAdapter dynamicAdapter = new DynamicAdapter(normalAdapter, layoutManager);

        normalAdapter.setCallback(new NormalAdapter.Callback() {
            @Override
            public void call(int index) {
                normalAdapter.setNum(index - dynamicAdapter.headerCount());
            }
        });

//        SingleAdapter singleAdapter = new SingleAdapter(normalAdapter, layoutManager);
        View header = getLayoutInflater().inflate(R.layout.header, null);
        View header2 = getLayoutInflater().inflate(R.layout.header, null);
        View footer = getLayoutInflater().inflate(R.layout.footer, null);
        View footer2 = getLayoutInflater().inflate(R.layout.footer, null);
        dynamicAdapter.addHeaderView(header);
        dynamicAdapter.addHeaderView(header2);
        dynamicAdapter.addFooterView(footer);
        dynamicAdapter.addFooterView(footer2);
//        singleAdapter.setHeader(header);
//        singleAdapter.setFooter(footer);
//        recyclerView.setAdapter(singleAdapter);
//
        recyclerView.setAdapter(dynamicAdapter);

//        recyclerView.post(new Runnable() {
//            @Override
//            public void run() {
//                dynamicAdapter.notifyDataSetChanged();
//            }
//        });

    }

}
