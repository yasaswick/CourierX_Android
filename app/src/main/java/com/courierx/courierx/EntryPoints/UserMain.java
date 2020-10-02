package com.courierx.courierx.EntryPoints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.courierx.courierx.Interfaces.UserDataCallback;
import com.courierx.courierx.Models.CourierXUser;
import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.R;
import com.courierx.courierx.Services.FirebaseRealtime;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class UserMain extends AppCompatActivity {

    UserDetailsSingleton userDetailsSingleton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDetailsSingleton = UserDetailsSingleton.getInstance();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.userNameText);
        TextView navEmail = headerView.findViewById(R.id.userEmailText);
        navUsername.setText(userDetailsSingleton.getCourierXUser().getFirstName() + " " + userDetailsSingleton.getCourierXUser().getLastName());
         navEmail.setText(userDetailsSingleton.getCourierXUser().getEmail());
         navEmail.setText(userDetailsSingleton.getCourierXUser().getEmail());
        Log.d("TAG", "Changed!!" + userDetailsSingleton.getCourierXUser().getLastName());


        setUpNavigation();
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
    findViewById(R.id.menuIcon).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            drawerLayout.openDrawer(GravityCompat.START);


            
        }
    });

    }



    public void setUpNavigation(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view_user);
        NavController navController = Navigation.findNavController(findViewById(R.id.navHostFragment_user));
        NavigationUI.setupWithNavController(bottomNavigationView , navController );
    }

}