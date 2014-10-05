package com.example.coen268_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ToDoContacts extends Activity {
	
	private String[] items = {" Add Item Details ", "Call/Map Contacts"};
	private ListView itemsListView;
	private ArrayAdapter arrayAdapter;	
	String item_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_do_contacts);
		
		item_name=getIntent().getExtras().getString("item_name");
		//Log.d("MY_DEBUG", "TO DO Item Name="+item_name);
		
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
            	 Intent i = new Intent(getApplicationContext(), SubItemList.class);
            	 i.putExtra("item_name",item_name);
	              startActivity(i);
            	}
            	if(position==1)
            	{
            	 Intent i = new Intent(getApplicationContext(), ContactNames.class);
            	 i.putExtra("item_name",item_name);
	              startActivity(i);
            	}
            	
	             
            }
        });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.to_do_contacts, menu);
		return true;
	}
	
	@Override
	protected void onResume() {

	   super.onResume();
	   this.onCreate(null);
	}     


}
