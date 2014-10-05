/*
MEENAKSHY HARIKUMAR 
SCU ID :1001341
COEN 268- Project
*/
package com.example.coen268_project;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class FirstScreen extends Activity {
	
	SharedPreferences sh_Pref;
	SharedPreferences sh_Pref_two;
	Editor toEdit;
	TextView tv1;
	SQLiteDatabase items_db;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_screen);
		
		sh_Pref = getSharedPreferences("Planner Details", MODE_PRIVATE);
		String name = sh_Pref.getString("Planner Name", "");
		String flag = sh_Pref.getString("Planner Flag", "");
		
		tv1 = (TextView) findViewById(R.id.textView1);
		
		if(flag.equals("yes"))
		{	
		
		tv1.setText("Welcome to "+ name); 
		View b = findViewById(R.id.button1);
		b.setVisibility(View.INVISIBLE);
		
		}
		else
		{
			View c = findViewById(R.id.button2);
			c.setVisibility(View.INVISIBLE);
			View d = findViewById(R.id.button3);
			d.setVisibility(View.INVISIBLE);
			View e = findViewById(R.id.button4);
			e.setVisibility(View.INVISIBLE);
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first_screen, menu);
		return true;
	}
	
	/** Called when the user clicks the CreatePlanner button */
	 public void startSecondActivity(View view) {
	     // Start second activity response to button
		 Intent intent = new Intent(this, SecondScreen.class);
		    startActivity(intent);
	 }
	 
	 /** Called when the user clicks the ViewPlanner button */
	 public void startItemListActivity(View view) {
	     // Start second activity response to button
		 Intent intent = new Intent(this, ItemList.class);
		    startActivity(intent);
	 }
	 
	 public void startBudgetActivity(View view) {
	     // Start second activity response to button
		 Intent intent = new Intent(this, Budget.class);
		    startActivity(intent);
	 }
	 
	 public void resetDb(View view)
	 {
		 items_db = new ItemsDB(this).getWritableDatabase();
		 intent = new Intent(this, FirstScreen.class);
		 
		 AlertDialog.Builder alert = new AlertDialog.Builder(FirstScreen.this);
		    
	        alert.setTitle("Reset Planner");
	        alert.setMessage("This will erase all saved planner data. Are you sure you want to continue?");
	        alert.setPositiveButton("YES", new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                // TOD O Auto-generated method stub
	                    
	                    // main code on after clicking yes
	            	 
	        		 items_db.delete(ItemsDB.DATABASE_TABLE, null, null);
	        		 sh_Pref_two = getSharedPreferences("Planner Details", MODE_PRIVATE);
	        		 toEdit = sh_Pref_two.edit();
	        		 String flag = sh_Pref.getString("Planner Flag", "");
	        		 flag="no";
	        		 toEdit.putString("Planner Flag",flag);
	        		 toEdit.commit();
	        		 
	        		 startActivity(intent);	        			
	      
	            }
	        });
	        alert.setNegativeButton("NO", new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                // TODO Auto-generated method stub
	                dialog.dismiss();
	            }
	        });
	      
	        alert.show();
	        
		
	 }
}
