package com.example.practico3.ui.modificar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.practico3.R;
import com.example.practico3.databinding.FragmentModificarBinding;
import com.example.practico3.modelos.Producto;

public class ModificarFragment extends Fragment {
    private FragmentModificarBinding binding;
    private ModificarViewModel viewModel;

    public static ModificarFragment newInstance() {
        return new ModificarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentModificarBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(ModificarViewModel.class);

        binding.btnBuscarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = binding.etBuscarProducto.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("codigo", codigo);
                Navigation
                        .findNavController(getActivity(), R.id.nav_host_fragment_content_main)
                        .navigate(R.id.detalleProductoFragment, bundle);
            }
        });


        return binding.getRoot();
    }

}