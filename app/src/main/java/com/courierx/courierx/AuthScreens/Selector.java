package com.courierx.courierx.AuthScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.courierx.courierx.R;
import com.courierx.courierx.EntryPoints.AdminMain;
import com.courierx.courierx.EntryPoints.DeliveryMain;
import com.courierx.courierx.EntryPoints.UserMain;

public class Selector extends AppCompatActivity implements View.OnClickListener {

    Button adminBtn;
    Button userBtn;
    Button deliveryBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        adminBtn = findViewById(R.id.adminSelectorBtn);
        adminBtn.setOnClickListener(this);
        userBtn = findViewById(R.id.userSelectorBtn);
        userBtn.setOnClickListener(this);
        deliveryBtn = findViewById(R.id.deliverySelectorBtn);
        deliveryBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.adminSelectorBtn:
                Intent intent0 = new Intent(this, AdminMain.class);
                startActivity(intent0);
                break;
            case  R.id.userSelectorBtn:
                Intent intent1 = new Intent(this, UserMain.class);
                startActivity(intent1);
                break;
            case R.id.deliverySelectorBtn:
                Intent intent2 = new Intent(this, DeliveryMain.class);
                startActivity(intent2);
                break;
        }

    }
}