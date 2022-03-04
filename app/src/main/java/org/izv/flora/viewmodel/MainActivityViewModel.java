package org.izv.flora.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.izv.flora.model.Repository;
import org.izv.flora.model.entity.Flora;

import java.util.ArrayList;

public class MainActivityViewModel extends AndroidViewModel {

    private Repository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
    }


    public MutableLiveData<ArrayList<Flora>> getFloraLiveData() {
        return repository.getFloraLiveData();
    }

    public void getFlora() {
        repository.getFlora();
    }

}