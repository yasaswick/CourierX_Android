package com.courierx.courierx;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class FeedbackListViewHolder extends RecyclerView.ViewHolder {



    TextView title , date, details ,name;
    ImageView readStat;

    public FeedbackListViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.feedbackCardTitle);
        date = itemView.findViewById(R.id.feedbackCardDate);
        details = itemView.findViewById(R.id.feedbackCardDetails);
        name = itemView.findViewById(R.id.feedbackCardName);
        readStat = itemView.findViewById(R.id.readFeedbackIcon);
    }
}
