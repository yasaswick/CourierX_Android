package com.courierx.courierx.Packages;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.courierx.courierx.Models.PackageDetails;
import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPackageDetails extends Fragment {

    EditText description, weight, sheduledDate;
    Button button2;
    CheckBox fragile;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String key = database.getReference("quiz").push().getKey();
    DatabaseReference myRef = database.getReference("packages").child(key);
    UserDetailsSingleton userDetailsSingleton = UserDetailsSingleton.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_package_details, container, false);
        description = view.findViewById(R.id.description);
        weight = view.findViewById(R.id.weight);
        sheduledDate = view.findViewById(R.id.sheduleDate);
        fragile = view.findViewById(R.id.fragile);
        button2 = view.findViewById(R.id.button2);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageDetails pkg = new PackageDetails();
                pkg.setPackageId(key);
                pkg.setSender(userDetailsSingleton.getCourierXUser().getUid().toString());
                pkg.setDescription(description.getText().toString());
                pkg.setWeight(Float.valueOf(weight.getText().toString()));
                pkg.setScheduledDate(sheduledDate.getText().toString());
                if(fragile.isChecked()){
                    pkg.setFragile(Boolean.TRUE);
                }
                myRef.setValue(pkg);
            }
        });

        return view;
    }
}