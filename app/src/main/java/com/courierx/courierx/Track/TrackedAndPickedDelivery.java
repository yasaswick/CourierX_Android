package com.courierx.courierx.Track;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.courierx.courierx.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class TrackedAndPickedDelivery extends Fragment {

    Bundle data;
    String pkgid;
    TextView packageid, sndrrcivr, discrption, adddte, shduledte;
    DatabaseReference ref;
    private Query query;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tracked_and_picked_delivery, container, false);
        packageid = view.findViewById(R.id.packageID);
        sndrrcivr = view.findViewById(R.id.sndrecvr);
        discrption = view.findViewById(R.id.dccrptn);
        adddte = view.findViewById(R.id.adddte);
        shduledte = view.findViewById(R.id.shdledte);
        ref = FirebaseDatabase.getInstance().getReference().child("packages");
        data = this.getArguments();
        if (data != null){
            pkgid = data.getString("packageID");
        }
        query = ref.orderByChild("packageId").equalTo(pkgid);
        return view;
    }
}