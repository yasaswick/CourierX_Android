package com.courierx.courierx.EntryPoints;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.courierx.courierx.Interfaces.UserDataCallback;
import com.courierx.courierx.Models.CourierXUser;
import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.Packages.AddPackageDetails;
import com.courierx.courierx.R;
import com.courierx.courierx.Profile.Settings;
import com.courierx.courierx.SearchRecepient;
import com.courierx.courierx.Services.FirebaseAuthentication;
import com.courierx.courierx.Services.FirebaseRealtime;

public class HomePage extends Fragment {


    FirebaseRealtime firebaseRealtime;
    CourierXUser user;
    Button sendTrackedPackage;
    UserDetailsSingleton userDetailsSingleton;
    UserDataCallback userDataCallback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseRealtime = new FirebaseRealtime();
        userDetailsSingleton = UserDetailsSingleton.getInstance();
        user = new CourierXUser();
        Log.d("TAG", "User is: " + user);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        final Button sendPackage = view.findViewById(R.id.send_pacakge_btn);
        final TextView UserName = view.findViewById(R.id.textViewName);
        Log.d("TAG", "Value is: " + user);
        UserName.setText(userDetailsSingleton.getCourierXUser().getFirstName());

        sendPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FirebaseAuthentication firebaseAuthentication = new FirebaseAuthentication();
//                firebaseAuthentication.logOut(getContext());
                sendPackage();


            }
        });


        userDataCallback = new UserDataCallback() {
            @Override
            public void callback(CourierXUser courierXUser) {
                UserName.setText(courierXUser.getFirstName());
            }
        };

        return view;

    }


    void sendPackage(){
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.navHostFragment_user, new SearchRecepient());
        ft.commit();
    }

}