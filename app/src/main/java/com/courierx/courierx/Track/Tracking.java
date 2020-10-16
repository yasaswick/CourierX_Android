package com.courierx.courierx.Track;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.courierx.courierx.Delivery.DeliveryLocationListViewHolder;
import com.courierx.courierx.Models.TrackInfo;
import com.courierx.courierx.R;
import com.courierx.courierx.User.FindPackage;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class Tracking extends Fragment {

    private EditText getSetPackageId;
    private Button setTrackingBtn;
    private Bundle data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracking, container, false);
        getSetPackageId = view.findViewById(R.id.user_tracking_id);
        setTrackingBtn = view.findViewById(R.id.buttonsrch);

        setTrackingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String packageID = getSetPackageId.getText().toString();
                data = new Bundle();
                data.putString("packageID" , packageID);
                findPackageFrag();


            }
        });

        return view;
    }
    public void findPackageFrag(){

        FindPackage findPackage = new FindPackage();
        findPackage.setArguments(data);
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.navHostFragment_user, findPackage);
        ft.commit();
    }

}