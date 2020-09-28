package com.courierx.courierx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class UserMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

    findViewById(R.id.menuIcon).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    });

        NavigationView navigationView = findViewById(R.id.navigationView);
        NavController navController = Navigation.findNavController(findViewById(R.id.navHostFragment));
        NavigationUI.setupWithNavController(navigationView , navController );
    }
}