package com.courierx.courierx.Services;

import android.util.Log;

import com.courierx.courierx.Interfaces.UserDataCallback;
import com.courierx.courierx.Models.CourierXUser;
import com.courierx.courierx.Models.CreditLog;
import com.courierx.courierx.Models.Package;
import com.courierx.courierx.Models.UserDetailsSingleton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class FirebaseRealtime {

    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userRef = database.getReference("user");
    DatabaseReference packageRef = database.getReference("package");
    private UserDataCallback userDataCallback;
    private CourierXUser courierXUser;

    public String uid;


    public FirebaseRealtime(){
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        courierXUser = new CourierXUser();
    }


    public void addPackage(Package pkg){

    }

    public void addCredit(Float amount, CreditLog creditLog){
        userRef.child(currentUser.getUid()).child("creditLog").setValue(creditLog);
        Map<String , Object> credit = new HashMap<>();
        credit.put("balance" , amount);
        userRef.child(currentUser.getUid()).updateChildren(credit);
    }

    public void registerUser(CourierXUser user){
        userRef.child(user.getUid()).setValue(user);
    }


    public void getUserDetails(){
        DatabaseReference myRef = database.getReference("user").child(currentUser.getUid());
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                courierXUser = dataSnapshot.getValue(CourierXUser.class);
                Log.d("TAG", "Value is: " + courierXUser);
                createUserSingleton(courierXUser);
                if(userDataCallback!=null){
                    userDataCallback.callback(courierXUser);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

    }

    public void createUserSingleton(CourierXUser courierXUser){
        UserDetailsSingleton userDetailsSingleton = UserDetailsSingleton.getInstance();
        userDetailsSingleton.setCourierXUser(courierXUser);
    }

    public void setUserDataCallback(UserDataCallback userDataCallback) {
        this.userDataCallback= userDataCallback;
    }

}
