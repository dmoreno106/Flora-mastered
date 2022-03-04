package org.izv.flora.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.viewmodel.EditFloraViewModel;

import java.util.ArrayList;

public class EditFloraActivity extends AppCompatActivity {
    private Context context;
    private Flora flora;
    private ArrayList<Flora> arrayFlora = new ArrayList<>();
    private EditFloraViewModel efvm;
    private String ivFloraURL = "https://informatica.ieszaidinvergeles.org:10012/ad/felixRDLFApp/public/api/imagen/";
    private ImageView ivFlora;
    private EditText etNombre, etFamilia, etIdentificacion,
            etAltitud, etHabitat, etFitosociologia,
            etBiotipo, etBioReproductiva, etFloracion,
            etFructificacion, etExpSexual, etPolinizacion,
            etDispersion, etNumCromosomatico, etRepAsexual,
            etDistribucion, etBiologia, etDemografia,
            etAmenazas, etMedPropuestas;

    private Button btBorrar, btEditar, btCancelEdit, btGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flora);
        iniatilize();
    }

    private void iniatilize() {
        context = this;
        arrayFlora = (ArrayList<Flora>) getIntent().getSerializableExtra("arrayFlora");

        initComponents();

    }

    private void initComponents() {

        efvm = new ViewModelProvider(this).get(EditFloraViewModel.class);

        ivFlora = findViewById(R.id.ivFlora);
        etNombre = findViewById(R.id.etNombre);
        etFamilia = findViewById(R.id.etFamilia);
        etIdentificacion = findViewById(R.id.etIdentificacion);
        etAltitud = findViewById(R.id.etAltitud);
        etHabitat = findViewById(R.id.etHabitat);
        etFitosociologia = findViewById(R.id.etFitosociologia);
        etBiotipo = findViewById(R.id.etBiotipo);
        etBioReproductiva = findViewById(R.id.etBioReproductiva);
        etFloracion = findViewById(R.id.etFloracion);
        etFructificacion = findViewById(R.id.etFructificacion);
        etExpSexual = findViewById(R.id.etExpSexual);
        etPolinizacion = findViewById(R.id.etPolinizacion);
        etDispersion = findViewById(R.id.etDispersion);
        etNumCromosomatico = findViewById(R.id.etNumCromosomatico);
        etRepAsexual = findViewById(R.id.etRepAsexual);
        etDistribucion = findViewById(R.id.etDistribucion);
        etBiologia = findViewById(R.id.etBiologia);
        etDemografia = findViewById(R.id.etDemografia);
        etAmenazas = findViewById(R.id.etAmenazas);
        etMedPropuestas = findViewById(R.id.etMedPropuestas);

        setFlora();
        buttons();
        noEdit();
    }

    private void buttons() {

        btBorrar = findViewById(R.id.btBorrar);
        btBorrar.setOnClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle(R.string.borrado)
                    .setMessage(R.string.borrarFlora)
                    .setPositiveButton(R.string.accept, (dialog, which) -> {
                        efvm.deleteFlora(flora.getId());
                        Toast.makeText(context, R.string.floraAdd, Toast.LENGTH_LONG).show();
                        finish();
                    })
                    .setNegativeButton(R.string.cancel, (dialog, which) -> {
                        dialog.cancel();
                    })
                    .show();
        });


        btEditar = findViewById(R.id.btEdit);
        btEditar.setOnClickListener(view -> {
                        edit();
        });


        btCancelEdit = findViewById(R.id.btCancelEdit);
        btCancelEdit.setOnClickListener(view -> {
        finish();
        });



        btGuardar = findViewById(R.id.btGuardar);
        btGuardar.setOnClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle(R.string.cambiar)
                    .setMessage(R.string.deseaCambiar)
                    .setPositiveButton(R.string.accept, (dialog, which) -> {
                        if (!etNombre.getText().toString().isEmpty()) {
                            efvm.editFlora(flora.getId(), getFlora());
                            finish();
                        } else {
                            Toast.makeText(context, R.string.noName, Toast.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton(R.string.cancel, (dialog, which) -> {
                        dialog.cancel();
                    })
                    .show();
        });
    }

    private Flora getFlora() {
        flora = new Flora();
        flora.setNombre(etNombre.getText().toString());
        flora.setFamilia(etFamilia.getText().toString());
        flora.setIdentificacion(etIdentificacion.getText().toString());
        flora.setAltitud(etAltitud.getText().toString());
        flora.setHabitat(etHabitat.getText().toString());
        flora.setFitosociologia(etFitosociologia.getText().toString());
        flora.setBiotipo(etBiotipo.getText().toString());
        flora.setBiologia_reproductiva(etBioReproductiva.getText().toString());
        flora.setFloracion(etFloracion.getText().toString());
        flora.setFructificacion(etFructificacion.getText().toString());
        flora.setExpresion_sexual(etExpSexual.getText().toString());
        flora.setPolinizacion(etPolinizacion.getText().toString());
        flora.setDispersion(etDispersion.getText().toString());
        flora.setNumero_cromosomatico(etNumCromosomatico.getText().toString());
        flora.setReproduccion_asexual(etRepAsexual.getText().toString());
        flora.setDistribucion(etDistribucion.getText().toString());
        flora.setBiologia(etBiologia.getText().toString());
        flora.setDemografia(etDemografia.getText().toString());
        flora.setAmenazas(etAmenazas.getText().toString());
        flora.setMedidas_propuestas(etMedPropuestas.getText().toString());
        return flora;
    }

    private void setFlora() {

        Bundle bundle = getIntent().getExtras();
        flora = bundle.getParcelable("selectedFlora");


        etNombre.setText(flora.getNombre());
        etFamilia.setText(flora.getFamilia());
        etIdentificacion.setText(flora.getIdentificacion());
        etAltitud.setText(flora.getAltitud());
        etHabitat.setText(flora.getHabitat());
        etFitosociologia.setText(flora.getFitosociologia());
        etBiotipo.setText(flora.getBiotipo());
        etBioReproductiva.setText(flora.getBiologia_reproductiva());
        etFloracion.setText(flora.getFloracion());
        etFructificacion.setText(flora.getFructificacion());
        etExpSexual.setText(flora.getExpresion_sexual());
        etPolinizacion.setText(flora.getPolinizacion());
        etDispersion.setText(flora.getDispersion());
        etNumCromosomatico.setText(flora.getNumero_cromosomatico());
        etRepAsexual.setText(flora.getReproduccion_asexual());
        etDistribucion.setText(flora.getDistribucion());
        etBiologia.setText(flora.getBiologia());
        etDemografia.setText(flora.getDemografia());
        etAmenazas.setText(flora.getAmenazas());
        etMedPropuestas.setText(flora.getMedidas_propuestas());
    }


    private void edit() {


        btEditar.setVisibility(View.GONE);
        ivFlora.setVisibility(View.GONE);
        btGuardar.setVisibility(View.VISIBLE);

        etNombre.setEnabled(true);
        etFamilia.setEnabled(true);
        etIdentificacion.setEnabled(true);
        etAltitud.setEnabled(true);
        etHabitat.setEnabled(true);
        etFitosociologia.setEnabled(true);
        etBiotipo.setEnabled(true);
        etBioReproductiva.setEnabled(true);
        etFloracion.setEnabled(true);
        etFructificacion.setEnabled(true);
        etExpSexual.setEnabled(true);
        etPolinizacion.setEnabled(true);
        etDispersion.setEnabled(true);
        etNumCromosomatico.setEnabled(true);
        etRepAsexual.setEnabled(true);
        etDistribucion.setEnabled(true);
        etBiologia.setEnabled(true);
        etDemografia.setEnabled(true);
        etAmenazas.setEnabled(true);
        etMedPropuestas.setEnabled(true);
    }


    private void noEdit() {


        btEditar.setVisibility(View.VISIBLE);

        ivFlora.setVisibility(View.VISIBLE);
        btGuardar.setVisibility(View.GONE);

        Picasso.get().load(ivFloraURL + flora.getId() + "/flora").into(ivFlora);

        etNombre.setEnabled(false);
        etFamilia.setEnabled(false);
        etIdentificacion.setEnabled(false);
        etAltitud.setEnabled(false);
        etHabitat.setEnabled(false);
        etFitosociologia.setEnabled(false);
        etBiotipo.setEnabled(false);
        etBioReproductiva.setEnabled(false);
        etFloracion.setEnabled(false);
        etFructificacion.setEnabled(false);
        etExpSexual.setEnabled(false);
        etPolinizacion.setEnabled(false);
        etDispersion.setEnabled(false);
        etNumCromosomatico.setEnabled(false);
        etRepAsexual.setEnabled(false);
        etDistribucion.setEnabled(false);
        etBiologia.setEnabled(false);
        etDemografia.setEnabled(false);
        etAmenazas.setEnabled(false);
        etMedPropuestas.setEnabled(false);
    }
}