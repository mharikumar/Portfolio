/*
MEENAKSHY HARIKUMAR 
SCU ID :1001341
COEN 268- Project
*/
package com.example.coen268_project;

//import com.example.coen268_homework3.ViewPhotoActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ItemList extends Activity {
	
	private String[] items = {"Jewellery", "Outfits", "Wedding Hall", "Food", "Decoration","Guest List"};
	private ListView itemsListView;
	private ArrayAdapter arrayAdapter;	
	
    //Old code
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_list);
		
		// Initialize the UI components
	    itemsListView = (ListView) findViewById(R.id.listView1);
	    arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
	    itemsListView.setAdapter(arrayAdapter);
	    
	    itemsListView.setOnItemClickListener(new OnItemClickListener() {
	          public void onItemClick(AdapterView<?> parent, View view,
	              int position, long id) {
	        	  
	        	 
	        	  
	        		  Intent i = new Intent(getApplicationContext(), ToDoContacts.class);
		        	  //String item_name=itemsListView.get(position).getName();
		        	  i.putExtra("item_name",items[position]);
		        	  startActivity(i);
	        	  
	              // selected item 
	        	  //String list_index=Integer.toString(position+1);	             
	               
	              // Launching new Activity on selecting single List Item
	              
	              // sending list index to new activity
	              //i.putExtra("list_index", list_index);
	              
	             
	          }
	        });		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item_list, menu);
		return true;
	}

	
}
