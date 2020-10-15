package com.courierx.courierx;

import android.app.DatePickerDialog;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.courierx.courierx.Models.PackageDetails;
import com.courierx.courierx.Models.UserDetailsSingleton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class UpdatePackage extends Fragment {

    EditText description, weight, sheduledDate;
    String packid, sndr, rcvr, sdt, pkgid, wght;
    Button button2;
    CheckBox fragile, track;
    Calendar calendar = Calendar.getInstance();
    Long timestamp, current;
    Bundle data;
    DatabaseReference ref;
    Query query;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

    private void updateLabel(){
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        sheduledDate.setText(sdf.format(calendar.getTime()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_update_package, container, false);
        description = view.findViewById(R.id.description);
        weight = view.findViewById(R.id.weight);
        sheduledDate = view.findViewById(R.id.sheduleDate);
        fragile = view.findViewById(R.id.fragile);
        track = view.findViewById(R.id.track);
        button2 = view.findViewById(R.id.button2);
        current = System.currentTimeMillis();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                timestamp = calendar.getTimeInMillis();
                updateLabel();
            }
        };
        sheduledDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        data = this.getArguments();
        ref = FirebaseDatabase.getInstance().getReference().child("packages");
        if(data != null){
            pkgid = data.getString("packageID");
        }
        query = ref.orderByChild("packageId").equalTo(pkgid);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Map<String, Object> value = (Map<String, Object>) snapshot.getValue();
                packid = String.valueOf(value.get("packageId"));
                rcvr = String.valueOf(value.get("receiver"));
                sndr = String.valueOf(value.get("sender"));
                String des = String.valueOf(value.get("description"));
                String weght = String.valueOf(value.get("weight"));
                sdt = String.valueOf(value.get("scheduledDate"));
                calendar.setTimeInMillis(Long.parseLong(sdt));
                String shedate = formatter.format(calendar.getTime());
                String fragle = String.valueOf(value.get("fragile"));
                String trck = String.valueOf(value.get("isTracked"));
                description.setText(des);
                weight.setText(weght);
                sheduledDate.setText(shedate);
                if (fragle.equals("true")){
                    fragile.setChecked(true);
                }
                if (trck.equals("true")){
                    track.setChecked(true);
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

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(description.getText().toString().trim().equalsIgnoreCase("")){
                    description.requestFocus();
                    description.setError("This field cannot be null!");
                }
                else if (weight.getText().toString().trim().equalsIgnoreCase("")){
                    weight.requestFocus();
                    weight.setError("Please enter weight!");
                }
                else if (sheduledDate.getText().toString().trim().equalsIgnoreCase("")){
                    sheduledDate.requestFocus();
                    sheduledDate.setError("Please enter your prefered sheduled date!");
                }
                else {
                    sheduledDate.setError(null);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference upRef = database.getReference().child("packages").child(pkgid);
                    PackageDetails pkg = new PackageDetails();
                    pkg.setPackageId(packid);
                    pkg.setSender(sndr);
                    pkg.setReceiver(rcvr);
                    pkg.setDescription(description.getText().toString());
                    wght = weight.getText().toString();
                    try {
                        pkg.setWeight(Float.valueOf(weight.getText().toString()));
                        if(timestamp != null){
                            pkg.setScheduledDate(timestamp.toString());
                        }
                        else{
                            pkg.setScheduledDate(sdt);
                        }
                        pkg.setAddedDate(current.toString());
                        pkg.setStatus("Pending");
                        if(fragile.isChecked()){
                            pkg.setFragile("true");
                        }else {
                            pkg.setFragile("false");
                        }
                        if (track.isChecked()){
                            pkg.setIsTracked("true");
                        }else {
                            pkg.setIsTracked("false");
                        }
                        upRef.setValue(pkg);
                        Snackbar snackbar = Snackbar.make(view, "Package Updated!", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        listViewFragment();
                    }catch (NumberFormatException e){
                        weight.requestFocus();
                        weight.setError("Please enter a numeric weight!");
                    }
                }
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
}