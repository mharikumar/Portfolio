package com.example.coen268_project;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class SetNewReminder extends Activity {
	
	private DatePicker picker;
	private TimePicker t_picker;
	private ScheduleClient setReminder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_new_reminder);
		
		picker = (DatePicker) findViewById(R.id.datePicker1);
		t_picker=(TimePicker) findViewById(R.id.timePicker1);
		
		// Create a new service client and bind our activity to this service
		setReminder = new ScheduleClient(this);
		setReminder.doBindService();
	}
	
	
	public void onTimeSelected(View v)
	{
		int day = picker.getDayOfMonth();
        int month = picker.getMonth()+1;
        int year = picker.getYear();        
        Toast.makeText(this, month+"/"+day+"/"+year, Toast.LENGTH_SHORT).show();
		t_picker.clearFocus();
		 int hour = t_picker.getCurrentHour();
	      int minute = t_picker.getCurrentMinute();
	      Toast.makeText(this, hour+":"+minute, Toast.LENGTH_SHORT).show();
	      
	   // Create a new calendar set to the date chosen
	        // we set the time to midnight (i.e. the first minute of that day)
	        Calendar c = Calendar.getInstance();
	        c.set(year, month, day);
	        c.set(Calendar.HOUR_OF_DAY, hour);
	        c.set(Calendar.MINUTE, minute);
	        c.set(Calendar.SECOND, 0);
	        // Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
	        setReminder.setAlarmForNotification(c);
	        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_new_reminder, menu);
		return true;
	}
	
	@Override
    protected void onStop() {
        // When our activity is stopped ensure we also stop the connection to the service
        // this stops us leaking our activity into the system *bad*
        if(setReminder != null)
        	setReminder.doUnbindService();
        super.onStop();
    }

}
