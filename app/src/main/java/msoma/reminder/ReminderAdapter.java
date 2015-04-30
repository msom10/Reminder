package msoma.reminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Mohanish on 14/04/15.
 */
public class ReminderAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Reminder> reminders;

    public ReminderAdapter(Context context, ArrayList<Reminder> reminders) {
        this.context = context;
        this.reminders = reminders;
    }

    @Override
    public int getCount() {
        return reminders.size();
    }

    @Override
    public Object getItem(int position) {
        return reminders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the view has been created for the row. If not, lets inflate it
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_list_reminder_item, null); // List layout here
        }

        // Grab the TextViews in our custom layout
        TextView reminderName = (TextView)convertView.findViewById(R.id.reminderNameText);
        TextView reminderDueDate = (TextView)convertView.findViewById(R.id.dueDateText);

        //format date
        Date tempdueDate = reminders.get(position).getDueDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

        //Assign values to the TextViews using the Monster object
        reminderName.setText(reminders.get(position).getTittle());
        reminderDueDate.setText("Due Date: " +dateFormat.format(tempdueDate));
        //reminderDueDate.setText("Due Date: " + reminders.get(position).getDueDate());

        return convertView;
    }
}
