package com.example.practico3.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.practico3.R;
import com.example.practico3.modelos.Producto;

import static com.example.practico3.MainActivity.listaProductos;

import android.app.Application;

public class HomeViewModel extends AndroidViewModel {
    private MutableLiveData<String> mutableMensajeExito;
    private MutableLiveData<String> mutableMensajeError;

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMutableMensajeError() {
        if (mutableMensajeError == null) mutableMensajeError = new MutableLiveData<>();
        return mutableMensajeError;
    }

    public LiveData<String> getMutableMensajeExito() {
        if (mutableMensajeExito == null) mutableMensajeExito = new MutableLiveData<>();
        return mutableMensajeExito;
    }

    public void cargarProducto(String codigo, String descripcion, String precio) {
        String mensajeError = Producto.validarCodigo(codigo);
        mensajeError += Producto.validarDescripcion(descripcion);
        mensajeError += Producto.validarPrecio(precio);
        if (mensajeError.isEmpty()) {
            Producto producto = new Producto(codigo, Double.parseDouble(precio), descripcion);
            if (listaProductos.contains(producto))
                mutableMensajeError.setValue(getApplication().getResources().getString(R.string.producto_existente));
            else{
                listaProductos.add(producto);
                mutableMensajeExito.setValue(getApplication().getResources().getString(R.string.cargado_con_exito));
            }
        } else {
            mutableMensajeError.setValue(mensajeError);
        }
    }
}