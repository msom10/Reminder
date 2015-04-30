package msoma.reminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;


public class reminderDetail extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_detail);

        Intent i = getIntent();
        Reminder m = i.getParcelableExtra("tappedReminder");

        TextView detTitle = (TextView)findViewById(R.id.detailTitle);
        TextView detDesc= (TextView)findViewById(R.id.detailDesc);
        TextView detDueDate = (TextView)findViewById(R.id.detailDueDate);
        CheckBox isCompleteChkBox = (CheckBox)findViewById(R.id.statusCheckBox);


        detTitle.setText(m.getTittle());
        detDesc.setText(m.getDesc());
        detDueDate.setText(m.getDueDate().toString());
        isCompleteChkBox.setChecked(m.isReminderStatus());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reminder_detail, menu);
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

    public void backToReminderPressed(View v){

        boolean isComplete = ((CheckBox) findViewById(R.id.statusCheckBox)).isChecked();

        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        i.putExtra("isComplete",isComplete);

        setResult(RESULT_OK, i);
        finish();

    }



}
