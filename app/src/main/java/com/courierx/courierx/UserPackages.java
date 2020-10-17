package com.courierx.courierx;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.Packages.ToMeViewHolder;
import com.courierx.courierx.Track.TrackedAndPickedDelivery;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.courierx.courierx.Models.PackageDetails;
import com.google.firebase.database.Query;

public class UserPackages extends Fragment {

    FirebaseRecyclerOptions<PackageDetails> toMeListOptions;
    FirebaseRecyclerAdapter<PackageDetails , ToMeViewHolder> toMeListAdapter;
    DatabaseReference ref;
    private RecyclerView toMeListRecycler;
    private UserDetailsSingleton userDetailsSingleton;
    private Query query;
    Bundle data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ref = FirebaseDatabase.getInstance().getReference().child("packages");
        userDetailsSingleton = UserDetailsSingleton.getInstance();
        query = ref.orderByChild("sender").equalTo(userDetailsSingleton.getCourierXUser().getUid());
        View view =  inflater.inflate(R.layout.fragment_user_packages, container, false);
        toMeListRecycler = view.findViewById(R.id.to_me_recycler_view);
        toMeListRecycler.setHasFixedSize(true);
        toMeListRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        Switch sButton = view.findViewById(R.id.mypackageswitch);
        toMeListOptions = new FirebaseRecyclerOptions.Builder<PackageDetails>().setQuery(query , PackageDetails.class).build();
        toMeListAdapter = new FirebaseRecyclerAdapter<PackageDetails, ToMeViewHolder>(toMeListOptions) {
            @NonNull
            @Override
            public ToMeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View recyclerview = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_package_list_card , parent , false);
                return new ToMeViewHolder(recyclerview);
            }

            @Override
            protected void onBindViewHolder(@NonNull ToMeViewHolder holder, int position, @NonNull PackageDetails model) {
                final String id = getRef(position).getKey();
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        data = new Bundle();
                        data.putString("packageID", id);
                        detailedViewFragment();
                    }
                });
                holder.pakgeid.setText(model.getPackageId());
                holder.pakgedis.setText(model.getDescription());
            }
        };
        sButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
             if(b){
                 query = ref.orderByChild("receiver").equalTo(userDetailsSingleton.getCourierXUser().getUid());
                 toMeListOptions = new FirebaseRecyclerOptions.Builder<PackageDetails>().setQuery(query , PackageDetails.class).build();
                 toMeListAdapter.updateOptions(toMeListOptions);
             }else{
                 query = ref.orderByChild("sender").equalTo(userDetailsSingleton.getCourierXUser().getUid());
                 toMeListOptions = new FirebaseRecyclerOptions.Builder<PackageDetails>().setQuery(query , PackageDetails.class).build();
                 toMeListAdapter.updateOptions(toMeListOptions);
             }

            }
        });

        toMeListAdapter.startListening();
        toMeListRecycler.setAdapter(toMeListAdapter);

        return view;
    }

    public void detailedViewFragment(){
        TrackedAndPickedDelivery trackedAndPickedDelivery = new TrackedAndPickedDelivery();
        trackedAndPickedDelivery.setArguments(data);
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.navHostFragment_user, trackedAndPickedDelivery);
        ft.commit();
    }
}