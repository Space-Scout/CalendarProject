package com.example.calendarproject;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity {

    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;

    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(time));
    }

    private void initWidgets() {
        eventNameET = findViewById(R.id.eventNameET); //finds eventNameET by its id in activity_event_edit.xml
        eventDateTV = findViewById(R.id.eventDateTV); //finds eventDateTV by its id in activity_event_edit.xml
        eventTimeTV = findViewById(R.id.eventTimeTV); //finds eventTimeTV by its id in activity_event_edit.xml
    }

    public void saveEventAction(View view) {
        String eventName = eventNameET.getText().toString();
        Event newEvent = new Event(eventName, CalendarUtils.selectedDate, time); // create new event
        Event.eventsList.add(newEvent); //add new event to events list in Event class
        finish(); //closes the activity
    }
}