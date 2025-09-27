package com.example.practico3.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practico3.R;
import com.example.practico3.modelos.Producto;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {
    private ArrayList<Producto> lista;
    private Context context;
    private LayoutInflater layoutInflater;

    public ProductoAdapter(ArrayList<Producto> listaProductos, Context context, LayoutInflater layoutInflater) {
        this.context = context;
        this.lista = listaProductos;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item, parent, false);
        return new ProductoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        if (this.lista != null && getItemCount() > 0) {
            Producto producto = this.lista.get(position);
            holder.tvCodigo.setText(producto.getCodigo());
            holder.tvPrecio.setText(String.valueOf(producto.getPrecio()));
            holder.tvDescripcion.setText(producto.getDescripcion());
        } else {
            holder.tvCodigo.setText("Sin datos");
            holder.tvPrecio.setText("Sin datos");
            holder.tvDescripcion.setText("Sin datos");
        }
    }

    @Override
    public int getItemCount() {
        return this.lista == null ? 0 : this.lista.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigo, tvPrecio, tvDescripcion;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);

        }
    }
}
