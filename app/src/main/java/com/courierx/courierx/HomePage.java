package com.courierx.courierx;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomePage extends Fragment {


    Button sendTrackedPackage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        Button sendPackage = view.findViewById(R.id.send_pacakge_btn);
        sendPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPackage();
            }
        });
        return view;

    }


    void sendPackage(){
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.wrapper_frame, new Settings());
        ft.commit();
    }


}