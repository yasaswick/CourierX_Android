package com.courierx.courierx.Services;

import com.courierx.courierx.Models.Package;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRealtime {

    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userRed = database.getReference("user");
    DatabaseReference packageRef = database.getReference("package");

    public String uid;

    FirebaseRealtime(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        this.uid = currentUser.getUid().toString();
    }


    public void addPackage(Package pkg){



    }


    public void addCredit(){


    }






}
