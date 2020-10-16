package com.courierx.courierx.Delivery;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.courierx.courierx.AuthScreens.SignUp;
import com.courierx.courierx.R;
import com.courierx.courierx.Track.Location;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DeliverymanSearch extends Fragment {
    private EditText getSetPackageId;
    private Button setDeliveryBtn;
    private Bundle data;
    private Button cancelBtn;
    DatabaseReference ref;
    Query query;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_set_delivery, container, false);

       getSetPackageId = view.findViewById(R.id.getSetPackageID);
       setDeliveryBtn = view.findViewById(R.id.setDeliveryDoneBtn);

       cancelBtn = view.findViewById(R.id.button2);

       setDeliveryBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(final View view) {
               if(getSetPackageId.getText().toString().trim().equalsIgnoreCase("")){
                   getSetPackageId.requestFocus();
                   getSetPackageId.setError("Please enter the package Id you want to find!");
               }


               final String packageID = getSetPackageId.getText().toString();
               ref = FirebaseDatabase.getInstance().getReference().child("packages");
               query = ref.orderByChild("packageId").equalTo(packageID);

               query.addValueEventListener(new ValueEventListener() {

                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if(!snapshot.exists()){
                          getSetPackageId.requestFocus();
                           Snackbar.make(view,"No such package!", Snackbar.LENGTH_LONG).show();

                       } else {
                           data = new Bundle();
                           data.putString("packageID", packageID);
                           detailedViewFragment();
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });

           }
       });
        cancelBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        return view;
    }
    public void detailedViewFragment(){

        Location location = new Location();
        location.setArguments(data);
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.bottom_nav_fragment_delivery, location);
        ft.commit();
    }
    public void onBackPressed(){
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.disallowAddToBackStack();
        ft.replace(R.id.bottom_nav_fragment_delivery, new DeliverymanHome());
        ft.commit();
    }
}