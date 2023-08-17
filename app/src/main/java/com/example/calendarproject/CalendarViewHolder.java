package com.example.calendarproject;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public final TextView dayOfMonth; // TextView displays text to the user and optionally allows them to edit it programmatically. XML(android:etc) and Java textView.setText, etc
    private final CalendarAdapter.OnItemListener onItemListener;


    public CalendarViewHolder(@NonNull @NotNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        //retrieves text with id "cellDayText" from calendar_cell.xml which is now a View object due to the CalendarViewHolder method
        //in CalendarAdapter changing the xml into a View object by using inflater.
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override //Implemented from View.OnClickListener interface from View.class
    public void onClick(View view) {
        onItemListener.onItemClick(getAdapterPosition(), (String)dayOfMonth.getText());
        //View object is passed in containing click position in the view object
        //casting the TextView dayOfMonth to a string after retrieving ~ android:text="" ~ field from TextView object
    }
}
