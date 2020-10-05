package com.courierx.courierx;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import com.courierx.courierx.Models.PackageDetails;
import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddPackageDetails extends Fragment {

    EditText description, weight, sheduledDate;
    Button button2;
    CheckBox fragile, track;
    Calendar calendar = Calendar.getInstance();
    Long timestamp, current;
    Bundle data;
    String receiver;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String key = database.getReference("quiz").push().getKey();
    DatabaseReference myRef = database.getReference("packages").child(key);
    UserDetailsSingleton userDetailsSingleton = UserDetailsSingleton.getInstance();

    private void updateLabel(){
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        sheduledDate.setText(sdf.format(calendar.getTime()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_package_details, container, false);
        description = view.findViewById(R.id.description);
        weight = view.findViewById(R.id.weight);
        sheduledDate = view.findViewById(R.id.sheduleDate);
        data = this.getArguments();
        if(data != null){
            receiver = data.getString("receiverID");
        }
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
        fragile = view.findViewById(R.id.fragile);
        track = view.findViewById(R.id.track);
        button2 = view.findViewById(R.id.button2);
        current = System.currentTimeMillis();



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageDetails pkg = new PackageDetails();
                pkg.setPackageId(key);
                pkg.setSender(userDetailsSingleton.getCourierXUser().getUid().toString());
                pkg.setDescription(description.getText().toString());
                pkg.setWeight(Float.valueOf(weight.getText().toString()));
                pkg.setScheduledDate(timestamp.toString());
                pkg.setAddedDate(current.toString());
                pkg.setReceiver(receiver);
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
                myRef.setValue(pkg);
            }
        });

        return view;
    }
}