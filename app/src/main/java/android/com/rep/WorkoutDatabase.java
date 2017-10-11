package android.com.rep;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ethfm on 8/19/2017.
 */

public class WorkoutDatabase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Workouts";
    public static final String TABLE_NAME = "Workouts_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "date";
    public static final String COL_3 = "exercise";
    public static final String COL_4 = "weights";
    public static final String COL_5 = "sets";
    public static final String COL_6 = "reps";
    public static final String COL_7 = "projected_max";

    public WorkoutDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WORKOUT_TABLE = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT NOT NULL, exercise TEXT NOT NULL, weights TEXT NOT NULL, " +
                "sets TEXT NOT NULL, reps TEXT NOT NULL, projected_max TEXT NOT NULL)";
        db.execSQL(CREATE_WORKOUT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addWorkout(Workouts workout) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(COL_1, workout.getId());
        values.put(COL_2, workout.getDate());
        values.put(COL_3, workout.getExercise());
        values.put(COL_4, workout.getWeight());
        values.put(COL_5, workout.getSets());
        values.put(COL_6, workout.getReps());
        values.put(COL_7, workout.getProjMax());
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }


    public ArrayList<Workouts> getAllWorkouts() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Workouts> allWorkouts = new ArrayList<Workouts>();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Workouts workout = new Workouts();
                workout.setID(Integer.parseInt(cursor.getString(0)));
                workout.setDate(cursor.getString(1));
                workout.setExercise(cursor.getString(2));
                workout.setWeight(Integer.parseInt(cursor.getString(3)));
                workout.setSets(Integer.parseInt(cursor.getString(4)));
                workout.setReps(Integer.parseInt(cursor.getString(5)));
                workout.getProjMax();//Integer.parseInt(cursor.getString(6)));
                allWorkouts.add(workout);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return allWorkouts;
    }

    public ArrayList<Workouts> getDateWorkouts(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Workouts> datedWorkouts = new ArrayList<Workouts>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_1,
                        COL_2, COL_3, COL_4, COL_5, COL_6, COL_7}, COL_2 + "=?",
                new String[]{date}, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Workouts workout = new Workouts();
                    workout.setID(Integer.parseInt(cursor.getString(0)));
                    workout.setDate(cursor.getString(1));
                    workout.setExercise(cursor.getString(2));
                    workout.setWeight(Integer.parseInt(cursor.getString(3)));
                    workout.setSets(Integer.parseInt(cursor.getString(4)));
                    workout.setReps(Integer.parseInt(cursor.getString(5)));
                    workout.getProjMax();//Integer.parseInt(cursor.getString(6)));
                    datedWorkouts.add(workout);
                }
                while (cursor.moveToNext());
            }
        }
        db.close();
        return datedWorkouts;
    }
    public ArrayList<Workouts> getTypeExercise(String exercise) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Workouts> typeExercise = new ArrayList<Workouts>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_1,
                        COL_2, COL_3, COL_4, COL_5, COL_6, COL_7}, COL_3 + "=?",
                new String[]{exercise}, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Workouts workout = new Workouts();
                    workout.setID(Integer.parseInt(cursor.getString(0)));
                    workout.setDate(cursor.getString(1));
                    workout.setExercise(cursor.getString(2));
                    workout.setWeight(Integer.parseInt(cursor.getString(3)));
                    workout.setSets(Integer.parseInt(cursor.getString(4)));
                    workout.setReps(Integer.parseInt(cursor.getString(5)));
                    workout.getProjMax();//Integer.parseInt(cursor.getString(6)));
                    typeExercise.add(workout);
                }
                while (cursor.moveToNext());
            }
        }
        db.close();
        return typeExercise;
    }

        /*SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Workouts> datedWorkouts = new ArrayList<Workouts>();
        String query = "SELECT * FROM " + TABLE_NAME + "WHERE" + COL_2 + "=" + date;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                Workouts workout = new Workouts();
                workout.setID(Integer.parseInt(cursor.getString(0)));
                workout.setDate(cursor.getString(1));
                workout.setExercise(cursor.getString(2));
                workout.setWeight(Integer.parseInt(cursor.getString(3)));
                workout.setSets(Integer.parseInt(cursor.getString(4)));
                workout.setReps(Integer.parseInt(cursor.getString(5)));
                workout.setProjMax(Integer.parseInt(cursor.getString(6)));
                datedWorkouts.add(workout);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return datedWorkouts;*/

   /** public ArrayList<ExerciseDates> getAllDates() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ExerciseDates> allDates = new ArrayList<ExerciseDates>();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ExerciseDates date = new ExerciseDates();
                date.setId(Integer.parseInt(cursor.getString(0)));
                date.setDate(cursor.getString(1));
                allDates.add(date);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return allDates;
    }
*/
    public Workouts getWorkout(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { COL_1,
                        COL_2, COL_3,COL_4,COL_5,COL_6,COL_7 }, COL_1 + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Workouts workout = new Workouts(cursor.getString(1),cursor.getString(2), Integer.parseInt(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)),Integer.parseInt(cursor.getString(5)));

        return workout;
    }
    /**public ExerciseDates getDates(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { COL_1,
                        COL_2 }, COL_1 + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        ExerciseDates date = new ExerciseDates(cursor.getString(1));

        return date;
    }*/
    public void deleteDate(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_2 + "=?", new String[]{date});
        db.close();
    }
    public void deleteExercise(String content) {
        String[] words = content.split(" ");
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(words));
        String[] eliminate = new String[] {"Date:", "Exercise:", "Weight:", "Sets:", "Reps:"};
        ArrayList<String> trash = new ArrayList<String>(Arrays.asList(eliminate));
        list.removeAll(trash);
        for(int i = 0; i < list.size();i++) {
            Log.i("stuff", list.get(i));

        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_2 + "=? AND " + COL_3 + "=? AND " + COL_4 + "=? AND "
                + COL_5 + "=? AND " + COL_6 + "=?", new String[] {list.get(0),list.get(1),list.get(2),list.get(3),list.get(4)});
        db.close();

    }

}