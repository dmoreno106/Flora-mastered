package org.izv.flora.viewmodel;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import org.izv.flora.model.Repository;
import org.izv.flora.model.entity.Imagen;

public class AddImagenViewModel extends AndroidViewModel {

    private Repository repository;

    public void saveImagen(Intent intent, Imagen imagen) {
        repository.saveImagen(intent, imagen);
    }

    public AddImagenViewModel(@NonNull Application application) {
        super(application);

        repository= new Repository(application);


    }
}
