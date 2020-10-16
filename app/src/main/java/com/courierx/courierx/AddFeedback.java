package com.courierx.courierx;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.courierx.courierx.Models.CourierXUser;
import com.courierx.courierx.Models.Feedback;
import com.courierx.courierx.Models.UserDetailsSingleton;
import com.courierx.courierx.Services.FirebaseRealtime;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class AddFeedback extends Fragment {

    EditText topic,message;
    TextInputLayout topicLayout,messageLayout;
    Button addFeedback,CancelFeedback;
    FirebaseRealtime firebaseRealtime;
    UserDetailsSingleton userDetailsSingleton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_add_feedback, container, false);

        userDetailsSingleton = UserDetailsSingleton.getInstance();
        firebaseRealtime = new FirebaseRealtime();
        String[] Topics = new String[] {"General", "Suggestion", "Support", "Complaint"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),R.layout.dropdown_menu_popup_item,Topics);

        final AutoCompleteTextView editTextFilledExposedDropdown = view.findViewById(R.id.selectTopicDropDown);
        editTextFilledExposedDropdown.setText("General");
        editTextFilledExposedDropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextFilledExposedDropdown.showDropDown();
            }
        });

        editTextFilledExposedDropdown.setAdapter(adapter);

        addFeedback = view.findViewById(R.id.addFeedbackBtn);
        topic = view.findViewById(R.id.selectTopicDropDown);
        message = view.findViewById(R.id.feedbackMessage);
        topicLayout = view.findViewById(R.id.feedbackTopicLayout);
        messageLayout = view.findViewById(R.id.feedbackMessageLayout);

        addFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Feedback feedback = new Feedback();
                feedback.setUserId(userDetailsSingleton.getCourierXUser().getUid());
                feedback.setUserName(userDetailsSingleton.getCourierXUser().getFirstName() + " " + userDetailsSingleton.getCourierXUser().getLastName());
                feedback.setDate(System.currentTimeMillis());
                if(topic.getText().toString()!= null){
                    feedback.setTitle(topic.getText().toString());
                    if(message.getText().toString()!= null){
                        feedback.setContent(message.getText().toString());
                        feedback.setRead(0);
                        firebaseRealtime.addFeedback(feedback);
                        Snackbar snackbar = Snackbar.make(view, "Feedback Added Successfully", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }else{
                        messageLayout.setError("Please Enter a message");
                    }
                }else{
                    topicLayout.setError("Please Enter a topic");
                }
            }
        });
        return view;
    }








}