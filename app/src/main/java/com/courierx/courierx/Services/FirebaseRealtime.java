package com.courierx.courierx.Services;

import com.courierx.courierx.Models.CourierXUser;
import com.courierx.courierx.Models.Package;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRealtime {

    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userRef = database.getReference("user");
    DatabaseReference packageRef = database.getReference("package");

    public String uid;

    FirebaseRealtime(){
        mAuth = FirebaseAuth.getInstance();
    }


    public void addPackage(Package pkg){


    }

    public void addCredit(){


    }


    public void registerUser(CourierXUser user){
        userRef.child(user.getUid()).setValue(user);

    }






}
