package com.courierx.courierx.AuthScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.R;
import com.courierx.courierx.Services.FirebaseRealtime;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Wrapper extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseRealtime firebaseRealtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrapper);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        firebaseRealtime = new FirebaseRealtime();
        if (currentUser != null){
            Intent intent = new Intent(this, Selector.class);
            startActivity(intent);
            firebaseRealtime.getUserDetails();
            this.finish();
        }else {
            InflateViewLogin();
        }



    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }


    void InflateViewLogin(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.wrapper_frame, new Login());
        ft.commit();
    }
}