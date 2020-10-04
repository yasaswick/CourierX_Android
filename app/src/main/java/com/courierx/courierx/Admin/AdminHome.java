package com.courierx.courierx.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.courierx.courierx.AddPayment;
import com.courierx.courierx.Models.CourierXUser;
import com.courierx.courierx.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminHome extends Fragment {


    FirebaseRecyclerOptions<CourierXUser> userListOptions;
    FirebaseRecyclerAdapter<CourierXUser , UserListViewHolder > userListAdapter;
    DatabaseReference ref;
    private RecyclerView userListRecycler;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ref = FirebaseDatabase.getInstance().getReference().child("user");
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);

        userListRecycler = view.findViewById(R.id.userListRecycler);
        userListRecycler.setHasFixedSize(true);
        userListRecycler.setLayoutManager(new LinearLayoutManager(getContext()));


        userListOptions = new FirebaseRecyclerOptions.Builder<CourierXUser>().setQuery(ref , CourierXUser.class).build();
        userListAdapter = new FirebaseRecyclerAdapter<CourierXUser, UserListViewHolder>(userListOptions) {

            @Override
            protected void onBindViewHolder(@NonNull UserListViewHolder holder, int position, @NonNull CourierXUser model) {

                final String key = getRef(position).getKey();
                final String userName = model.getFirstName() + " " + model.getLastName();
                final Long balance = model.getBalance();

                holder.uid.setText(model.getUid());
                holder.balance.setText(model.getBalance().toString() + " LKR");
                holder.name.setText(model.getFirstName() + " " + model.getLastName() );

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), AddPayment.class);
                        intent.putExtra("uid" , key);
                        intent.putExtra("balance" , balance);
                        intent.putExtra("userName" , userName);

                        startActivity(intent);
                    }
                });



            }
            @NonNull
            @Override
            public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View recyclerview = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_card , parent , false);
                return new UserListViewHolder(recyclerview);

            }
        };

        userListAdapter.startListening();
        userListRecycler.setAdapter(userListAdapter);

        return view;

    }
}