package android.com.rep;

/**
 * Created by ethfm on 9/4/2017.
 */

public class ExerciseDates {
    private int id;
    private String date;
    public ExerciseDates(){

    }
    public ExerciseDates(String date){
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public String toString() {
        return this.getDate();
    }
}
