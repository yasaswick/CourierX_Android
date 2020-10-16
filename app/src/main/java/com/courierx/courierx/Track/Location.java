package com.courierx.courierx.Track;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.courierx.courierx.Delivery.DeliveryListViewHolder;
import com.courierx.courierx.Delivery.DeliveryLocationListViewHolder;
import com.courierx.courierx.Models.PackageDetails;
import com.courierx.courierx.Models.TrackInfo;
import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.R;
import com.courierx.courierx.UpdateLocationDetails;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Location extends Fragment {
    String st;
    TextView locationStatus;
    TextView packageId;
    UserDetailsSingleton userDetailsSingleton ;
    Bundle data;
    Button btn;

    FirebaseRecyclerOptions<TrackInfo> deliveryLocationListOptions;
    FirebaseRecyclerAdapter<TrackInfo, DeliveryLocationListViewHolder> deliveryLocationListAdapter;
    DatabaseReference ref;
    private RecyclerView deliveryLocationListRecycler;
    TextView deliveryManName2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDetailsSingleton= UserDetailsSingleton.getInstance();
        Log.d("test" , "value " + userDetailsSingleton.getCourierXUser().getFirstName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        locationStatus = view.findViewById(R.id.locationStatus);
        packageId = view.findViewById(R.id.location_packageId);
        btn = view.findViewById(R.id.update_location_btn);
        data = this.getArguments();
        if(data != null){
            st = data.getString("packageID");
            packageId.setText(st);
        }
        Log.d("test","your package is tracked "+ st);


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("packages");
        Query query=mDatabase.orderByChild("packageId").equalTo(st);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               // Log.d("data" , "value  : " + snapshot.getValue(PackageDetails.class).getStatus());
                for(DataSnapshot ds : snapshot.getChildren()) {
                    packageId.setText(ds.getValue(PackageDetails.class).getPackageId());
                    locationStatus.setText(ds.getValue(PackageDetails.class).getStatus());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref = FirebaseDatabase.getInstance().getReference("packages").child(st).child("trackInfo");

        deliveryLocationListRecycler = view.findViewById(R.id.last_loction_list_recyclerview);
        deliveryLocationListRecycler.setHasFixedSize(true);
        deliveryLocationListRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
     //   deliveryManName2 = view.findViewById(R.id.deliverymanName);
       // deliveryManName2.setText(userDetailsSingleton.getCourierXUser().getFirstName());

        deliveryLocationListOptions = new FirebaseRecyclerOptions.Builder<TrackInfo>().setQuery(ref , TrackInfo.class).build();
        deliveryLocationListAdapter = new FirebaseRecyclerAdapter<TrackInfo, DeliveryLocationListViewHolder>(deliveryLocationListOptions) {
            @NonNull
            @Override
            public DeliveryLocationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View recyclerview = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_location_list_card , parent , false);
                return new DeliveryLocationListViewHolder(recyclerview);
            }

            @Override
            protected void onBindViewHolder(@NonNull DeliveryLocationListViewHolder holder, int position, @NonNull TrackInfo model) {

                holder.location.setText(model.getLocation());
                Date date = new Date(model.getDate());
                SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
                        Locale.getDefault());
                String text = sfd.format(date);
                holder.time.setText(text);
            }
        } ;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailedViewFragment();
            }
        });

        deliveryLocationListAdapter.startListening();
        deliveryLocationListRecycler.setAdapter(deliveryLocationListAdapter);

        return view;
    }




    public void detailedViewFragment(){
        UpdateLocationDetails updateLocationDetails = new UpdateLocationDetails();
        updateLocationDetails.setArguments(data);
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.bottom_nav_fragment_delivery, updateLocationDetails);
        ft.commit();
    }



}