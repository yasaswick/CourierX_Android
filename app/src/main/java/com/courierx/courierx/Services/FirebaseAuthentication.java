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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class FirebaseAuthentication {

    private FirebaseAuth mAuth;



    public FirebaseAuthentication(){
        mAuth = FirebaseAuth.getInstance();
    }


    public void loginWithEmail(String email, String password , final Context mcontext){
        Activity activity = (Activity) mcontext;
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( activity , new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("auth", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(mcontext , Selector.class);
                            mcontext.startActivity(intent);
                            ((Activity) mcontext).finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("auth", "signInWithEmail:failure", task.getException());
                            Toast.makeText(mcontext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



    public void logOut(Context mcontext){
        mAuth.signOut();
        Intent intent = new Intent(mcontext , Wrapper.class);
        mcontext.startActivity(intent);
    }



}
