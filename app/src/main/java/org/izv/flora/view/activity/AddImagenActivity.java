package org.izv.flora.view.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.model.entity.Imagen;
import org.izv.flora.viewmodel.AddImagenViewModel;

import java.util.ArrayList;
import java.util.Random;

public class AddImagenActivity extends AppCompatActivity {

    private Button btCancelImage;
    private ActivityResultLauncher<Intent> launcher;
    private Intent resultadoImagen;
    private Spinner spinnerIdFlora;
    private Button btAddImagen;
    private EditText etNombre,etDescripcion;
    private AddImagenViewModel aivm;
    private ArrayList<Flora> arrayFlora;
    private long id;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_imagen);
        arrayFlora= (ArrayList<Flora>) getIntent().getSerializableExtra("arrayFlora");
        initialize();
    }

        private void initialize() {
           launcher= getLauncher();
         aivm = new ViewModelProvider(this).get(AddImagenViewModel.class);
            spinnerIdFlora =findViewById(R.id.spinnerFloraId);
            etNombre=findViewById(R.id.etNombreAddImg);
            etDescripcion=findViewById(R.id.etDescripcion);


            fillSpinner();

            iv=findViewById(R.id.ivAddPicture);
            iv.setOnClickListener(view -> {
                selectImage();
            });


            btCancelImage =findViewById(R.id.btCancelImage);
            btCancelImage.setOnClickListener(view -> {
                finish();
            });


            btAddImagen=findViewById(R.id.btAddImagen);
            btAddImagen.setOnClickListener(view -> {
                uploadDataImage();
            });
        }

    private void uploadDataImage() {

        long id = 0;
        String nombre = "";
        for (int i = 0; i < arrayFlora.size(); i++) {
            if (spinnerIdFlora.getSelectedItem().toString().equals(arrayFlora.get(i).getNombre())) {
                id = arrayFlora.get(i).getId();
            }
        }

        Random seed = new Random(30);
        Integer numero=-seed.nextInt();
        String idFlora = String.valueOf(id);
        nombre = etNombre.getText().toString() + "_" +numero;
        String descripcion =etDescripcion.getText().toString();
        if (!(nombre.trim().isEmpty() || idFlora.trim().isEmpty() || resultadoImagen == null)) {
            Imagen imagen = new Imagen();
            imagen.nombre = nombre;
            imagen.descripcion = descripcion;
            imagen.idflora = Long.parseLong(idFlora);
            aivm.saveImagen(resultadoImagen, imagen);
            Log.v("xyzyx", imagen.toString());
            finish();
        } else {
        }
    }

    ActivityResultLauncher<Intent> getLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    //respuesta al resultado de haber seleccionado una imagen
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        resultadoImagen = result.getData();
                    }
                }
        );
    }

    Intent getContentIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        return intent;
    }


    void selectImage() {
        Intent intent = getContentIntent();
        launcher.launch(intent);
    }
        void fillSpinner(){

            String[] selected = new String[arrayFlora.size()];
            for (int i = 0; i < arrayFlora.size(); i++) {
                selected[i] = arrayFlora.get(i).getNombre();
            }
         ArrayAdapter<String> content = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, selected);
            spinnerIdFlora.setAdapter(content);
        }
}