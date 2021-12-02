package com.example.myapplication.community;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);


        ListView listView = findViewById(R.id.listview);

        List<String> list = new ArrayList<>();
        list.add("Fuk");
        list.add("Android");
        list.add("Studio");
        list.add("This");
        list.add("Caused");
        list.add("So");
        list.add("So");
        list.add("So");
        list.add("Many");
        list.add("Bugs");
        list.add("gaaaaaaa");
        list.add("aaaaaaaaaaaa");
        list.add("aaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        list.add("hhhhhhhhhhhhhhhhhh");
        list.add("ahsdajhdal");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
        }
}