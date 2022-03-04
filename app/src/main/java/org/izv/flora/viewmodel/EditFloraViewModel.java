package org.izv.flora.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.izv.flora.model.Repository;
import org.izv.flora.model.entity.Flora;


public class EditFloraViewModel extends AndroidViewModel {
    private Repository repository;

    public EditFloraViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void deleteFlora(long id) {
        repository.deleteFlora(id);
    }
    public void editFlora(long id, Flora flora) {
        repository.editFlora(id, flora);
    }
}