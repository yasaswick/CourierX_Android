package com.courierx.courierx;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToMeViewHolder extends RecyclerView.ViewHolder {

    TextView pakgeid, pakgedis;

    public ToMeViewHolder(@NonNull View itemView) {
        super(itemView);
        
        pakgeid = itemView.findViewById(R.id.pkgid);
        pakgedis = itemView.findViewById(R.id.pkgdiscription);

    }
}
