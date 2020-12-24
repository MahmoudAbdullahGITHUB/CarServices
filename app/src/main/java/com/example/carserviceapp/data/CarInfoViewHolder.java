package com.example.carserviceapp.data;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carserviceapp.R;

public class CarInfoViewHolder extends RecyclerView.ViewHolder {

    public TextView details;
    public Button acceptB;

    public CarInfoViewHolder(@NonNull View itemView) {
        super(itemView);

        details = itemView.findViewById(R.id.item_details);
        acceptB = itemView.findViewById(R.id.accept_b);
    }
}
