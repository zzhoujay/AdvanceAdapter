package zhou.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import zhou.widget.AdvanceAdapter;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdvanceAdapter advanceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> msgs = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            msgs.add("AdvanceAdapter  -->  " + i);
        }

        NormalAdapter normalAdapter = new NormalAdapter(msgs);
        advanceAdapter = new AdvanceAdapter(normalAdapter);
        View header = getLayoutInflater().inflate(R.layout.header, null);
        View header2 = getLayoutInflater().inflate(R.layout.header2, null);
        View footer = getLayoutInflater().inflate(R.layout.footer, null);
        View footer2 = getLayoutInflater().inflate(R.layout.footer2, null);
        advanceAdapter.addHeader(header);
        advanceAdapter.addHeader(header2);
        advanceAdapter.addFooter(footer);
        advanceAdapter.addFooter(footer2);
        recyclerView.setAdapter(advanceAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
