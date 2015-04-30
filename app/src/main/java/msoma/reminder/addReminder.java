package msoma.reminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class addReminder extends Activity {

    private DatePicker dueDate;
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        setCurrentDateOnView();

    }

    public void setCurrentDateOnView() {

        dueDate = (DatePicker) findViewById(R.id.datePicker);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        dueDate.init(year, month, day, null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addReminderPresses(View v){

        // Grab the reference to EditText fields on the layout
        EditText title = (EditText) findViewById(R.id.titleText);
        EditText desc = (EditText) findViewById(R.id.descText);
        DatePicker dueDate = (DatePicker) findViewById(R.id.datePicker);

        int day = dueDate.getDayOfMonth();
        int month = dueDate.getMonth();
        int year = dueDate.getYear();
        Date reminderDueDate = new Date();

        String stringDueDate = day+"-"+month+"-"+year;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            reminderDueDate = dateFormat.parse(stringDueDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(dueDate.getDayOfMonth());
        Log.d("Date is: ",stringDueDate);

        Reminder r = new Reminder();

        String rtitle = title.getText().toString();
        String rdesc = desc.getText().toString();

        r.setTittle(rtitle);
        r.setDesc(rdesc);
        r.setDueDate(reminderDueDate);

        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        i.putExtra("addReminder",r);
        //startActivity(i);
        //setContentView(R.layout.activity_main);
        setResult(RESULT_OK, i);
        finish();
    }
}
