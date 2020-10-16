package com.courierx.courierx.Credit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.courierx.courierx.Models.CreditLog;
import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.R;
import com.courierx.courierx.Services.FirebaseRealtime;
import com.courierx.courierx.UserPackages;

public class ConfirmPay extends Fragment {

    TextView previous,payment,balance;
    Button confirmButton;
    UserDetailsSingleton userDetailsSingleton;
    FirebaseRealtime firebaseRealtime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_confirm_pay, container, false);
        firebaseRealtime = new FirebaseRealtime();

        userDetailsSingleton = UserDetailsSingleton.getInstance();
        confirmButton = view.findViewById(R.id.setDeliveryDoneBtn);
        previous = view.findViewById(R.id.user_credit_balance);
        payment = view.findViewById(R.id.user_payment_amount);
        balance= view.findViewById(R.id.user_credit_balance);

        Long userCredit = userDetailsSingleton.getCourierXUser().getBalance();
        Long charge = Long.valueOf(200);
        Long finalCredit = userCredit - charge;
        previous.setText(userCredit.toString());
        payment.setText(charge.toString());
        balance.setText(finalCredit.toString());
        CreditLog creditLog = new CreditLog();
        creditLog.setAmount(charge);
        creditLog.setType("Delivery Charge");
        firebaseRealtime.addCredit(userDetailsSingleton.getCourierXUser().getUid(), creditLog , finalCredit);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listViewFragment();
            }
        });



        return  view;

    }


    public void listViewFragment() {
        UserPackages userPackages = new UserPackages();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.navHostFragment_user, userPackages);
        fragmentTransaction.commit();
    }


}