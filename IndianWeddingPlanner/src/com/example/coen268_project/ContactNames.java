package com.example.coen268_project;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class ContactNames extends Activity {

	//private String[] items = {"Bhima Jewellers ", "Josco Jewellers"};
	private ListView itemsListView;
	private ArrayAdapter arrayAdapter;	
	String item_name;
	//SQLiteDatabase db;
	private Cursor contacts;
	private ItemsDB db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_names);
		
		item_name=getIntent().getExtras().getString("item_name");
		
		db = new ItemsDB(this);
		contacts = db.getSubItems(item_name, "CONTACT_NAME");
		
		ListAdapter adapter = new SimpleCursorAdapter(this, 
				android.R.layout.simple_list_item_1, 
				contacts, 
				new String[] {"CONTACT_NAME"}, 
				new int[] {android.R.id.text1});
		
		// Initialize the UI components
	    itemsListView = (ListView) findViewById(R.id.listView1);
	    //arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
	    //itemsListView.setAdapter(arrayAdapter);
	    itemsListView.setAdapter(adapter);
	    itemsListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
                
            	 contacts.moveToPosition(position);
            	 String contact_name = contacts.getString(contacts.getColumnIndex("CONTACT_NAME"));
            	 String contact_number= contacts.getString(contacts.getColumnIndex("CONTACT_NUMBER"));
            	 String contact_add= contacts.getString(contacts.getColumnIndex("CONTACT_ADD"));
            	 
            	 Intent i = new Intent(getApplicationContext(), ContactDetails.class);
            	 
            	 Bundle extras = new Bundle();
				 extras.putString("EXTRA_NAME",contact_name);
				 extras.putString("EXTRA_NUMBER", contact_number);
				 extras.putString("EXTRA_ADD", contact_add);
				 i.putExtras(extras);
    
           // 	 i.putExtra("item_name",item_name);
	              startActivity(i);
            	
            	
	             
            }
        });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_names, menu);
		return true;
	}
	
	@Override
	protected void onResume() {

	   super.onResume();
	   this.onCreate(null);
	}     

	
	

}
