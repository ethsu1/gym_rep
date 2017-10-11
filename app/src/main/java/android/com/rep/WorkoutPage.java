package android.com.rep;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.com.rep.TotalWorkouts;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WorkoutPage extends AppCompatActivity {
    private EditText dateText;
    private EditText exercise;
    private EditText weight;
    private EditText sets;
    private EditText reps;
    private DatePicker currentDate;
    private SimpleDateFormat dateFormat;
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener date;
    private WorkoutDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_page);
        //create pop-up calendar for selecting workout date
        dateText = (EditText) findViewById(R.id.Date);
        calendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month,
                                  int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                dateText.setText(sdf.format(calendar.getTime()));
            }
        };
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(WorkoutPage.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        db = new WorkoutDatabase(this);
        Button addworkout = (Button) findViewById(R.id.AddWorkout);
        addworkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Workouts workout = new Workouts();
                int count = 0;

                workout.setDate(dateText.getText().toString());
                if (dateText.length() != 0) {
                    workout.setDate(dateText.getText().toString());
                    count+=1;
                } else {
                    toastMessage("You must put a date");
                }

                exercise = (EditText) findViewById(R.id.Exercise);
                if (exercise.length() != 0) {
                    workout.setExercise(exercise.getText().toString());
                    count+=1;
                } else {
                    toastMessage("You must put an exercise");
                }

                weight = (EditText) findViewById(R.id.Weight);
                if (weight.length() != 0 && isInteger(weight.getText().toString())) {
                    workout.setWeight(Integer.parseInt(weight.getText().toString()));
                    count+=1;
                } else {
                    toastMessage("You must put a weight");
                }
                sets = (EditText) findViewById(R.id.Sets);
                if (exercise.length() != 0&& isInteger(sets.getText().toString())) {
                    workout.setSets(Integer.parseInt(sets.getText().toString()));
                    count+=1;
                } else {
                    toastMessage("You must put a set number");
                }
                reps = (EditText) findViewById(R.id.Reps);
                if (exercise.length() != 0&&isInteger(reps.getText().toString())) {
                    workout.setReps(Integer.parseInt(reps.getText().toString()));
                    workout.setProjMax(Integer.parseInt(weight.getText().toString()));
                    //Log.d("reps2", Integer.toString(workout.getReps()));
                    count+=1;
                } else {
                    toastMessage("You must put a rep number");
                }
                if(count==5) {
                    boolean insertData = db.addWorkout(workout);
                    toastMessage("Workout Successfully Inserted!");
                } else {
                    toastMessage("Something went wrong");
                }

            }
        });
        Button seeWorkout = (Button) findViewById(R.id.workouts);
        seeWorkout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkoutPage.this, DailyWorkouts.class);
                startActivity(intent);
            }
        });
    db.close();
    }
    private void toastMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
    private void showDate(int year, int month, int day) {
        dateText.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
