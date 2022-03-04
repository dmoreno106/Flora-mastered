package org.izv.flora.view.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.model.entity.Imagen;
import org.izv.flora.viewmodel.AddImagenViewModel;

import java.util.ArrayList;

public class AddImagenActivity extends AppCompatActivity {

    private Button btSelectImage;
    private ActivityResultLauncher<Intent> launcher;
    private Intent resultadoImagen;
    private AutoCompleteTextView spinnerIdFlora;
    private Button btAddImagen;
    private EditText etNombre,etDescripcion;
    private AddImagenViewModel aivm;
    private ArrayList<Flora> arrayFlora;
    private long id;
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
            spinnerIdFlora =findViewById(R.id.idSpinner);
            etNombre=findViewById(R.id.etNombre);
            etDescripcion=findViewById(R.id.etDescripcion);


            fillSpinner();

            btSelectImage=findViewById(R.id.btCancelImage);
            btSelectImage.setOnClickListener(view -> {
                selectImage();
            });


            btAddImagen=findViewById(R.id.btAddImagen);
            btAddImagen.setOnClickListener(view -> {
                uploadDataImage();
            });
        }

    private void uploadDataImage() {
        for (Flora flora:arrayFlora) {
            if(spinnerIdFlora.getText().equals(flora.getNombre())){
              id=flora.getId();
            }
        }

    String nombre=etNombre.getText().toString();
    String descripcion=etDescripcion.getText().toString();
    String idFlora= spinnerIdFlora.getText().toString();
    if(nombre.trim().isEmpty()|| idFlora.trim().isEmpty()|| resultadoImagen==null ||id==0){

    }else{
        Imagen imagen=new Imagen();
        imagen.nombre=nombre;
        imagen.descripcion=descripcion;
        imagen.idflora=Long.parseLong(idFlora);
        aivm.saveImagen(resultadoImagen,imagen);
        finish();
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

        void fillSpinner(){

            String[] selected = new String[arrayFlora.size()];
            for (int i = 0; i < arrayFlora.size(); i++) {
                selected[i] = arrayFlora.get(i).getNombre();
            }
         ArrayAdapter<String> content = new ArrayAdapter<>(this, R.layout.list_flora, R.id.idFlora, selected);
            spinnerIdFlora.setAdapter(content);
        }
}