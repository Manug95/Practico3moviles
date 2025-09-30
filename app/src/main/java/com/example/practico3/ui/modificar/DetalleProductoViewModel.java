package com.example.practico3.ui.modificar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.practico3.modelos.Producto;
import static com.example.practico3.MainActivity.listaProductos;

public class DetalleProductoViewModel extends AndroidViewModel {
    private MutableLiveData<String> mutableMensajeExito;
    private MutableLiveData<String> mutableMensajeError;
    private MutableLiveData<Producto> mutableProducto;
    public DetalleProductoViewModel(@NonNull Application application) {
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

    public LiveData<Producto> getMutableProducto(){
        if(mutableProducto == null){
            mutableProducto = new MutableLiveData<>();
        }
        return mutableProducto;
    }

    public void modificarProducto(Producto productoViejo, String codigo, String descripcion, String precio) {
        String mensajeError = Producto.validarCodigo(codigo);
        mensajeError += Producto.validarDescripcion(descripcion);
        mensajeError += Producto.validarPrecio(precio);
        if (mensajeError.isEmpty()) {
            int indice = listaProductos.indexOf(productoViejo);
            if (indice >= 0) {
                listaProductos.set(indice, new Producto(codigo, Double.parseDouble(precio), descripcion));
                mutableMensajeExito.setValue("Producto modificado con éxito");
            } else {
                mutableMensajeError.setValue("Error al modificar el producto");
            }
        }else {
            mutableMensajeError.setValue(mensajeError);
        }
    }

    public void buscarProducto(String codigo){
        for(Producto p : listaProductos){
            if(p.getCodigo().equalsIgnoreCase(codigo)){
                mutableProducto.setValue(p);
                return;
            }
        }
        mutableMensajeError.setValue("El producto no existe");
    }
}