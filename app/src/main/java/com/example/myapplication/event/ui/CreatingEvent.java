package com.example.myapplication.event.ui;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.event.converter.EventDataConverter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreatingEvent extends AppCompatActivity {

    private HashMap events = new HashMap<>();
    private ArrayList courseEvents = new ArrayList<>();
    private ArrayList generalEvents = new ArrayList<>();

    /**
     * Render necessary components when the page is shown in the app
     * @param savedInstanceState    the previous status
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_event);

        final EditText eventName = findViewById(R.id.puttingevent);
        final EditText eventDate = findViewById(R.id.puttingdate);
        final EditText eventTime = findViewById(R.id.puttingtime);
        final EditText eventType = findViewById(R.id.categorizing);
        final EditText eventLocation = findViewById(R.id.puttinglocation);
        final EditText courseCode = findViewById(R.id.puttingcoursecode);
        final Button createEventButton = findViewById(R.id.createingeventbutton);
        final TextView failed = findViewById(R.id.fail_message);
        final TextView fillout = findViewById(R.id.fillout_message);
        final Button cancelButton = findViewById(R.id.cancel_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_to_event = new Intent(CreatingEvent.this, ActivityEvent.class);
                startActivity(back_to_event);
            }
        });

        createEventButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String name = eventName.getText().toString();
                String date = eventDate.getText().toString();
                String time = eventTime.getText().toString();
                String type = eventType.getText().toString();
                String location = eventLocation.getText().toString();
                String code = courseCode.getText().toString();

                if (name.isEmpty() || date.isEmpty() || time.isEmpty() || type.isEmpty()) {
                    Log.w("Creating Event", "Failed to fill out the information");
                    fillout.setVisibility(View.VISIBLE);
                } else {
                    Map<String, String> newEvent = new HashMap<>();
                    newEvent.put("name", name);
                    newEvent.put("date", date);
                    newEvent.put("time", time);
                    newEvent.put("type", type);

                    FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();
                    String currUserUid = currUser.getUid();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    DocumentReference userEvents = db.collection("users")
                            .document("UserEvent");

                    userEvents.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                HashMap allData = (HashMap) document.getData();
                                events = (HashMap) document.get(currUserUid);
                                if (events == null) {
                                    courseEvents = new ArrayList();
                                    generalEvents = new ArrayList();
                                } else {
                                    courseEvents = (ArrayList) events.get("CourseEvents");
                                    generalEvents = (ArrayList) events.get("GeneralEvents");
                                }

                                HashMap<String, ArrayList> prevUserData = new HashMap<>();
                                HashMap<String, ArrayList> newUserData = new HashMap<>();

                                prevUserData.put("CourseEvents", courseEvents);
                                prevUserData.put("GeneralEvents", generalEvents);

                                boolean result = checkAddingData(prevUserData, name, date, time, location, code, type);

                                if (result) {
                                    // When the event is general event (e.g. meeting, extracurricular event)
                                    if (code.equals("")) {
                                        newEvent.put("location", location);
                                        generalEvents.add(newEvent);
                                    } else {
                                        // when the event is assignment/exam/other homeworks
                                        newEvent.put("code", code);
                                        courseEvents.add(newEvent);
                                    }

                                    newUserData.put("GeneralEvents", generalEvents);
                                    newUserData.put("CourseEvents", courseEvents);

                                    allData.put(currUserUid, newUserData);

                                    userEvents.set(allData);
                                    Log.d("Creating Event", "Successfully added new data");
                                    Intent back_to_event = new Intent(CreatingEvent.this,
                                            ActivityEvent.class);
                                    startActivity(back_to_event);
                                    finish();
                                } else {
                                    Log.w("Creating Event", "Failed to add new data");
                                    failed.setVisibility(View.VISIBLE);
                                }
                            } else {
                                Log.d("Creating Event", "failed to obtain the data", task.getException());
                                failed.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }


            }
        });
    }

    /**
     * Check whether new data is duplicated in the database or not.
     * If it is a new data, then return true. Otherwise, return false.
     * @param newData   a dataset with newly added data
     * @param name  the event name
     * @param date  the event date
     * @param time  the event time
     * @param location  the event location
     * @param code  the course code for assignment/exam/others event
     * @param type  the type of the event
     * @return  true/false
     */
    public static boolean checkAddingData(HashMap newData, String name, String date, String time, String location, String code, String type) {
        EventDataConverter dataConverter= new EventDataConverter(newData);
        boolean result;
        if (code.equals("")) {
            result = dataConverter.addNewEvent(name, date, time, location, type);
        } else {
            result = dataConverter.addNewEvent(name, date, time, code, type);
        }
        return result;
    }

}