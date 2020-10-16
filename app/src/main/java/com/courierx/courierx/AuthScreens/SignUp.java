package com.courierx.courierx.AuthScreens;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.courierx.courierx.Models.CourierXUser;
import com.courierx.courierx.Models.CreditLog;
import com.courierx.courierx.R;
import com.courierx.courierx.Services.FirebaseAuthentication;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class SignUp extends Fragment {

    TextView logInRedirect;
    Button signUpBtn;
    FirebaseAuthentication firebaseAuthentication;
    EditText password,email,fname,lname,address,phone ,passwordRepeat;
    CourierXUser courierXUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuthentication = new FirebaseAuthentication();
        courierXUser = new CourierXUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        logInRedirect = v.findViewById(R.id.signInBtn);
        email = v.findViewById(R.id.signUpEmail);
        password = v.findViewById(R.id.signUpPassRepeat);
        phone = v.findViewById(R.id.signUpPhone);
        address = v.findViewById(R.id.signUpAddress);
        fname = v.findViewById(R.id.signUpFirstName);
        lname = v.findViewById(R.id.signUpLastName);
        passwordRepeat = v.findViewById(R.id.signUpPass);


        logInRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InflateViewLogin();
            }
        });

        signUpBtn = v.findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pswd = password.getText().toString().trim();
                String repeat = passwordRepeat.getText().toString().trim();
                String useremail = email.getText().toString().trim();
                String firstname = fname.getText().toString().trim();
                String lastname = lname.getText().toString().trim();
                String mobile = phone.getText().toString().trim();
                String useraddress = address.getText().toString().trim();

                if(validatePassword(pswd,repeat)){
                    if(passwordMatch(pswd,repeat)){
                        if(validateEmail(useremail)){

                            if(fieldValidate(firstname)&& fieldValidate(lastname)&& fieldValidate(mobile)&& fieldValidate(useraddress)){


                                courierXUser.setEmail(email.getText().toString());
                                courierXUser.setPhoneNumber(phone.getText().toString());
                                courierXUser.setFirstName(fname.getText().toString());
                                courierXUser.setLastName(lname.getText().toString());
                                courierXUser.setAddress(address.getText().toString());
                                courierXUser.setRole("user");
                                firebaseAuthentication.registerUser(courierXUser,pswd,getContext());



                            }else{
                                Snackbar.make(view,"Please fill all fields!",Snackbar.LENGTH_SHORT).show();
                            }

                        }else{
                            Snackbar.make(view,"Please enter valid email!",Snackbar.LENGTH_SHORT).show();
                        }
                    } else {
                        Snackbar.make(view,"Passwords Doesn't match!",Snackbar.LENGTH_SHORT).show();
                    }
                }else {
                    Snackbar.make(view,"Password should be more than 8 characters!",Snackbar.LENGTH_SHORT).show();
                }

            }
        });
        return v;

    }


    void InflateViewLogin(){
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.wrapper_frame, new Login());
        ft.commit();
    }


    public boolean validateEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }


    public boolean validatePassword(String password, String repeat){
        if(password.length()>8 && repeat.length()>8){
            return true;
        }else {
            return false;
        }
    }

    public boolean passwordMatch(String password, String repeat){
        if(password.equals(repeat)){
            return true;
        }else {
            return false;
        }
    }

    public boolean fieldValidate(String message){
        if (!message.isEmpty()){
           return true;
        } else {
            return false;
        }
    }


}