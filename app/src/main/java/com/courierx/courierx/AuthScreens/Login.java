package com.courierx.courierx.AuthScreens;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.courierx.courierx.AuthScreens.SignUp;
import com.courierx.courierx.R;
import com.courierx.courierx.Services.FirebaseAuthentication;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends Fragment {
    private FirebaseAuth mAuth;

    TextView emailInput;
    TextView passwordInput;
    TextView signUpRedirect;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        Button button = v.findViewById(R.id.loginBtn);
        emailInput = v.findViewById(R.id.signInInputEmail);
        passwordInput = v.findViewById(R.id.signInInputPassword);
        signUpRedirect = v.findViewById(R.id.needAccountBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               loginWithEmail();
            }
        });
        signUpRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InflateViewSignUp();
            }
        });

        return v;
    }

    public void loginWithEmail(){
        String mail = emailInput.getText().toString();
        String psw = passwordInput.getText().toString();
        FirebaseAuthentication firebaseAuthentication = new FirebaseAuthentication();
        firebaseAuthentication.loginWithEmail(mail,psw, getContext());
    }



    void InflateViewSignUp(){
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.wrapper_frame, new SignUp());
        ft.commit();
    }



}

