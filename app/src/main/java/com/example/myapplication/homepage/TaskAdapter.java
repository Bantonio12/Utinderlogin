package com.example.myapplication.homepage;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.os.CountDownTimer;

import java.sql.SQLOutput;
import java.time.LocalTime;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.myapplication.R;
import java.util.List;
import java.time.*;

public class TaskAdapter extends ArrayAdapter<String> {
    private CountDownTimer countDownTimer;
    private List<String> tasks;
    private List<Boolean> checked;
    public TaskAdapter(Context context, int resourceId, ArrayList<String> tasks) {
        super(context, resourceId, tasks);
        this.tasks = tasks;
        checked = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            checked.add(false);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View curView = convertView;
        if (curView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            curView = layoutInflater.inflate(R.layout.activity_taskitem, null);
        }
        String task = getItem(position);
        TextView name = curView.findViewById(R.id.taskname);
        name.setText(task);

        LocalTime datetime = LocalTime.now();

        final CheckBox checkbox = curView.findViewById(R.id.checkboxforaddingtask);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checked.set(position, checkbox.isChecked());
                if (b){
                    System.out.println("Checked");
                    delete(position);
                    checkbox.setChecked(false);
                }
            }
        });

//        if(datetime.getHour() == 18 && datetime.getMinute() == 25){
//            for(int i=0; i <= checked.size(); i++){
//                if(checked.get(i)){
//                    delete(i);
//                }
//            }
//        }

        return curView;
    }

    public void delete(int position) {
        this.tasks.remove(position);
        this.checked.remove(position);

        super.notifyDataSetChanged();
    }

    public List<Boolean> getCheckBoxList() {
        return this.checked;
    }

    public void notifyDataSetChanged2() {
        super.notifyDataSetChanged();
        checked.add(false);
    }
}
