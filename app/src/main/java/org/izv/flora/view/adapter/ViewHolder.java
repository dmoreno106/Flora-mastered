package org.izv.flora.view.adapter;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.flora.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    /**
     * Campos de la clase
     */
    public ImageView ivFlora;
    public EditText etNombre, etFamilia,etHabitat,etDemografia,etAmenazas;
    public Button btExpand;
    Boolean expanded;

    public ViewHolder(@NonNull View item) {
        super(item);
        ivFlora = item.findViewById(R.id.ivFloraItem);
        etNombre = item.findViewById(R.id.etNombreItem);
        etFamilia = item.findViewById(R.id.etFamiliaItem);
        etHabitat=item.findViewById(R.id.etHabitatItem);
        etDemografia=item.findViewById(R.id.etDemografiaItem);
        etAmenazas=item.findViewById(R.id.etAmenazasItem);


        expanded=false;
        itemView.findViewById(R.id.btExpand).setOnClickListener(view -> {
            if (expanded){
                item.findViewById(R.id.tiHabitat).setVisibility(View.GONE);
                item.findViewById(R.id.tiDemografia).setVisibility(View.GONE);
                item.findViewById(R.id.tiAmenazas).setVisibility(View.GONE);
                expanded=false;
            }else{
                item.findViewById(R.id.tiHabitat).setVisibility(View.VISIBLE);
                item.findViewById(R.id.tiDemografia).setVisibility(View.VISIBLE);
                item.findViewById(R.id.tiAmenazas).setVisibility(View.VISIBLE);
                expanded=true;
            }

        });
    }
}
