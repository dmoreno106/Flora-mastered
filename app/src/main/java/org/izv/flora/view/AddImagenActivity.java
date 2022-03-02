package org.izv.flora.view;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.model.entity.Imagen;
import org.izv.flora.viewmodel.AddFloraViewModel;
import org.izv.flora.viewmodel.AddImagenViewModel;

public class AddImagenActivity extends AppCompatActivity {

    private Button btSelectImage;
    private ActivityResultLauncher<Intent> launcher;
    private Intent resultadoImagen;
    private Button btAddImagen;
    private EditText etIdFlora,etNombre,etDescripcion;
    private AddImagenViewModel aivm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_imagen);
        initialize();
    }

        private void initialize() {
           launcher= getLauncher();
         aivm = new ViewModelProvider(this).get(AddImagenViewModel.class);

            etIdFlora=findViewById(R.id.etIdFlora);
            etNombre=findViewById(R.id.etNombre);
            etDescripcion=findViewById(R.id.etDescripcion);

            btSelectImage=findViewById(R.id.btSelectImage);
            btSelectImage.setOnClickListener(view -> {
                selectImage();
            });


            btAddImagen=findViewById(R.id.btAddImagen);
            btAddImagen.setOnClickListener(view -> {
                uploadDataImage();
            });
        }

    private void uploadDataImage() {
    String nombre=etNombre.getText().toString();
    String descripcion=etDescripcion.getText().toString();
    String idFlora=etIdFlora.getText().toString();
    if(nombre.trim().isEmpty()|| idFlora.trim().isEmpty()|| resultadoImagen==null){
        Imagen imagen=new Imagen();
        imagen.nombre=nombre;
        imagen.descripcion=descripcion;
        imagen.idflora=Long.parseLong(idFlora);
        aivm.saveImagen(resultadoImagen,imagen);

    }
    }

    ActivityResultLauncher<Intent> getLauncher() {
            return registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                      if(result.getResultCode() == Activity.RESULT_OK) {
                      //  copyData(result.getData());
                      resultadoImagen=result.getData();
                      //  Uri uri=result.getData().getData();
                      }
                    });
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
            getLauncher().launch(getContentIntent());
        }
}