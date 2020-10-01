package com.courierx.courierx;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.courierx.courierx.Packages.ToMeViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.courierx.courierx.Models.Package;

public class UserPackages extends Fragment {

    FirebaseRecyclerOptions<Package> toMeListOptions;
    FirebaseRecyclerAdapter<Package , ToMeViewHolder> toMeListAdapter;
    DatabaseReference ref;
    private RecyclerView toMeListRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ref = FirebaseDatabase.getInstance().getReference().child("packages");
        View view =  inflater.inflate(R.layout.fragment_user_packages, container, false);
        toMeListRecycler = view.findViewById(R.id.to_me_recycler_view);
        toMeListRecycler.setHasFixedSize(true);
        toMeListRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        toMeListOptions = new FirebaseRecyclerOptions.Builder<Package>().setQuery(ref , Package.class).build();
        toMeListAdapter = new FirebaseRecyclerAdapter<Package, ToMeViewHolder>(toMeListOptions) {
            @NonNull
            @Override
            public ToMeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View recyclerview = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_package_list_card , parent , false);
                return new ToMeViewHolder(recyclerview);
            }

            @Override
            protected void onBindViewHolder(@NonNull ToMeViewHolder holder, int position, @NonNull Package model) {
                holder.pakgeid.setText(model.getPackageId());
            }
        };

        toMeListAdapter.startListening();
        toMeListRecycler.setAdapter(toMeListAdapter);

        return view;
    }
}