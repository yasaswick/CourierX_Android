package com.courierx.courierx.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.courierx.courierx.AddPayment;
import com.courierx.courierx.Models.CourierXUser;
import com.courierx.courierx.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminSearch extends Fragment {


    EditText userId;
    Button searchUserBtn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userRef = database.getReference("user");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_admin_search, container, false);

        userId = view.findViewById(R.id.searchUserInput);
        searchUserBtn = view.findViewById(R.id.searchUserBtn);



        searchUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String searchId = userId.getText().toString();

                userRef.orderByChild("uid").equalTo(searchId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for(DataSnapshot ds : snapshot.getChildren()){

                                CourierXUser courierXUser = ds.getValue(CourierXUser.class);
                                Intent intent = new Intent(getContext(), AddPayment.class);
                                intent.putExtra("uid" , courierXUser.getUid());
                                intent.putExtra("balance" , courierXUser.getBalance());
                                intent.putExtra("userName" , courierXUser.getUsername());
                                startActivity(intent);
                                

                            }
                        }else {
                            Snackbar.make(view,"No such user", Snackbar.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





            }
        });




        return view;
    }
}