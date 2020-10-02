package com.courierx.courierx.Track;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.courierx.courierx.Delivery.DeliveryListViewHolder;
import com.courierx.courierx.Delivery.DeliveryLocationListViewHolder;
import com.courierx.courierx.Models.PackageDetails;
import com.courierx.courierx.Models.TrackInfo;
import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Location extends Fragment {
    String st;
    TextView locationStatus;
    TextView packageId;
    UserDetailsSingleton userDetailsSingleton ;

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
        packageId = view.findViewById(R.id.packageid);
        Bundle data = this.getArguments();
        if(data != null){
            st = data.getString("packageID");
        }
        Log.d("test","your package is tracked "+ st);


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("packages").child(st);
        //Query query=mDatabase.orderByChild("packageId").equalTo(st);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("data" , "value  : " + snapshot.getValue(PackageDetails.class).getStatus());
                locationStatus.setText(snapshot.getValue(PackageDetails.class).getStatus());
                packageId.setText(snapshot.getValue(PackageDetails.class).getPackageId());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref = FirebaseDatabase.getInstance().getReference("packages").child(st).child("trackInfoList");

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
                holder.time.setText(model.getDate()+ " "+model.getTime());
            }
        } ;

        deliveryLocationListAdapter.startListening();
        deliveryLocationListRecycler.setAdapter(deliveryLocationListAdapter);

        return view;
    }



}