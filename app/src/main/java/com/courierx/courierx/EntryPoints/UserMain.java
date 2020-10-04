package com.courierx.courierx.EntryPoints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.courierx.courierx.AuthScreens.Wrapper;
import com.courierx.courierx.Interfaces.UserDataCallback;
import com.courierx.courierx.Models.CourierXUser;
import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.R;
import com.courierx.courierx.Services.FirebaseAuthentication;
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
        TextView navBalance = headerView.findViewById(R.id.drawer_profile_balance);
        TextView navUid = headerView.findViewById(R.id.drawer_profile_uid);
        Button logoutBtn = navigationView.findViewById(R.id.nav_drawer_logout_btn);


         navUsername.setText(userDetailsSingleton.getCourierXUser().getFirstName() + " " + userDetailsSingleton.getCourierXUser().getLastName());
         navEmail.setText(userDetailsSingleton.getCourierXUser().getEmail());
         navBalance.setText(userDetailsSingleton.getCourierXUser().getBalance().toString());
         navUid.setText(userDetailsSingleton.getCourierXUser().getUid());

        Log.d("TAG", "Changed!!" + userDetailsSingleton.getCourierXUser().getLastName());

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuthentication firebaseAuthentication = new FirebaseAuthentication();
                firebaseAuthentication.logOut(getBaseContext());
                Intent intent = new Intent(getApplicationContext() , Wrapper.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });



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