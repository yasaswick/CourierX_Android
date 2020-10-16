package com.courierx.courierx;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.courierx.courierx.Models.PackageDetails;
import com.courierx.courierx.Models.TrackInfo;
import com.courierx.courierx.Services.FirebaseRealtime;
import com.courierx.courierx.Track.Location;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;


public class UpdateLocationDetails extends Fragment {

    EditText location;
    Button button;
    Bundle data;
    String packageid;
    Switch sButton;
    Switch sButton2;
    FirebaseRealtime firebaseRealtime;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_location_details, container, false);
        location = view.findViewById(R.id.addLocation);
        button = view.findViewById(R.id.updateLocationBtn);
        firebaseRealtime = new FirebaseRealtime();
        TextView packageIdtext = view.findViewById(R.id.update_location_detail_packageid);
        sButton = view.findViewById(R.id.switch1);
        sButton2 = view.findViewById(R.id.switch2);

        data = this.getArguments();
        if(data != null){
            packageid = data.getString("packageID");
            packageIdtext.setText(packageid);
        }
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("packages");
        Query query=mDatabase.orderByChild("packageId").equalTo(packageid);

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    if(ds.getValue(PackageDetails.class).getStatus()=="Picked"){
                        sButton2.setChecked(true);
                    }
                    else if(ds.getValue(PackageDetails.class).getStatus()=="Delivered"){
                        sButton.setChecked(true);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageDetails packageDetails = new PackageDetails();
                packageDetails.setPackageId(packageid);
                TrackInfo trackInfo = new TrackInfo();

                if(sButton.isChecked()){
                    packageDetails.setStatus("Delivered");
                }

                if(sButton2.isChecked()){
                        packageDetails.setStatus("Picked");
                }


                if(location.getText().toString().trim().equalsIgnoreCase("")){
                    location.requestFocus();
                    location.setError("Please enter the location of package!");
                }
                else {

                    trackInfo.setLocation(location.getText().toString());


                    trackInfo.setDate(System.currentTimeMillis());
                    firebaseRealtime.setPackageLocation(packageDetails, trackInfo);

                    detailedViewFragment();
                }
            }
        });

        return view;
    }

    public void detailedViewFragment(){
        Location location = new Location();
        location.setArguments(data);
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.bottom_nav_fragment_delivery, location);
        ft.commit();
    }


}