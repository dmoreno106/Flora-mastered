package org.izv.flora.model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import org.izv.flora.R;
import org.izv.flora.model.api.FloraClient;
import org.izv.flora.model.entity.CreateResponse;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.model.entity.Imagen;
import org.izv.flora.model.entity.RowsResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private Context context;
    private static FloraClient floraClient;

    private MutableLiveData<ArrayList<Flora>> floraLiveData = new MutableLiveData<>();
    private MutableLiveData<Long> addFloraLiveData=new MutableLiveData<Long>();
    private MutableLiveData<Long> addImagenLiveData=new MutableLiveData<>();
    private MutableLiveData<Long> deleteFloraLiveData = new MutableLiveData<>();
    private MutableLiveData<Long> deleteImagenLiveData = new MutableLiveData<>();
    private MutableLiveData<Long> editFloraLiveData = new MutableLiveData<>();

    static {
        floraClient = getFloraClient();
    }

    public Repository(Context context) {
        this.context = context;
    }

    private static FloraClient getFloraClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://informatica.ieszaidinvergeles.org:10008/ad/felixRLDFApp/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(FloraClient.class);
    }

    public MutableLiveData<ArrayList<Flora>> getFloraLiveData() {
        return floraLiveData;
    }

    public MutableLiveData<Long> getAddFloraLiveData(){ return addFloraLiveData;}
    public MutableLiveData<Long> getAddImagenLiveData() { return addImagenLiveData; }






    public void deleteFlora(long id) {
        Call<RowsResponse> call = floraClient.deleteFlora(id);
        call.enqueue(new Callback<RowsResponse>() {
            @Override
            public void onResponse(Call<RowsResponse> call, Response<RowsResponse> response) {
                deleteFloraLiveData.setValue(response.body().rows);
            }

            @Override
            public void onFailure(Call<RowsResponse> call, Throwable t) {

            }
        });
    }

    public void getFlora(long id) {

    }

    public void getFlora() {
        Call<ArrayList<Flora>> call = floraClient.getFlora();
        call.enqueue(new Callback<ArrayList<Flora>>() {
            @Override
            public void onResponse(Call<ArrayList<Flora>> call, Response<ArrayList<Flora>> response) {
                floraLiveData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<ArrayList<Flora>> call, Throwable t) {

            }
        });
    }



    public void setAddImagenLiveData(MutableLiveData<Long> addImagenLiveData) {
        this.addImagenLiveData = addImagenLiveData;
    }

    public void createFlora(Flora flora) {
        Call<CreateResponse>call=floraClient.createFlora(flora);
        call.enqueue(new Callback<CreateResponse>() {
            @Override
            public void onResponse(Call<CreateResponse> call, Response<CreateResponse> response) {
                addFloraLiveData.setValue(response.body().id);
            }

            @Override
            public void onFailure(Call<CreateResponse> call, Throwable t) {
                Log.v("xyzxy",t.toString());
            }
        });
    }

    public void editFlora(long id, Flora flora) {
        Call<RowsResponse> call = floraClient.editFlora(id, flora);
        call.enqueue(new Callback<RowsResponse>() {
            @Override
            public void onResponse(Call<RowsResponse> call, Response<RowsResponse> response) {
                try {
                    editFloraLiveData.setValue(response.body().rows);
                    Toast.makeText(context, R.string.editSuccesful, Toast.LENGTH_LONG).show();
                } catch (NullPointerException e) {
                    Toast.makeText(context, R.string.editFail, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RowsResponse> call, Throwable t) {

            }
        });
    }

    public void saveImagen(Intent intent,Imagen imagen){
        String nombre="xyzyx.abc";
        copyData(intent,nombre);
        File file=new File(context.getExternalFilesDir(null),nombre);
        subirImagen(file,imagen);
    }
    private void subirImagen(File file, Imagen imagen){
        RequestBody requestFile=RequestBody.create(MediaType.parse("image/*"),file);
        MultipartBody.Part body=MultipartBody.Part.createFormData("photo",imagen.nombre,requestFile);
        Call<Long> call =floraClient.subirImagen(body,imagen.idflora,imagen.descripcion);

        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
            addImagenLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {

            }
        });
    }
    private void copyData(Intent data,String name){
        Uri uri=data.getData();
        InputStream in= null;
        OutputStream out=null;
        try {
            in=context.getContentResolver().openInputStream(uri);
            out=new FileOutputStream(new File(context.getExternalFilesDir(null),name));
            byte[]buffer=new byte[1024];
            int len;
            while ((len=in.read(buffer))!=-1);{
             out.write(buffer,0,len);
            }
            in.close();
            out.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } ;
    }
}