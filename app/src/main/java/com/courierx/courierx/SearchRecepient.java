package com.courierx.courierx;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SearchRecepient extends Fragment {

    EditText recepient;
    Button serch;
    Bundle data;
    DatabaseReference ref;
    Query query;
    String receiverID;

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


                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(!snapshot.exists()){
                                recepient.requestFocus();
                                recepient.setError("No Such User Exists!");
                            } else {
                                data = new Bundle();
                                data.putString("receiverID", receiverID);
                                detailedViewFragment();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    }
                    );
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

    public boolean recepientValidate(String recpnt) {
        if (recpnt.isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }
}