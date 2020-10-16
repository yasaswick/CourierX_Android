package com.courierx.courierx.Credit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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

public class ConfirmPay extends AppCompatActivity {

    TextView previous,payment,balance;
    Button confirmButton;
    UserDetailsSingleton userDetailsSingleton;
    FirebaseRealtime firebaseRealtime;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_payment);

        firebaseRealtime = new FirebaseRealtime();
        userDetailsSingleton = UserDetailsSingleton.getInstance();
        confirmButton = findViewById(R.id.confirmPaymentButton);
        previous = findViewById(R.id.user_credit_balance);
        payment = findViewById(R.id.user_payment_amount);
        balance = findViewById(R.id.user_new_credit_balance);

        Long userCredit = userDetailsSingleton.getCourierXUser().getBalance();
        final Long charge = Long.valueOf(200);
        final Long finalCredit = userCredit - charge;
        previous.setText(userCredit.toString());
        payment.setText(charge.toString());
        balance.setText(finalCredit.toString());

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreditLog creditLog = new CreditLog();
                creditLog.setAmount(charge);
                creditLog.setType("Delivery Charge");
                firebaseRealtime.addCredit(userDetailsSingleton.getCourierXUser().getUid(), creditLog, finalCredit);
                close();
            }
        });
    }

    public void close() {
        this.finish();
    }


}