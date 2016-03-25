package com.zzhoujay.advanceadaptersimple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zzhoujay.advanceadapter.DynamicAdapter;


public class MainActivity extends AppCompatActivity {

    DynamicAdapter dynamicAdapter;
    View h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        if (recyclerView != null) {
            recyclerView.setLayoutManager(layoutManager);
        }

        assert recyclerView != null;

        final NormalAdapter normalAdapter = new NormalAdapter(20);
        dynamicAdapter = new DynamicAdapter(normalAdapter, layoutManager);

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
//
        recyclerView.setAdapter(dynamicAdapter);


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
