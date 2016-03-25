package com.zzhoujay.advanceadaptersimple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zzhoujay.advanceadapter.DynamicAdapter;
import com.zzhoujay.advanceadapter.SingleAdapter;


public class MainActivity extends AppCompatActivity {

    DynamicAdapter dynamicAdapter;
    SingleAdapter singleAdapter;
    View h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        if (recyclerView != null) {
            recyclerView.setLayoutManager(layoutManager);
        }

        assert recyclerView != null;

        final NormalAdapter normalAdapter = new NormalAdapter(20);
        dynamicAdapter = new DynamicAdapter(normalAdapter, layoutManager);

        singleAdapter = new SingleAdapter(normalAdapter, layoutManager);
        normalAdapter.setCallback(new NormalAdapter.Callback() {
            @Override
            public void call(int index) {
                normalAdapter.setNum(index - dynamicAdapter.headerCount());
            }
        });

        View header = getLayoutInflater().inflate(R.layout.header, null);
        View header2 = getLayoutInflater().inflate(R.layout.header, null);
        View footer = getLayoutInflater().inflate(R.layout.footer, null);
        View footer2 = getLayoutInflater().inflate(R.layout.footer, null);
        dynamicAdapter.addHeaderView(header);
        dynamicAdapter.addHeaderView(header2);
        dynamicAdapter.addFooterView(footer);
        dynamicAdapter.addFooterView(footer2);
//        singleAdapter.setHeaderView(header);
//        singleAdapter.setFooterView(footer);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "add");
        menu.add(0, 1, 1, "remove");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                h = LayoutInflater.from(this).inflate(R.layout.header, null);
                dynamicAdapter.addFooterView(h);
                break;
            case 1:
                dynamicAdapter.removeFooterView(h);
        }
        return super.onOptionsItemSelected(item);
    }
}
