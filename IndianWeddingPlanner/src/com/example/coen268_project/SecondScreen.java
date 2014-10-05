/*
MEENAKSHY HARIKUMAR 
SCU ID :1001341
COEN 268- Project
*/
package com.example.coen268_project;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class SecondScreen extends Activity {
	
	EditText mEdit;
	ListView myListView;
	String planner_name;
	String selected_state;
	String selected_community;
	String is_planner_created;
	Button create, exit;
    
	SharedPreferences sh_Pref;
    Editor toEdit;
    
    private Cursor subitems;
	private MyAssetDb db;
	
	SQLiteDatabase items_db; 
	ContentValues newValues;
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second_screen);
		
		Spinner spState;
		 spState = (Spinner) findViewById(R.id.spinner1);
		// State Item Selected Listener
		spState.setOnItemSelectedListener(new OnItemSelectedListener() {
 
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                    int position, long id) {
                // On selecting a spinner item
                String item = adapter.getItemAtPosition(position).toString();
                selected_state=item;
            }
 
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
 
            }
        });
		//Second Spinner- Community Option
		Spinner spCommunity;
		 spCommunity = (Spinner) findViewById(R.id.spinner2);
		// State Item Selected Listener
		spCommunity.setOnItemSelectedListener(new OnItemSelectedListener() {

           @Override
           public void onItemSelected(AdapterView<?> adapter, View v,
                   int position, long id) {
               // On selecting a spinner item
               String item = adapter.getItemAtPosition(position).toString();
               selected_community=item;
           }

           @Override
           public void onNothingSelected(AdapterView<?> arg0) {
               // TODO Auto-generated method stub

           }
       });
	}
	
	public void sharedPrefernces() {
		 
        sh_Pref = getSharedPreferences("Planner Details", MODE_PRIVATE);
        toEdit = sh_Pref.edit();
        is_planner_created="yes";
        toEdit.putString("Planner Name",planner_name);
        toEdit.putString("Planner State",selected_state);
        toEdit.putString("Planner community",selected_community);        
        toEdit.putString("Planner Flag",is_planner_created);
        toEdit.commit();
 
    }
	
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second_screen, menu);
		return true;
	}
	
	 public void createPlanner(View view) {
	     // Start second activity response to button
		 mEdit= (EditText) findViewById(R.id.editText1);	  
	     //Toast.makeText(getApplicationContext(), mEdit.getText().toString(), Toast.LENGTH_SHORT).show();
	     planner_name=mEdit.getText().toString();
	     
	   
	     //Save Planner name, state, community to shared preference
	     sharedPrefernces();
	     
	     //code added before presentation
	     db = new MyAssetDb(this);
	     String item_name="Jewellery";
			subitems = db.getSubItems(selected_state,selected_community);
			String arr[]=new String[100];
			items_db = new ItemsDB(this).getWritableDatabase();
			newValues = new ContentValues();
			while (subitems.moveToNext()) 
			{
				//Insert values corresponding to state and community from assets into ItemsDB
				
				
				newValues.put(ItemsDB.ITEM_NAME, subitems.getString(1));
				newValues.put(ItemsDB.SUBITEM_NAME, subitems.getString(4));
				items_db.insert(ItemsDB.DATABASE_TABLE, null, newValues);
				
				Log.d("MY_ASSET", "Subitem = "+subitems);
				
			}
			/*
			subitems.moveToFirst();
			Log.d("MY_ASSET", "Subitem = "+subitems.getString(4));
			subitems.moveToNext();
			Log.d("MY_ASSET", "Subitem = "+subitems.getString(4));
			*/
	     items_db.close();
		 Intent intent = new Intent(this, ItemList.class);
		    startActivity(intent);
	 }
	 
	 
	 
	
	 
}
