package org.izv.flora.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<Flora> floraList;
    private View.OnClickListener listener;
    private  Boolean expanded;
    public Adapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        view.setOnClickListener(listener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

         String ivURL = "https://informatica.ieszaidinvergeles.org:10012/ad/felixRDLFApp/public/api/imagen/";

        Flora flora = floraList.get(position);
        holder.etNombre.setText(flora.getNombre());
        holder.etFamilia.setText(flora.getFamilia());
        holder.etDemografia.setText(flora.getDemografia());
        holder.etHabitat.setText(flora.getHabitat());
        holder.etAmenazas.setText(flora.getAmenazas());
        Picasso.get().load(ivURL + flora.getId() + "/flora")
                .memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE).into(holder.ivFlora);


    }

    @Override
    public int getItemCount() {
        if (floraList == null) {
            return 0;
        }
        return floraList.size();
    }

    public void setFloraList(List<Flora> floraList) {
        this.floraList = floraList;
        notifyDataSetChanged();
    }


    public Flora getItem(int poisition) {
        return floraList.get(poisition);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

}