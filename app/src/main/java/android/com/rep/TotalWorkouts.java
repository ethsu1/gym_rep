package android.com.rep;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import static android.com.rep.R.styleable.View;

public class TotalWorkouts extends AppCompatActivity {
    private ListView mylistview;
    WorkoutDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_workouts);
        mylistview = (ListView) findViewById(R.id.listview);
        mylistview.setChoiceMode(mylistview.CHOICE_MODE_MULTIPLE);
        db = new WorkoutDatabase(this);
        Bundle data = getIntent().getExtras();
        String dateData = data.getString("date");
        ArrayList<Workouts> workout_total = db.getDateWorkouts(dateData);
        Collections.sort(workout_total, new CustomDateComparator());
        ArrayList<String> total = new ArrayList<String>();
        String entries;
        //listview of all exercises for all dates
        for (int i = 0; i < workout_total.size(); i++) {
            entries = workout_total.get(i).toString();
            total.add(entries);
        }
        //ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, total);
        ListAdapter adapter = new MyListViewAdapterTwo(TotalWorkouts.this, total);
        mylistview.setAdapter(adapter);
        /*mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {
                db.deleteExercise(l);
                Toast.makeText(TotalWorkouts.this, "deleted Exercise", Toast.LENGTH_SHORT).show();
            }
        });*/
        }
    }

