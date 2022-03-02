package org.izv.flora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.izv.flora.model.entity.CreateResponse;
import org.izv.flora.model.entity.RowsResponse;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.model.api.FloraClient;
import org.izv.flora.view.AddFloraActivity;
import org.izv.flora.view.AddImagenActivity;
import org.izv.flora.viewmodel.AddFloraViewModel;
import org.izv.flora.viewmodel.AddImagenViewModel;
import org.izv.flora.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

   private FloraClient floraClient;
    private FloatingActionButton fabAdd,fabAddImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        fabAdd=findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddActivity();
            }
        });
        MainActivityViewModel mavm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        MutableLiveData<ArrayList<Flora>> floraList = mavm.getFloraLiveData();
        floraList.observe(this, floraPlural -> {
            Log.v("xyzyx", floraPlural.toString());
            //recyclerview
        });
        mavm.getFlora();
        fabAddImagen=findViewById(R.id.fabImagen);
        fabAddImagen.setOnClickListener(view -> {
            openAddImagenActivity();
        });

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://informatica.ieszaidinvergeles.org:10099/ad/felixRDLFApp/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        floraClient = retrofit.create(FloraClient.class);

        Call<ArrayList<Flora>> call = floraClient.getFlora();
        call.enqueue(new Callback<ArrayList<Flora>>() {
            @Override
            public void onResponse(Call<ArrayList<Flora>> call, Response<ArrayList<Flora>> response) {
                Log.v("xyzyx", response.body().toString());
            }
            @Override
            public void onFailure(Call<ArrayList<Flora>> call, Throwable t) {
                Log.v("xyzyx", t.getLocalizedMessage());
            }
        });

        Call<Flora> call2 = floraClient.getFlora(1);
        call2.enqueue(new Callback<Flora>() {
            @Override
            public void onResponse(Call<Flora> call, Response<Flora> response) {
                Log.v("xyzx¡yx", response.body().toString());
            }

            @Override
            public void onFailure(Call<Flora> call, Throwable t) {
                Log.v("xyzyx", t.getLocalizedMessage());
            }
        });

        Flora flora = new Flora();
        flora.setNombre("flora mía versión 3");
        Call<CreateResponse> call3 = floraClient.createFlora(flora);
        call3.enqueue(new Callback<CreateResponse>() {
            @Override
            public void onResponse(Call<CreateResponse> call, Response<CreateResponse> response) {
                Log.v("xyzxyx", response.body().toString());
            }

            @Override
            public void onFailure(Call<CreateResponse> call, Throwable t) {
                Log.v("xyzyx", t.getLocalizedMessage());
            }
        });

        flora.setNombre("pepe 3");
        flora.setAltitud("alta altitud");
        Call<RowsResponse> call4 = floraClient.editFlora(25, flora);
        call4.enqueue(new Callback<RowsResponse>() {
            @Override
            public void onResponse(Call<RowsResponse> call, Response<RowsResponse> response) {
                Log.v("xyzxyx", response.body().toString());
            }

            @Override
            public void onFailure(Call<RowsResponse> call, Throwable t) {
                Log.v("xyzyx", t.getLocalizedMessage());
            }
        });

        Call<RowsResponse> call5 = floraClient.deleteFlora(25);
        call5.enqueue(new Callback<RowsResponse>() {
            @Override
            public void onResponse(Call<RowsResponse> call, Response<RowsResponse> response) {
                Log.v("xyzxyx", response.body().toString());
            }

            @Override
            public void onFailure(Call<RowsResponse> call, Throwable t) {
                Log.v("xyzyx", t.getLocalizedMessage());
            }
        });*/
    }

    private void openAddImagenActivity(){
        Intent intent=new Intent(this, AddImagenActivity.class);
        startActivity(intent);
    }

    private void openAddActivity() {
        Intent intent =new Intent(this, AddFloraActivity.class);
        startActivity(intent);
    }
}