package com.example.practico3.ui.gallery;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.practico3.databinding.FragmentGalleryBinding;
import com.example.practico3.modelos.Producto;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        galleryViewModel.getMutableListaProductos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Producto>>() {
            @Override
            public void onChanged(ArrayList<Producto> productos) {
                ProductoAdapter adapter = new ProductoAdapter(productos, getContext(), getLayoutInflater());
                GridLayoutManager glm = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                binding.rvListaProductos.setLayoutManager(glm);
                binding.rvListaProductos.setAdapter(adapter);
            }
        });

        galleryViewModel.cargarListaProductos();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}