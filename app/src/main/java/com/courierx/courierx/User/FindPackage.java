package com.courierx.courierx.User;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.R;

public class FindPackage extends Fragment {
    String st;
    TextView locationStatus;
    TextView packageId;
    Bundle data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_find_package, container, false);

        locationStatus = view.findViewById(R.id.locationStatus);
        packageId = view.findViewById(R.id.tracking_id_user);
        data = this.getArguments();
        if(data != null){
            st = data.getString("packageID");
            packageId.setText(st);
        }
        return view;





    }
}