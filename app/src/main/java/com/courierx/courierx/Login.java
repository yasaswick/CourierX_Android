package com.courierx.courierx;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.fragment.app.FragmentTransaction;

import java.util.concurrent.Executor;


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
        emailInput = v.findViewById(R.id.emailAddress);
        passwordInput = v.findViewById(R.id.password);
        signUpRedirect = v.findViewById(R.id.signUpRedirect);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               InflateViewSignUp();
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
        mAuth.signInWithEmailAndPassword(mail, psw)
                .addOnCompleteListener( getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("auth", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("auth", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



    void InflateViewSignUp(){
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.wrapper_frame, new SignUp());
        ft.commit();
    }



}

