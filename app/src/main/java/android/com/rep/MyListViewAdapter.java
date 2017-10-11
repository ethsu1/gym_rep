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
 * helps delete an entire workout based on date
 */

public class MyListViewAdapter extends ArrayAdapter<String> {
    /*customButtonListener customListener;

    public interface customButtonListener {
        public void onButtonClickListener(int position,String value);
    }

        public void setCustomButtonListener(customButtonListener listener) {
        this.customListener = listener;
    }*/

    private Context context;
    private ArrayList<String> data = new ArrayList<String>();

    public MyListViewAdapter(Context context, ArrayList<String> dataItem) {
        super(context, R.layout.activity_single_list_view, dataItem);
        this.data = dataItem;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.activity_single_list_view, null);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView
                    .findViewById(R.id.listview_text);
            viewHolder.button = (Button) convertView
                    .findViewById(R.id.listview_delete_btn);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final String temp = getItem(position);
        viewHolder.text.setText(temp);
        viewHolder.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v != null) {
                    WorkoutDatabase db = new WorkoutDatabase(getContext());
                    db.deleteDate(temp);
                    //v.onButtonClickListener(position,temp);
                    remove(temp);
                    notifyDataSetChanged();
                    Toast.makeText(context, "deleted workout", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return convertView;
    }

    public class ViewHolder {
        TextView text;
        Button button;
    }
}

