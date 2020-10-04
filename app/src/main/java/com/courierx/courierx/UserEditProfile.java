package com.courierx.courierx;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.courierx.courierx.Models.CourierXUser;
import com.courierx.courierx.Services.FirebaseAuthentication;
import com.courierx.courierx.Services.FirebaseRealtime;
import com.google.android.material.snackbar.Snackbar;


public class UserEditProfile extends Fragment {


    Button userProfileSaveBtn, userProfileResetPassword;
    FirebaseAuthentication firebaseAuthentication;
    FirebaseRealtime firebaseRealtime;
    CourierXUser courierXUser;
    EditText fname,lname,address,phone;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_edit_profile, container, false);
        userProfileResetPassword = view.findViewById(R.id.userProfileRestPassBtn);
        userProfileSaveBtn = view.findViewById(R.id.userProfileSaveBtn);

        fname = view.findViewById(R.id.profileEditFirstName);
        lname = view.findViewById(R.id.profileEditLastName);
        address = view.findViewById(R.id.profileEditAddress);
        phone =view.findViewById(R.id.profileEditPhone);


        firebaseAuthentication = new FirebaseAuthentication();
        firebaseRealtime = new FirebaseRealtime();

        courierXUser = new CourierXUser();

        userProfileResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuthentication.sendResetEmail();
                Snackbar snackbar = Snackbar
                        .make(view, "Password reset instructions sent to your email !", Snackbar.LENGTH_LONG);
                snackbar.show();

            }
        });

        userProfileSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                courierXUser.setFirstName(fname.getText().toString());
                courierXUser.setLastName(lname.getText().toString());
                courierXUser.setPhoneNumber(phone.getText().toString());
                courierXUser.setAddress(address.getText().toString());
                firebaseRealtime.updateUser(courierXUser);
                Snackbar snackbar = Snackbar.make(view, "Updated Successfully", Snackbar.LENGTH_LONG);
                snackbar.show();
            }




        });





        return view;
    }












}