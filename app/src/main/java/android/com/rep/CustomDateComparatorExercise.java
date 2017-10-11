package android.com.rep;

import java.util.Comparator;

/**
 * Created by ethfm on 9/4/2017.
 */

public class CustomDateComparatorExercise implements Comparator<ExerciseDates> {
    public int compare(ExerciseDates workouts, ExerciseDates t1) {
        return t1.getDate().compareTo(workouts.getDate());
    }
}
