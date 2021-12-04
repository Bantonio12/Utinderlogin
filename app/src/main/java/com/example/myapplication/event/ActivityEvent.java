package com.example.myapplication.event;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import android.content.Context;
import android.app.Dialog;

import java.text.SimpleDateFormat;
import java.util.*;

import com.example.myapplication.homepage.Homepage;
import com.example.myapplication.community.Community;
import com.example.myapplication.me.MyAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ActivityEvent extends AppCompatActivity {
    private HashMap events = new HashMap<>();
    private ArrayList courseEvents = new ArrayList<>();
    private ArrayList generalEvents = new ArrayList<>();
    private ArrayList<HashMap<String, String>> eventsOnDate = new ArrayList<>();
    public Context context = this;
    private Dialog listEvents;
    EventsAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Button AddEventB = findViewById(R.id.addingbutton);
        final Button homebutton = findViewById(R.id.homebutton);
        final Button eventbutton = findViewById(R.id.eventbutton);
        final Button communitybutton = findViewById(R.id.communitybutton);
        final Button mebutton = findViewById(R.id.mebutton);
        final CalendarView calendarView = findViewById(R.id.Calendar_in_eventview);
        AddEventB.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent adding_event_intent = new Intent(ActivityEvent.this, CreatingEvent.class);
                startActivity(adding_event_intent);
                finish();
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String currDate = String.valueOf(dayOfMonth);
                if (currDate.length() == 1) {
                    currDate = "0" + currDate;
                }
                String currMonth = String.valueOf(month + 1);
                String currYear = String.valueOf(year);
                String selectedDate = currYear + "-" + currMonth + "-" + currDate;
                Log.e("Selected date", selectedDate);

                // TODO: Make a popup window that shows the list of events on that day
                showPopUp(selectedDate);
            }
        });

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homebutton_intent = new Intent(ActivityEvent.this, Homepage.class);
                startActivity(homebutton_intent);
                finish();
            }
        });
        eventbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eventbutton_intent = new Intent(ActivityEvent.this, ActivityEvent.class);
                startActivity(eventbutton_intent);
                finish();
            }
        });
        communitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent communitybutton_intent = new Intent(ActivityEvent.this, Community.class);
                startActivity(communitybutton_intent);
                finish();
            }
        });
        mebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mebutton_intent = new Intent(ActivityEvent.this, MyAccount.class);
                startActivity(mebutton_intent);
                finish();
            }
        });
    }

    private void showPopUp(String selectedDate) {
        // TODO: Extract the data for the selected date and add the info to the page
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userEvents = db.collection("users")
                .document("UserEvent");

        userEvents.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    events = (HashMap) document.get("admin");
                    courseEvents = (ArrayList) events.get("CourseEvents");
                    generalEvents = (ArrayList) events.get("GeneralEvents");

                    HashMap<String, ArrayList> userData = new HashMap<>();

                    userData.put("CourseEvents", courseEvents);
                    userData.put("GeneralEvents", generalEvents);
                    System.out.println(userData.toString());

                    EventDataConverter eventDataConverter = new EventDataConverter(userData);
                    eventsOnDate = eventDataConverter.findEvent(selectedDate);
                    System.out.println(eventsOnDate.toString());

                    arrayAdapter = new EventsAdapter(context, R.layout.activity_event_popup_item, eventsOnDate);

                    listEvents = new Dialog(context);
                    listEvents.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    listEvents.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    listEvents.setContentView(R.layout.activity_event_popup);

                    ListView l = listEvents.findViewById(R.id.events);
                    ((TextView) listEvents.findViewById(R.id.date)).setText(selectedDate);
                    l.setAdapter(arrayAdapter);

                    Button closeButton = listEvents.findViewById(R.id.back);
                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            eventsOnDate = new ArrayList<>();
                            listEvents.dismiss();
                        }
                    });

                    listEvents.show();

                } else {
                    Log.d("Events on this Day", "failed to obtain the data", task.getException());
                }
            }
        });

    }
}
