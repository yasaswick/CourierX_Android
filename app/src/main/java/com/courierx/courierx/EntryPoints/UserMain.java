package com.courierx.courierx.EntryPoints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.courierx.courierx.AddPackageDetails;
import com.courierx.courierx.AuthScreens.Wrapper;
import com.courierx.courierx.Interfaces.UserDataCallback;
import com.courierx.courierx.Models.CourierXUser;
import com.courierx.courierx.Models.PackageDetails;
import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.R;
import com.courierx.courierx.Services.FirebaseAuthentication;
import com.courierx.courierx.Services.FirebaseRealtime;
import com.courierx.courierx.UserEditProfile;
import com.courierx.courierx.UserPackages;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

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
         navBalance.setText(userDetailsSingleton.getCourierXUser().getBalance().toString() + " LKR" );
         navUid.setText(userDetailsSingleton.getCourierXUser().getUid());


        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        findViewById(R.id.menuIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
        Log.d("TAG", "Changed!!" + userDetailsSingleton.getCourierXUser().getLastName());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.shareUserID:
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, userDetailsSingleton.getCourierXUser().getUid());
                        sendIntent.setType("text/plain");
                        Intent shareIntent = Intent.createChooser(sendIntent, null);
                        startActivity(shareIntent);
                        return true;
                    case R.id.supportPhone:
                        String number = "+94717329369";
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" +number));

                        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                            startActivity(intent);
                        } else {
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
                            Log.d("phone" , "no permission");
                        }
                        return true;

                    case R.id.githubPage:
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/yasaswick/CourierX_Android"));
                        startActivity(browserIntent);


                    case  R.id.editProfile:
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.navHostFragment_user, new UserEditProfile());
                        ft.addToBackStack(null);
                        ft.commit();
                        drawerLayout.closeDrawers();
                        return  true;
                }
                return true;
            }
        });



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


    }



    public void setUpNavigation(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view_user);
        NavController navController = Navigation.findNavController(findViewById(R.id.navHostFragment_user));
        NavigationUI.setupWithNavController(bottomNavigationView , navController );
    }

}