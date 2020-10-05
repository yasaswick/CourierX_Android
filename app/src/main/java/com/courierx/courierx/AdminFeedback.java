package com.courierx.courierx;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.courierx.courierx.Admin.UserListViewHolder;
import com.courierx.courierx.Models.CourierXUser;
import com.courierx.courierx.Models.Feedback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AdminFeedback extends Fragment {

    FirebaseRecyclerOptions<Feedback> feedbackListOptions;
    FirebaseRecyclerAdapter<Feedback , FeedbackListViewHolder> feedbackListAdapter;
    DatabaseReference ref;
    private RecyclerView feedbackListRecycler;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ref = FirebaseDatabase.getInstance().getReference().child("feedback");

        View view = inflater.inflate(R.layout.fragment_admin_feedback, container, false);
        feedbackListRecycler = view.findViewById(R.id.adminFeedbackListRecycler);
        feedbackListRecycler.setHasFixedSize(true);
        feedbackListRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        feedbackListOptions = new FirebaseRecyclerOptions.Builder<Feedback>().setQuery(ref , Feedback.class).build();
        feedbackListAdapter = new FirebaseRecyclerAdapter<Feedback, FeedbackListViewHolder>(feedbackListOptions) {
            @NonNull
            @Override
            public FeedbackListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View recyclerview = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_feedback_list_card , parent , false);
                return new FeedbackListViewHolder(recyclerview);
            }

            @Override
            protected void onBindViewHolder(@NonNull FeedbackListViewHolder holder, int position, @NonNull Feedback model) {
                holder.name.setText(model.getUserName());
                holder.details.setText(model.getContent());
                holder.title.setText(model.getTitle());
                holder.date.setText(model.getDate().toString());
                Log.d("hhh" , " test values : " + model.getTitle());

            }
        };

        feedbackListAdapter.startListening();
        feedbackListRecycler.setAdapter(feedbackListAdapter);

      return  view;
        }
}