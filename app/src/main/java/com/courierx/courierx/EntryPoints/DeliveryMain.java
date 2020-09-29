package com.courierx.courierx.EntryPoints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.courierx.courierx.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DeliveryMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_main);
        setUpNavigation();

    }
    public void setUpNavigation(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView_delivery);
        NavController navController = Navigation.findNavController(findViewById(R.id.bottom_nav_fragment_delivery));
        NavigationUI.setupWithNavController(bottomNavigationView , navController );
    }

}