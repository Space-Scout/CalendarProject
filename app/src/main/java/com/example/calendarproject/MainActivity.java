package com.example.calendarproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.calendarproject.CalendarUtils.daysInMonthArray;
import static com.example.calendarproject.CalendarUtils.monthYearFromDate;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;


    // Android Bundles are generally used for passing data from one activity to another.
    // Basically the concept of key-value pair is used where the data that one wants to pass is the value of the map, which can be later retrieved by using the key.
    //They hold all types of values
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //layout of content view is designed by activity_main.xml in layout folder
        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now(); //sets selectedDate to current date and time
        setMonthView();
    }

    //Methods

    //setting variables monthYearText and calendarRecyclerView
    private void initWidgets(){
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView); // retrieves text with that id from xml file and converts to given class (RecyclerView)
        monthYearText = findViewById(R.id.monthYearTV); // retrieves text with that id from xml file and converts to given class (TextView)
    }


    private void setMonthView() { //uses monthYearFromDate method to set monthYearText
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate); //uses daysInMonthArray method

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7 ); //adds a grid with 7 columns
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void previousMonthAction(View view){     //moves to previous month when button (action) is hit
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view){         //moves to next month when button (action) is hit
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        if(date != null){
            CalendarUtils.selectedDate = date;
            setMonthView();
        }
    }

    public void weeklyAction(View view) {
        startActivity(new Intent(this,WeekViewActivity.class));  //starts WeekViewActivity when the button in activity_main.xml that links to that method is clicked.
    }
}
//A toast provides simple feedback about an operation in a small popup.
// It only fills the amount of space required for the message and the current activity remains visible and interactive. T
// Toasts automatically disappear after a timeout.