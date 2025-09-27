package com.example.practico3.ui.slideshow;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.practico3.R;
import com.example.practico3.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        muestraDialog();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void muestraDialog(){

        new AlertDialog.Builder(getContext())
            .setTitle(R.string.titulo_dialog_salir)
            .setMessage(R.string.pregunta_dialog_salir)
            .setPositiveButton(R.string.dialog_si,new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface di,int i){
                    getActivity().finish(); }
            })

            .setNegativeButton(R.string.dialog_no,new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface di,int i){
                    //No hace nada
                }
            }).show();


    }

}