package com.example.calendarproject;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

public class CalendarViewHolder extends RecyclerView.ViewHolder {
    private final TextView dayOfMonth;

    public CalendarViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
    }
}
