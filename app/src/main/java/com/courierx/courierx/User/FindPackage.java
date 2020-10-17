package com.courierx.courierx.User;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.courierx.courierx.Models.TrackInfo;
import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.R;
import com.courierx.courierx.Track.UserLocationListViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FindPackage extends Fragment {
    String st;
    TextView locationStatus;
    TextView packageId;
    Bundle data;
    FirebaseRecyclerOptions<TrackInfo> userLocationListOptions;
    FirebaseRecyclerAdapter<TrackInfo, UserLocationListViewHolder> userLocationListAdapter;
    DatabaseReference ref;
    private RecyclerView userLocationListRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_find_package, container, false);

        locationStatus = view.findViewById(R.id.locationStatus);
        packageId = view.findViewById(R.id.tracking_id_user);
        data = this.getArguments();
        if(data != null){
            st = data.getString("packageID");
            packageId.setText(st);
        }



        ref = FirebaseDatabase.getInstance().getReference("packages").child(st).child("trackInfo");

        userLocationListRecycler = view.findViewById(R.id.user_tracking_recycler_view);
        userLocationListRecycler.setHasFixedSize(true);
        userLocationListRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        userLocationListOptions = new FirebaseRecyclerOptions.Builder<TrackInfo>().setQuery(ref , TrackInfo.class).build();
        userLocationListAdapter = new FirebaseRecyclerAdapter<TrackInfo, UserLocationListViewHolder>(userLocationListOptions) {

            @NonNull
            @Override
            public UserLocationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View recyclerview = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_package_location_list_card , parent , false);
                return new UserLocationListViewHolder(recyclerview);
            }

            @Override
            protected void onBindViewHolder(@NonNull UserLocationListViewHolder holder, int position, @NonNull TrackInfo model) {
                holder.location.setText(model.getLocation());
                Date date = new Date(model.getDate());
                SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
                        Locale.getDefault());
                String text = sfd.format(date);

                holder.time.setText(text);
            }
        };
        userLocationListAdapter.startListening();
        userLocationListRecycler.setAdapter(userLocationListAdapter);




        return view;





    }
}