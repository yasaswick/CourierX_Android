package com.courierx.courierx.Delivery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.courierx.courierx.AuthScreens.SignUp;
import com.courierx.courierx.R;
import com.courierx.courierx.Track.Location;


public class DeliverymanSearch extends Fragment {

    private EditText getSetPackageId;
    private Button setDeliveryBtn;
    private Bundle data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_set_delivery, container, false);

       getSetPackageId = view.findViewById(R.id.getSetPackageID);
       setDeliveryBtn = view.findViewById(R.id.setDeliveryDoneBtn);

       setDeliveryBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String packageID = getSetPackageId.getText().toString();

               data = new Bundle();
               data.putString("packageID" , packageID);
               detailedViewFragment();

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