package com.courierx.courierx;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Map;

public class SearchRecepient extends Fragment {

    EditText recepient;
    Button serch;
    Bundle data;
    DatabaseReference ref;
    Query query;
    String receiverID, uid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search_recepient, container, false);
        recepient = view.findViewById(R.id.editTextTextPersonName);
        serch = view.findViewById(R.id.buttonsrch);

        serch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(recepient.getText().toString().trim().equalsIgnoreCase("")){
                    recepient.requestFocus();
                    recepient.setError("Please enter a User ID");
                }
                else {
                    receiverID = recepient.getText().toString();
                    ref = FirebaseDatabase.getInstance().getReference().child("user");
                    query = ref.orderByChild("uid").equalTo(receiverID);
                    query.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            Map<String, Object> value = (Map<String, Object>) snapshot.getValue();
                            uid = String.valueOf(value.get("uid"));
                            if (uid != null){
                                data = new Bundle();
                                data.putString("receiverID", receiverID);
                                detailedViewFragment();
                            }
                            else{
                                recepient.requestFocus();
                                recepient.setError("No Such User ID");
                            }
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        }
                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                        }
                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });

        return view;
    }

    public void detailedViewFragment(){
        AddPackageDetails addPackageDetails = new AddPackageDetails();
        addPackageDetails.setArguments(data);
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.navHostFragment_user, addPackageDetails);
        ft.commit();
    }
}