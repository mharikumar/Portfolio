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

public class SubItemFoldersList extends Activity {
	
	private String[] items = {"Gallery", "Reminders","Cost","Contact"};
	private ListView itemsListView;
	private ArrayAdapter arrayAdapter;	
	String subitem_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub_item_folders_list);
		
		subitem_name=getIntent().getExtras().getString("subitem_name");
		
		// Initialize the UI components
	    itemsListView = (ListView) findViewById(R.id.listView1);
	    arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
	    itemsListView.setAdapter(arrayAdapter);
	    itemsListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
            	
                
            	if(position==1)
            	{
            	 Intent i = new Intent(getApplicationContext(), Reminders.class);
            	 i.putExtra("subitem_name",subitem_name); // To carry forward name of sub item into next activity.
	              startActivity(i);
            	}
            	if(position==2)
            	{
            	 Intent i = new Intent(getApplicationContext(), SetSubItemCost.class);
            	 i.putExtra("subitem_name",subitem_name); // To carry forward name of sub item into next activity.
	              startActivity(i);
            	}
            	if(position==3)
            	{
            	 Intent i = new Intent(getApplicationContext(), SetSubItemContact.class);
            	 i.putExtra("subitem_name",subitem_name); // To carry forward name of sub item into next activity.
	              startActivity(i);
            	}
	             
            }
        });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sub_item_folders_list, menu);
		return true;
	}

}
