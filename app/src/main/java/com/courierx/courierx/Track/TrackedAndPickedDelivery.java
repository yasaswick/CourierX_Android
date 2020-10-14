package com.courierx.courierx.Track;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class TrackedAndPickedDelivery extends Fragment {

    Bundle data;
    String pkgid;
    TextView packageid, sender, sndrcvrtxt, discrption, adddte, shduledte;
    DatabaseReference ref;
    private Query query;
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
                String rcvr = String.valueOf(value.get("sender"));
                String sndr = String.valueOf(value.get("receiver"));
                String des = String.valueOf(value.get("description"));
                String adt = String.valueOf(value.get("addedDate"));
                calendar.setTimeInMillis(Long.parseLong(adt));
                String adddate = formatter.format(calendar.getTime());
                String sdt = String.valueOf(value.get("scheduledDate"));
                calendar.setTimeInMillis(Long.parseLong(sdt));
                String shedate = formatter.format(calendar.getTime());
                packageid.setText(packid);
                if(sndr.equals(person)){
                    sndrcvrtxt.setText("Sender");
                    sender.setText(rcvr);
                }
                else if (rcvr.equals(person)){
                    sndrcvrtxt.setText("Receiver");
                    sender.setText(sndr);
                }
                discrption.setText(des);
                adddte.setText(adddate);
                shduledte.setText(shedate);
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
        return view;
    }
}