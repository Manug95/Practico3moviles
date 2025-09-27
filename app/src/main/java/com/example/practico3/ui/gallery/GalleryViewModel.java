package com.example.practico3.ui.gallery;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.practico3.modelos.Producto;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.practico3.MainActivity.listaProductos;

public class GalleryViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Producto>> mutableListaProductos;

    public GalleryViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Producto>> getMutableListaProductos() {
        if (mutableListaProductos == null) {
            mutableListaProductos = new MutableLiveData<>();
        }
        return mutableListaProductos;
    }

    public void cargarListaProductos() {
        if (listaProductos.isEmpty()) {
            listaProductos.add(new Producto("C-2", 200.0, "BProducto 2"));
            listaProductos.add(new Producto("C-1", 100.0, "AProducto 1"));
            listaProductos.add(new Producto("C-4", 400.0, "DProducto 4"));
            listaProductos.add(new Producto("C-3", 300.0, "CProducto 3"));
        }

        Collections.sort(listaProductos);
        mutableListaProductos.setValue(listaProductos);
    }

}