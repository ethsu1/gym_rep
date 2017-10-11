package android.com.rep;

import java.util.Comparator;

/**
 * Created by ethfm on 8/20/2017.
 */

public class CustomDateComparator implements Comparator<Workouts> {
    @Override
    public int compare(Workouts workouts, Workouts t1) {
        return t1.getDate().compareTo(workouts.getDate());
    }
}
