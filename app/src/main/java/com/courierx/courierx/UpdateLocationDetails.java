package com.courierx.courierx;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.courierx.courierx.Models.PackageDetails;
import com.courierx.courierx.Models.TrackInfo;
import com.courierx.courierx.Services.FirebaseRealtime;
import com.courierx.courierx.Track.Location;
import com.google.firebase.database.ServerValue;


public class UpdateLocationDetails extends Fragment {

    EditText location;
    Button button;
    Bundle data;
    String packageid;
    FirebaseRealtime firebaseRealtime;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_location_details, container, false);
        location = view.findViewById(R.id.addLocation);
        button = view.findViewById(R.id.updateLocationBtn);
        firebaseRealtime = new FirebaseRealtime();
        TextView packageIdtext = view.findViewById(R.id.update_location_detail_packageid);

        data = this.getArguments();
        if(data != null){
            packageid = data.getString("packageID");
            packageIdtext.setText(packageid);
        }



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageDetails packageDetails = new PackageDetails();
                packageDetails.setPackageId(packageid);
                TrackInfo trackInfo = new TrackInfo();
                trackInfo.setLocation(location.getText().toString());
                trackInfo.setDate(System.currentTimeMillis());
            firebaseRealtime.setPackageLocation(packageDetails,trackInfo);
            }
        });


        return view;
    }

}