package com.example.coen268_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Reminders extends Activity {
	
	private String[] items = {"Set New Reminder", "View Reminders"};
	private ListView itemsListView;
	private ArrayAdapter arrayAdapter;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reminders);
		
		// Initialize the UI components
	    itemsListView = (ListView) findViewById(R.id.listView1);
	    arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
	    itemsListView.setAdapter(arrayAdapter);
	    itemsListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
                
            	if(position==0)
            	{
            	 Intent i = new Intent(getApplicationContext(), SetNewReminder.class);
	              startActivity(i);
            	}
	             
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reminders, menu);
		return true;
	}

}
