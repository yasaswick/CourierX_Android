package com.courierx.courierx;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
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
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;


public class AdminFeedback extends Fragment {

    FirebaseRecyclerOptions<Feedback> feedbackListOptions;
    FirebaseRecyclerAdapter<Feedback , FeedbackListViewHolder> feedbackListAdapter;
    DatabaseReference ref;
    private RecyclerView feedbackListRecycler;
    View contextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ref = FirebaseDatabase.getInstance().getReference().child("feedback");

        View view = inflater.inflate(R.layout.fragment_admin_feedback, container, false);
        feedbackListRecycler = view.findViewById(R.id.adminFeedbackListRecycler);
        feedbackListRecycler.setHasFixedSize(true);
        feedbackListRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);

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


        itemTouchHelper.attachToRecyclerView(feedbackListRecycler);
        feedbackListAdapter.startListening();
        feedbackListRecycler.setAdapter(feedbackListAdapter);
        contextView = view;
      return  view;
        }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int id = viewHolder.getAdapterPosition();
            Feedback feedback = feedbackListAdapter.getItem(id);

            switch (direction){
                case ItemTouchHelper.LEFT:
                    ref.child(feedback.getFeedbackId()).removeValue();
                    Snackbar.make(contextView, "Deleted Review", Snackbar.LENGTH_SHORT).show();
                    break;

                case ItemTouchHelper.RIGHT:

                    break;
            }
        }


        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent))
                    .addActionIcon(R.drawable.ic_baseline_remove_circle_outline_24)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };



}