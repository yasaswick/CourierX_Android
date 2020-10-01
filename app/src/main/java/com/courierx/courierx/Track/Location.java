package com.courierx.courierx.Track;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.courierx.courierx.Models.PackageDetails;
import com.courierx.courierx.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Location extends Fragment {
    String st;
    TextView locationStatus;
    TextView packageId;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);


        locationStatus = view.findViewById(R.id.locationStatus);
        packageId = view.findViewById(R.id.packageid);
        Bundle data = this.getArguments();
        if(data != null){
            st = data.getString("packageID");
        }
        Log.d("test","your package is tracked "+ st);


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("packages").child(st);
        //Query query=mDatabase.orderByChild("packageId").equalTo(st);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("data" , "value  : " + snapshot.getValue(PackageDetails.class).getStatus());
                locationStatus.setText(snapshot.getValue(PackageDetails.class).getStatus());
                packageId.setText(snapshot.getValue(PackageDetails.class).getPackageId());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }


}