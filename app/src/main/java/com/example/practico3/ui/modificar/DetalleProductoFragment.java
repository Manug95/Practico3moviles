package com.example.practico3.ui.modificar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.practico3.R;
import com.example.practico3.databinding.FragmentDetalleProductoBinding;
import com.example.practico3.modelos.Producto;

public class DetalleProductoFragment extends Fragment {
    private FragmentDetalleProductoBinding binding;
    private DetalleProductoViewModel viewModel;
    private Producto productoSeleccionado;
    private String codigoBuscado;

    public static DetalleProductoFragment newInstance() {
        return new DetalleProductoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleProductoBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(DetalleProductoViewModel.class);

        binding.btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = binding.etModificarCodigo.getText().toString();
                String descripcion = binding.etModificarDescripcion.getText().toString();
                String precio = binding.etModificarPrecio.getText().toString();
                viewModel.modificarProducto(productoSeleccionado, codigo, descripcion, precio);
            }
        });

        viewModel.getMutableProducto().observe(getViewLifecycleOwner(), new Observer<Producto>() {
            @Override
            public void onChanged(Producto producto) {
                binding.etModificarCodigo.setText(producto.getCodigo());
                binding.etModificarDescripcion.setText(producto.getDescripcion());
                binding.etModificarPrecio.setText(String.valueOf(producto.getPrecio()));
                productoSeleccionado = producto;
            }
        });

        viewModel.getMutableMensajeExito().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {
                binding.tvMensajeModificar.setText(mensaje);
                binding.tvMensajeModificar.setTextColor(getResources().getColor(R.color.green, null));
            }
        });

        viewModel.getMutableMensajeError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {
                binding.tvMensajeModificar.setText(mensaje);
                binding.tvMensajeModificar.setTextColor(getResources().getColor(R.color.error_red, null));
            }
        });

        codigoBuscado = getArguments().getString("codigo");
        viewModel.buscarProducto(codigoBuscado);

        return binding.getRoot();
    }

}