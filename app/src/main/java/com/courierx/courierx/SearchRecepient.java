package com.courierx.courierx;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

        serch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String receiverID = recepient.getText().toString();
                data = new Bundle();
                data.putString("receiverID", receiverID);
                detailedViewFragment();
            }
        });
        

        return view;
    }

    public void detailedViewFragment(){
        AddPackageDetails addPackageDetails = new AddPackageDetails();
        addPackageDetails.setArguments(data);
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.navHostFragment_user, addPackageDetails);
        ft.commit();
    }
}