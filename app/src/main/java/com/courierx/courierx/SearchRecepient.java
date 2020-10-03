package com.courierx.courierx;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SearchRecepient extends Fragment {

    EditText recepient;
    Button serch;
    Bundle data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search_recepient, container, false);
        recepient = view.findViewById(R.id.editTextTextPersonName);
        serch = view.findViewById(R.id.buttonsrch);
        

        return view;
    }
}