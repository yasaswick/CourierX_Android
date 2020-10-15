package com.courierx.courierx.Delivery;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.courierx.courierx.AuthScreens.Wrapper;
import com.courierx.courierx.R;
import com.courierx.courierx.Services.FirebaseAuthentication;


public class DeliverymanSettings extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deliveryman_settings, container, false);
        Button logoutBtn = view.findViewById(R.id.deliveryman_logout_button);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuthentication firebaseAuthentication = new FirebaseAuthentication();
                firebaseAuthentication.logOut(getContext());
                Intent intent = new Intent(getContext() , Wrapper.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }

        });
        return view;

    }

}