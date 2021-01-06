package com.awak25.crudlistmakanann;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.awak25.crudlistmakanann.model.DataItem;
import com.awak25.crudlistmakanann.model.ResponseMakanan;
import com.awak25.crudlistmakanann.network.ApiClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_makanan)
    RecyclerView rvMakanan;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private MakananAdapter makananAdapter;
    private List<DataItem> dataItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        rvMakanan.setLayoutManager(new LinearLayoutManager(this));
        rvMakanan.setHasFixedSize(true);

        ApiClient.service.getAllMakanan().enqueue(new Callback<ResponseMakanan>() {
            @Override
            public void onResponse(Call<ResponseMakanan> call, Response<ResponseMakanan> response) {
                dataItems = response.body().getData();

                makananAdapter = new MakananAdapter(dataItems, MainActivity.this);
                rvMakanan.setAdapter(makananAdapter);
            }

            @Override
            public void onFailure(Call<ResponseMakanan> call, Throwable t) {

            }
        });
        reloadData();
    }

    public void reloadData(){
        ApiClient.service.getAllMakanan().enqueue(new Callback<ResponseMakanan>() {
            @Override
            public void onResponse(Call<ResponseMakanan> call, Response<ResponseMakanan> response) {
                if (response.isSuccessful()){
                    List<DataItem> dataItems = response.body().getData();
                    MakananAdapter adapterMakanan = new MakananAdapter(dataItems, MainActivity.this);
                    rvMakanan.setAdapter(adapterMakanan);
                }
            }

            @Override
            public void onFailure(Call<ResponseMakanan> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadData();
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
