package com.courierx.courierx.Packages;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.courierx.courierx.Models.Package;
import com.courierx.courierx.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PackageDetails extends Fragment {

    EditText description, weight, sheduledDate;
    Button button2;
    TextView packageId;
    CheckBox fragile;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("user").child("hseuiahfuabfbduahsdoi").child("package");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_package_details, container, false);
        packageId = view.findViewById(R.id.packageID);
        description = view.findViewById(R.id.description);
        weight = view.findViewById(R.id.weight);
        sheduledDate = view.findViewById(R.id.sheduleDate);
        fragile = view.findViewById(R.id.fragile);
        button2 = view.findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Package pkg = new Package("23123","sdfasdf",23,"11/11/2020",true);
                myRef.setValue(pkg);
            }
        });

        return view;
    }
}