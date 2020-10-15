package com.courierx.courierx.Track;

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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.courierx.courierx.AddPackageDetails;
import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.R;
import com.courierx.courierx.UpdatePackage;
import com.courierx.courierx.UserPackages;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class TrackedAndPickedDelivery extends Fragment {

    Bundle data, info;
    String pkgid;
    TextView packageid, sender, sndrcvrtxt, discrption, adddte, shduledte, stats;
    DatabaseReference ref;
    Query query;
    ImageButton delt, edit;
    UserDetailsSingleton userDetailsSingleton = UserDetailsSingleton.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Calendar calendar = Calendar.getInstance();
    String person;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tracked_and_picked_delivery, container, false);
        packageid = view.findViewById(R.id.pkgID);
        sender = view.findViewById(R.id.sndrecevr);
        sndrcvrtxt = view.findViewById(R.id.sndrrcvr);
        discrption = view.findViewById(R.id.dccrptn);
        adddte = view.findViewById(R.id.adddte);
        shduledte = view.findViewById(R.id.shdledte);
        stats = view.findViewById(R.id.stus);
        delt = view.findViewById(R.id.dltbtn);
        delt.setVisibility(View.INVISIBLE);
        edit = view.findViewById(R.id.edtbtn);
        edit.setVisibility(View.INVISIBLE);
        ref = FirebaseDatabase.getInstance().getReference().child("packages");
        data = this.getArguments();
        if (data != null){
            pkgid = data.getString("packageID");
        }
        person = userDetailsSingleton.getCourierXUser().getUid().toString();
        query = ref.orderByChild("packageId").equalTo(pkgid);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Map<String, Object> value = (Map<String, Object>) snapshot.getValue();
                String packid = String.valueOf(value.get("packageId"));
                String rcvr = String.valueOf(value.get("receiver"));
                String sndr = String.valueOf(value.get("sender"));
                String des = String.valueOf(value.get("description"));
                String adt = String.valueOf(value.get("addedDate"));
                calendar.setTimeInMillis(Long.parseLong(adt));
                String adddate = formatter.format(calendar.getTime());
                String sdt = String.valueOf(value.get("scheduledDate"));
                calendar.setTimeInMillis(Long.parseLong(sdt));
                String shedate = formatter.format(calendar.getTime());
                String status = String.valueOf(value.get("status"));
                packageid.setText(packid);
                if(sndr.equals(person)){
                    sndrcvrtxt.setText("Receiver");
                    sender.setText(rcvr);
                }
                else if (rcvr.equals(person)){
                    sndrcvrtxt.setText("Sender");
                    sender.setText(sndr);
                }
                if ((status.equals("Pending") && sndr.equals(person)) || (status.equals("Delivered") && sndr.equals(person))){
                    delt.setVisibility(View.VISIBLE);
                }
                if (status.equals("Pending") && sndr.equals(person)){
                    edit.setVisibility(View.VISIBLE);
                }
                discrption.setText(des);
                adddte.setText(adddate);
                shduledte.setText(shedate);
                stats.setText(status);
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

        delt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = ref.orderByChild("packageId").equalTo(pkgid);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot itemSnapshot : snapshot.getChildren()){
                            itemSnapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Snackbar snackbar = Snackbar.make(view, "Package Deleted!", Snackbar.LENGTH_LONG);
                snackbar.show();
                listViewFragment();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info = new Bundle();
                info.putString("packageID", pkgid);
                detailedViewFragment();
            }
        });

        return view;
    }

    public void listViewFragment() {
        UserPackages userPackages = new UserPackages();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.navHostFragment_user, userPackages);
        fragmentTransaction.commit();
    }

    public void detailedViewFragment() {
        UpdatePackage updatePackage = new UpdatePackage();
        updatePackage.setArguments(info);
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.navHostFragment_user, updatePackage);
        fragmentTransaction.commit();
    }

}