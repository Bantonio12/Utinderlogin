package com.example.myapplication.event.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import android.content.Context;
import android.app.Dialog;

import java.util.*;

import com.example.myapplication.community.CommunityActivity;
import com.example.myapplication.event.converter.EventDataConverter;
import com.example.myapplication.homepage.Homepage;
import com.example.myapplication.me.MyAccount;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A class that displays and controls the Event page
 */
public class ActivityEvent extends AppCompatActivity {
    private HashMap events = new HashMap<>();
    private ArrayList courseEvents = new ArrayList<>();
    private ArrayList generalEvents = new ArrayList<>();
    private ArrayList<HashMap<String, String>> eventsOnDate = new ArrayList<>();
    public Context context = this;
    private Dialog listEvents;
    EventsAdapter arrayAdapter;

    /**
     * When Event page is displayed, all the features in this GUI should have complete functions
     * @param savedInstanceState    the previous state to this Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Button AddEventB = findViewById(R.id.addingbutton);
        final Button homeButton = findViewById(R.id.homebutton);
        final Button eventButton = findViewById(R.id.eventbutton);
        final Button communityButton = findViewById(R.id.communitybutton);
        final Button myAccountButton = findViewById(R.id.mebutton);
        final CalendarView calendarView = findViewById(R.id.Calendar_in_eventview);

        AddEventB.setOnClickListener(v -> {
            Intent adding_event_intent = new Intent(ActivityEvent.this, CreatingEvent.class);
            startActivity(adding_event_intent);
            finish();
        });

        calendarView.setOnDateChangeListener((calendarView1, year, month, dayOfMonth) -> {
            String currDate = String.valueOf(dayOfMonth);
            if (currDate.length() == 1) {
                currDate = "0" + currDate;
            }
            String currMonth = String.valueOf(month + 1);
            String currYear = String.valueOf(year);
            String selectedDate = currYear + "-" + currMonth + "-" + currDate;
            Log.e("Selected date", selectedDate);

            showPopUp(selectedDate);
        });

        homeButton.setOnClickListener(view -> {
            Intent goToHomePage = new Intent(ActivityEvent.this, Homepage.class);
            startActivity(goToHomePage);
            finish();
        });
        eventButton.setOnClickListener(view -> {
            Intent goToEventPage = new Intent(ActivityEvent.this, ActivityEvent.class);
            startActivity(goToEventPage);
            finish();
        });
        communityButton.setOnClickListener(view -> {
            Intent goToCommunityPage = new Intent(ActivityEvent.this, CommunityActivity.class);
            startActivity(goToCommunityPage);
            finish();
        });
        myAccountButton.setOnClickListener(view -> {
            Intent goToMyAccountPage = new Intent(ActivityEvent.this, MyAccount.class);
            startActivity(goToMyAccountPage);
            finish();
        });
    }

    /**
     * Show the Popup window that contains the list of events on the selected date
     * @param selectedDate  the date that the User selected on the calendar
     */
    private void showPopUp(String selectedDate) {
        // TODO: Extract the data for the selected date and add the info to the page
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userEvents = db.collection("users")
                .document("UserEvent");

        userEvents.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                events = (HashMap) document.get("admin");
                courseEvents = (ArrayList) events.get("CourseEvents");
                generalEvents = (ArrayList) events.get("GeneralEvents");

                HashMap<String, ArrayList> userData = new HashMap<>();

                userData.put("CourseEvents", courseEvents);
                userData.put("GeneralEvents", generalEvents);

                EventDataConverter eventDataConverter = new EventDataConverter(userData);
                eventsOnDate = eventDataConverter.findEvent(selectedDate);

                arrayAdapter = new EventsAdapter(context, R.layout.activity_event_popup_item, eventsOnDate);

                listEvents = new Dialog(context);
                listEvents.requestWindowFeature(Window.FEATURE_NO_TITLE);
                listEvents.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                listEvents.setContentView(R.layout.activity_event_popup);

                ListView l = listEvents.findViewById(R.id.events);
                ((TextView) listEvents.findViewById(R.id.date)).setText(selectedDate);
                l.setAdapter(arrayAdapter);

                Button closeButton = listEvents.findViewById(R.id.back);
                closeButton.setOnClickListener(view -> {
                    eventsOnDate = new ArrayList<>();
                    listEvents.dismiss();
                });

                listEvents.show();

            } else {
                Log.d("Events on this Day", "failed to obtain the data", task.getException());
            }
        });

    }
}
