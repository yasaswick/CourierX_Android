package com.courierx.courierx.AuthScreens;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.courierx.courierx.R;
import com.google.firebase.auth.FirebaseAuth;


public class SignUp extends Fragment {

    private FirebaseAuth mAuth;

    TextView logInRedirect;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        logInRedirect = v.findViewById(R.id.signInBtn);
        logInRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InflateViewLogin();
            }
        });
        return v;

    }


    void InflateViewLogin(){
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.wrapper_frame, new Login());
        ft.commit();
    }



}