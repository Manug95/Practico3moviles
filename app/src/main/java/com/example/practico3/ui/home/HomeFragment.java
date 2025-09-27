package com.example.practico3.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.practico3.R;
import com.example.practico3.databinding.FragmentHomeBinding;
import com.example.practico3.modelos.Producto;

import static com.example.practico3.MainActivity.listaProductos;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        homeViewModel.getMutableMensajeExito().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {
                binding.tvMensaje.setText(mensaje);
                binding.tvMensaje.setTextColor(getResources().getColor(R.color.green, null));
                vaciarCampos();
            }
        });

        homeViewModel.getMutableMensajeError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String mensajeError) {
                binding.tvMensaje.setText(mensajeError);
                binding.tvMensaje.setTextColor(getResources().getColor(R.color.error_red, null));
            }
        });

        binding.btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = binding.etCodigo.getText().toString();
                String descripcion = binding.etDescripcion.getText().toString();
                String precio = binding.etPrecio.getText().toString();
                homeViewModel.cargarProducto(codigo, descripcion, precio);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.tvMensaje.setText("");
    }

    private void vaciarCampos() {
        binding.etCodigo.setText("");
        binding.etDescripcion.setText("");
        binding.etPrecio.setText("");
        //binding.tvMensaje.setText("");
    }
}