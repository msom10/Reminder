package msoma.reminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends Activity {

    public static final int ADD_Reminder_REQUEST = 1;
    public static final int DISPLAY_REMINDER_REQUEST = 2;

    private ListView reminderListView;
    private ArrayList<Reminder> remiderListArray;
    private int tappedReminderCursor = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ListView associated with layout
        reminderListView = (ListView)findViewById(R.id.remiderlistView);

        // Create our adapter and associate ArrayList
        remiderListArray = new ArrayList<Reminder>();
        ReminderAdapter adapter = new ReminderAdapter(this, remiderListArray);



        // Associate adapter with ListView
        reminderListView.setAdapter(adapter);

        // Update monster count
        updateRemiderCount();

        reminderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                // Grab the selected Reminder
                Reminder tappedReminder = (Reminder)reminderListView.getAdapter().getItem(i);

                //update Tapped Reminder Cursor
                tappedReminderCursor = i;

                Log.d("Title: ",tappedReminder.getTittle());
                Log.d("Desc: ",tappedReminder.getDesc());
                Log.d("Due Date: ",tappedReminder.getDueDate().toString());
                Log.d("Completed",String.valueOf(tappedReminder.isReminderStatus()));
                Log.d("Size of Reminders: "," "+remiderListArray.size());
                Log.d("Tapped Reminder: "," "+i);

                Intent intent = new Intent(getApplicationContext(),reminderDetail.class);
                intent.putExtra("tappedReminder",tappedReminder);
                intent.putExtra("cursor",i);
                startActivityForResult(intent, DISPLAY_REMINDER_REQUEST);
            }
        });


    }

    private void updateRemiderCount() {

        int totalReminder = remiderListArray.size();
        TextView reminderCountText = (TextView)findViewById(R.id.remindertext);

        if(totalReminder == 0){
            reminderCountText.setText("No Reminder! Add Reminder to avoid forgetting.");
        }
        else{
            reminderCountText.setText("Total Reminders: " + totalReminder);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                // Move to AddMonsterActivity and await result
                Intent i = new Intent(this, addReminder.class);
                startActivityForResult(i, ADD_Reminder_REQUEST);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_Reminder_REQUEST) {
            if (resultCode == RESULT_OK) {
                // Grab the Reminder object out of the intent
                Reminder m = data.getParcelableExtra("addReminder");

                Log.d(m.getDesc(),m.getTittle()+" "+m.getDueDate()+" "+m.isReminderStatus());


                remiderListArray.add(m);

                //sorting reminders
                Collections.sort(remiderListArray);

                // Apply new adapter and update count
                reminderListView.setAdapter(new ReminderAdapter(this, remiderListArray));
                updateRemiderCount();
            }
        }

        if (requestCode == DISPLAY_REMINDER_REQUEST) {
            if (resultCode == RESULT_OK) {

                boolean isComplete = data.getExtras().getBoolean("isComplete");
                Log.d("Completed? :",String.valueOf(isComplete));
                Log.d("Tapped Reminder: "," "+tappedReminderCursor);

                //Setting Reminder Status retreived from Reminder detail page
                remiderListArray.get(tappedReminderCursor).setReminderStatus(isComplete);

                //sorting reminder
                Collections.sort(remiderListArray);

                reminderListView.setAdapter(new ReminderAdapter(this, remiderListArray));
                updateRemiderCount();
            }
        }

    }

}
