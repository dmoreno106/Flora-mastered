package org.izv.flora.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.model.api.FloraClient;
import org.izv.flora.view.adapter.Adapter;
import org.izv.flora.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   private FloraClient floraClient;
    private RecyclerView rv;
    private FloatingActionButton fabAdd,fabAddImagen;
    private Adapter adapter;
    private  MutableLiveData<ArrayList<Flora>> floraList;
    private Context context;
    private MainActivityViewModel mavm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
         initialize();
    }

    private void initialize() {
        mavm = new ViewModelProvider(this).get(MainActivityViewModel.class);
         floraList= mavm.getFloraLiveData();

        buttons();

        adapter=new Adapter(this);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this);
        rv.setAdapter(adapter);


        mavm.getFlora();
        mavm.getFloraLiveData().observe(this, floras -> {
            adapter.setFloraList(floras);
        });
        adapter.setOnClickListener(view -> {

            Intent intent = new Intent(this, EditFloraActivity.class);
            intent.putExtra("selectedFlora", adapter.getItem(rv.getChildAdapterPosition(view)));

            ArrayList<Flora> floras = new ArrayList<>();
            for (int i = 0; i < adapter.getItemCount(); i++) {
                floras.add(adapter.getItem(i));
            }
            intent.putExtra("idFloras", floras);

            startActivity(intent);

        });
    }

    private void buttons() {
        floraList.observe(this, floraPlural -> {
           // Log.v("xyzyx", floraPlural.toString());


            fabAdd=findViewById(R.id.fabAdd);
            fabAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(context, AddFloraActivity.class);

                    ArrayList<Flora> floras = new ArrayList<>();
                    for (int i = 0; i < adapter.getItemCount(); i++) {
                        floras.add(adapter.getItem(i));
                    }
                    intent.putExtra("idFloras", floras);
                    startActivity(intent);
                }
            });

        });


        fabAddImagen=findViewById(R.id.fabImagen);
        fabAddImagen.setOnClickListener(view -> {
            openAddImagenActivity();
        });


    }

    private void openAddImagenActivity(){

        ArrayList<Flora> floras = new ArrayList<>();
        for (int i = 0; i < adapter.getItemCount(); i++) {
            floras.add(adapter.getItem(i));
        }



        Intent intent=new Intent(this, AddImagenActivity.class);
        intent.putExtra("arrayFlora", floras);
        startActivity(intent);
    }

    protected void onResume() {
        super.onResume();
        mavm.getFlora();
        initialize();
    }



}