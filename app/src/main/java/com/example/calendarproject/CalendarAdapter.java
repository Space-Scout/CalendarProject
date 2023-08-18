package com.example.calendarproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>{

    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;

    //daysOfMonthConstructor
    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener) {
        this.daysOfMonth = daysOfMonth;
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
        layoutParams.height = (int) (parent.getHeight() * 0.166666666); //getting full height of the view and then multiply by 1/6th so each cell is 1/6th of the view
        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CalendarViewHolder holder, int position) {
        holder.dayOfMonth.setText(daysOfMonth.get(position)); //Java utilisation of TextView
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size(); //get size of ArrayList of daysOfMonth
    }


    //Interface
    public interface OnItemListener{
        void onItemClick(int position, String dayText);
    }

}




