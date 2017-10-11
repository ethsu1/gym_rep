package android.com.rep;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;


public class GraphPage extends AppCompatActivity {
    private EditText exercise;
    WorkoutDatabase db;
    LineChart graph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_page);
        Button progression = (Button) findViewById(R.id.progressionbtn);
        db = new WorkoutDatabase(this);
        graph = (LineChart) findViewById(R.id.graph);
        exercise = (EditText) findViewById(R.id.enterExercise);
        progression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Workouts> typeExercise = db.getTypeExercise(exercise.getText().toString());
                ArrayList<Entry> entries = new ArrayList<Entry>();
                final ArrayList<String> dates = new ArrayList<String>();
                for(int i = 0; i<typeExercise.size();i++) {
                    entries.add(new Entry(i,(float)typeExercise.get(i).getProjMax()));
                    //Log.i("Max", Integer.toString(typeExercise.get(i).getProjMax()));
                    dates.add(typeExercise.get(i).getDate());
                }
                LineDataSet data = new LineDataSet(entries,exercise.getText().toString() + " Progress");
                List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
                dataSets.add(data);
                LineData datas = new LineData(dataSets);
                graph.setData(datas);
                IAxisValueFormatter formatter = new IAxisValueFormatter() {

                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return dates.get((int) value);
                    }

                    // we don't draw numbers, so no decimal digits needed
                    public int getDecimalDigits() {  return 0; }
                };

                XAxis xAxis = graph.getXAxis();
                xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
                xAxis.setValueFormatter(formatter);
                graph.invalidate();
            }
        });
    }
}
