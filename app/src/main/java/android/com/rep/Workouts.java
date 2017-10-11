package android.com.rep;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ethfm on 8/19/2017.
 */

public class Workouts {
    private int id;
    private String exercise;
    private String date;
    private int set;
    private int reps;
    private int weight;
    private int projMax;
    //Constructors
    public Workouts(){
    }

    public Workouts(String date, String exercise, int weight, int set, int reps) {
        this.date = date;
        this.exercise = exercise;
        this.weight = weight;
        this.set = set;
        this.reps = reps;
        //this.projMax =  (int)((weight)/(1.0278-(.0278*this.reps)));
    }
    //getter methods
    public int getId() {
        return this.id;
    }
    public String getExercise() {
        return this.exercise;
    }
    public String getDate(){
        return this.date;
    }
    public int getWeight(){
        return this.weight;
    }
    public int getSets(){
        return this.set;
    }
    public int getReps(){
        return this.reps;
    }
    public int getProjMax(){
        return (int)((this.getWeight())/(1.0278-(.0278*this.getReps())));
    }
    //setter methods
    public void setID(int id) {
        this.id = id;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setExercise(String exercise){
        this.exercise = exercise;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    public void setSets(int set){
        this.set = set;
    }
    public void setReps(int reps){
        this.reps = reps;
    }
    public void setProjMax(int max) {
        this.projMax = (int)((max)/(1.0278-(.0278*this.getReps())));
        //Log.i("bitchass", Integer.toString(this.projMax));
    }
    public String toString(){
        String date = "Date: " + this.getDate();
        String exercise = "Exercise: " + this.getExercise();
        String weight = "Weight: " + this.getWeight();
        String sets = "Sets: " + this.getSets();
        String reps = "Reps: " + this.getReps();
        //String max = "Max: " + this.getProjMax();
        return date + " " + exercise + " " + weight + " " + sets + " " + reps;
    }
}
