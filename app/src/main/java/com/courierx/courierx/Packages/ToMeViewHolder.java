package com.courierx.courierx.Packages;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.courierx.courierx.R;

public class ToMeViewHolder extends RecyclerView.ViewHolder {

    public TextView pakgeid, pakgedis;

    public ToMeViewHolder(@NonNull View itemView) {
        super(itemView);
        
        pakgeid = itemView.findViewById(R.id.packageID);
        pakgedis = itemView.findViewById(R.id.pkgdiscription);

    }
}
