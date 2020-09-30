package com.courierx.courierx.AuthScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.courierx.courierx.Models.CourierXUser;
import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.R;
import com.courierx.courierx.Services.FirebaseRealtime;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Wrapper extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseRealtime firebaseRealtime;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userRef = database.getReference("user");
    FirebaseUser currentUser;
    CourierXUser courierXUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrapper);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        firebaseRealtime = new FirebaseRealtime();
        //getUserDetails();
        if (currentUser != null){
            firebaseRealtime.getUserDetails();
            DatabaseReference myRef = database.getReference("user").child(currentUser.getUid());
            myRef.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    courierXUser = dataSnapshot.getValue(CourierXUser.class);
                    Log.d("TAG", "Value is: " + courierXUser);
                    if (courierXUser.getFirstName() != null) {
                        Log.d("TAG", "have user");
                        Intent intent = new Intent(Wrapper.this, Selector.class);
                        startActivity(intent);
                        Wrapper.this.finish();
                    }else {
                        Log.d("TAG", "no user");

                    }

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w("TAG", "Failed to read value.", error.toException());
                }
            });

        }else {
            InflateViewLogin();
        }



    }




    void InflateViewLogin(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.wrapper_frame, new Login());
        ft.commit();
    }




}