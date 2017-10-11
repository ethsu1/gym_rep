package android.com.rep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Phoebe on 9/16/17.
 * deletes a singular exercise
 */

public class MyListViewAdapterTwo extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<String> data = new ArrayList<String>();

    public MyListViewAdapterTwo(Context context, ArrayList<String> dataItem) {
        super(context, R.layout.activity_single_list_view_two, dataItem);
        this.data = dataItem;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MyListViewAdapterTwo.ViewHolderTwo viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.activity_single_list_view_two, null);
            viewHolder = new MyListViewAdapterTwo.ViewHolderTwo();
            viewHolder.text = (TextView) convertView
                    .findViewById(R.id.listviewtwo_text);
            viewHolder.button = (Button) convertView
                    .findViewById(R.id.listviewtwo_delete_btn);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyListViewAdapterTwo.ViewHolderTwo) convertView.getTag();
        }
        final String temp = getItem(position);
        viewHolder.text.setText(temp);
        viewHolder.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v != null) {
                    WorkoutDatabase db = new WorkoutDatabase(getContext());
                    db.deleteExercise(temp);
                    data.remove(position);
                    Toast.makeText(context, "deleted exercise", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }

            }
        });

        return convertView;
    }

    public class ViewHolderTwo {
        TextView text;
        Button button;
    }
}

