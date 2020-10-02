package com.courierx.courierx.Delivery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.courierx.courierx.Models.PackageDetails;
import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeliverymanHome extends Fragment {
    FirebaseRecyclerOptions<PackageDetails> deliveryListOptions;
    FirebaseRecyclerAdapter<PackageDetails, DeliveryListViewHolder> deliveryListAdapter;
    DatabaseReference ref;
    private RecyclerView deliveryListRecycler;
    TextView deliveryManName;
    UserDetailsSingleton userDetailsSingleton = UserDetailsSingleton.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ref = FirebaseDatabase.getInstance().getReference("packages");
        View view=inflater.inflate(R.layout.fragment_deliveryman_home, container, false);
        deliveryListRecycler = view.findViewById(R.id.deliveryman_recycler_view);
        deliveryListRecycler.setHasFixedSize(true);
        deliveryListRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        deliveryManName = view.findViewById(R.id.deliverymanName);
        deliveryManName.setText(userDetailsSingleton.getCourierXUser().getFirstName());
        deliveryListOptions = new FirebaseRecyclerOptions.Builder<PackageDetails>().setQuery(ref , PackageDetails.class).build();
        deliveryListAdapter = new FirebaseRecyclerAdapter<PackageDetails, DeliveryListViewHolder>(deliveryListOptions) {
            @NonNull
            @Override
            public DeliveryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View recyclerview = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_delivery_list_card , parent , false);
                return new DeliveryListViewHolder(recyclerview);
            }

            @Override
            protected void onBindViewHolder(@NonNull DeliveryListViewHolder holder, int position, @NonNull PackageDetails model) {

                holder.packageid.setText(model.getSender());
                holder.status.setText(model.getStatus());
                holder.date.setText(model.getPickDate());

            }
        };

        deliveryListAdapter.startListening();
        deliveryListRecycler.setAdapter(deliveryListAdapter);

        return view;
    }
}