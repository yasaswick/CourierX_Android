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
                String pswd = password.getText().toString();
                courierXUser.setEmail(email.getText().toString());
                courierXUser.setPhoneNumber(phone.getText().toString());
                courierXUser.setFirstName(fname.getText().toString());
                courierXUser.setLastName(lname.getText().toString());
                courierXUser.setAddress(address.getText().toString());
                courierXUser.setRole("user");
                courierXUser.setBalance((long) 1000.00);
                CreditLog credit= new CreditLog();
                credit.setDate("11/11/2010");
                credit.setAmount((long)1000);
                credit.setType("Joining Bonus");
                List<CreditLog> creditLog = new ArrayList<CreditLog>();
                creditLog.add(credit);
                courierXUser.setCreditLog(creditLog);
                firebaseAuthentication.registerUser(courierXUser,pswd,getContext());
            }
        });
        return v;

    }


    void InflateViewLogin(){
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.wrapper_frame, new Login());
        ft.commit();
    }



}