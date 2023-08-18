package com.example.calendarproject;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;

    // Android Bundles are generally used for passing data from one activity to another.
    // Basically the concept of key-value pair is used where the data that one wants to pass is the value of the map, which can be later retrieved by using the key.
    //They hold all types of values
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //layout of content view is designed by activity_main.xml in layout folder
        initWidgets();
        selectedDate = LocalDate.now(); //sets selectedDate to current date and time
        setMonthView();
    }

    //Methods

    //setting variables monthYearText and calendarRecyclerView
    private void initWidgets(){
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView); // retrieves text with that id from xml file and converts to given class (RecyclerView)
        monthYearText = findViewById(R.id.monthYearTV); // retrieves text with that id from xml file and converts to given class (TextView)
    }


    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy"); //creates a formatter object that puts LocalDate in form "MMMM yyyy"
        return date.format(formatter); //the formatter is used on the date object that was passed in and gets returned
    }

    private ArrayList<String> daysInMonthArray(LocalDate date){
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i<= 42; i++){
            if(i < dayOfWeek || i > daysInMonth + dayOfWeek){ //if i (the day) is before the first of the month or last of the month, add a blank square
                daysInMonthArray.add("");
            }else{                                             //otherwise, add the day of the month (i-dayOfWeek)
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    private void setMonthView() { //uses monthYearFromDate method to set monthYearText
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate); //uses daysInMonthArray method

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7 ); //adds a grid with 7 columns
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void previousMonthAction(View view){     //moves to previous month when button (action) is hit
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view){         //moves to next month when button (action) is hit
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if(dayText.equals("")){
            String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(this,message, Toast.LENGTH_LONG).show(); //A toast provides simple feedback about an operation in a small popup.
            // It only fills the amount of space required for the message and the current activity remains visible and interactive. T
            // Toasts automatically disappear after a timeout.


        }
    }
}
