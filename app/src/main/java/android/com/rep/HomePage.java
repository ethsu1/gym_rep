package android.com.rep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.com.rep.TotalWorkouts;
import android.com.rep.WorkoutPage;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Button seeWorkouts = (Button) findViewById(R.id.SeeWorkouts);
        seeWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, DailyWorkouts.class);
                startActivity(intent);
            }
        });
        Button addWorkouts = (Button) findViewById(R.id.AddWorkouts);
        addWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, WorkoutPage.class);
                startActivity(intent);
            }
        });
        Button progression = (Button) findViewById(R.id.SeeProgression);
        progression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, GraphPage.class);
                startActivity(intent);
            }
        });
    }
}
