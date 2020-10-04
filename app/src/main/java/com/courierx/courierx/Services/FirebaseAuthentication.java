package com.courierx.courierx.Services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.courierx.courierx.AuthScreens.Selector;
import com.courierx.courierx.AuthScreens.Wrapper;
import com.courierx.courierx.Models.CourierXUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class FirebaseAuthentication {

    private FirebaseAuth mAuth;
    private FirebaseRealtime firebaseRealtime;



    public FirebaseAuthentication(){
        mAuth = FirebaseAuth.getInstance();
        firebaseRealtime = new FirebaseRealtime();
    }


    public void loginWithEmail(String email, String password , final Context mContext){
        Activity activity = (Activity) mContext;
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( activity , new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("auth", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(mContext , Wrapper.class);
                            mContext.startActivity(intent);
                            ((Activity) mContext).finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("auth", "signInWithEmail:failure", task.getException());
                            Toast.makeText(mContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void logOut(Context mContext){
        mAuth.signOut();
//        Intent intent = new Intent(mContext , Wrapper.class);
//
//        mContext.startActivity(intent);
//        ((Activity) mContext).finish();
    }

    public void registerUser(final CourierXUser courierXUseruser, String password , final Context mContext){
        final Activity activity = (Activity) mContext;
        mAuth.createUserWithEmailAndPassword(courierXUseruser.getEmail(), password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Auth", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            courierXUseruser.setUid(user.getUid());
                            firebaseRealtime.registerUser(courierXUseruser);
                            Toast.makeText(activity, "User Created!.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(mContext , Wrapper.class);
                            mContext.startActivity(intent);
                            ((Activity) mContext).finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Auth", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void sendResetEmail(){
        mAuth.sendPasswordResetEmail(mAuth.getCurrentUser().getEmail());
    }





}
