package com.example.calendarproject;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>{

    private final ArrayList<LocalDate> days;
    private final OnItemListener onItemListener;

    //daysOfMonthConstructor
    public CalendarAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener) {
        this.days = days;
        this.onItemListener = onItemListener;
    }


    //IMPLEMENTING CalendarViewHolder methods
    @NonNull //automatic by Java
    @NotNull //automatic by Java
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //Creates LayoutInflater object named inflater to use
        //The LayoutInflater class is used to instantiate the contents of layout XML files into their corresponding View objects.
        //In other words, it takes an XML file as input and builds the View objects from it.
        View view = inflater.inflate(R.layout.calendar_cell, parent,false);
        //inflater object takes the calendar_cell xml file from layout and makes it a View object
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if(days.size() > 15) //month view
            layoutParams.height = (int) (parent.getHeight() * 0.166666666); //getting full height of the view and then multiply by 1/6th so each cell is 1/6th of the view
        else //week view
            layoutParams.height = (int) parent.getHeight();
        return new CalendarViewHolder(view, onItemListener,days);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CalendarViewHolder holder, int position) {
        LocalDate date = days.get(position);
        if(date == null)
            holder.dayOfMonth.setText("");
        else {
            holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth())); //Java utilisation of TextView
            if(date.equals(CalendarUtils.selectedDate))
                holder.parentView.setBackgroundColor(Color.LTGRAY);
        }
    }

    @Override
    public int getItemCount() {
        return days.size(); //get size of ArrayList of daysOfMonth
    }


    //Interface
    public interface OnItemListener{
        void onItemClick(int position, LocalDate date);
    }

}




