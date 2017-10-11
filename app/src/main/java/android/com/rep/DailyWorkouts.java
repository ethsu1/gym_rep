package android.com.rep;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DailyWorkouts extends AppCompatActivity  {

    private ListView mylistview;
    WorkoutDatabase db;

    //List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_workouts);
        mylistview = (ListView) findViewById(R.id.listview);
        db = new WorkoutDatabase(this);
        ArrayList<Workouts> date_total = db.getAllWorkouts();
        Collections.sort(date_total, new CustomDateComparator());
        ArrayList<String> total = new ArrayList<String>();
        String entries;
        //creates dates listview
        for (int i = 0; i < date_total.size(); i++) {
            entries = date_total.get(i).getDate().toString();
            if (!total.contains(entries)) {
                total.add(entries);
            }
        }
        ListAdapter adapter = new MyListViewAdapter(DailyWorkouts.this, total);
        //adapter.setCustomButtonListener();
        mylistview.setAdapter(adapter);
        //ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, total);
        //mylistview.setAdapter(adapter);
        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), TotalWorkouts.class);
                intent.putExtra("date", mylistview.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });
    }

}




