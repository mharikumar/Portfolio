/*
MEENAKSHY HARIKUMAR 
SCU ID :1001341
COEN 268- Project
*/
package com.example.coen268_project;

//import com.example.coen268_homework3.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
//import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;

public class SubItemList extends Activity {
	
	private Cursor subitems;
	private ItemsDB db;
	//List<String> arr;
	ListView myListView;
	 //ArrayAdapter<String> adapter;
	SharedPreferences sh_Pref;
    String item_name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub_item_list);
		
		sh_Pref = getSharedPreferences("Planner Details", MODE_PRIVATE);
		String state = sh_Pref.getString("Planner State", "");
		String community = sh_Pref.getString("Planner community", "");
		item_name=getIntent().getExtras().getString("item_name");
		Log.d("MY_DEBUG", "State="+state);
		Log.d("MY_DEBUG", "Community="+community);
		
		db = new ItemsDB(this);
		subitems = db.getSubItems(item_name, "SUBITEM_NAME"); 

		
		ListAdapter adapter = new SimpleCursorAdapter(this, 
				android.R.layout.simple_list_item_1, 
				subitems, 
				new String[] {"SUBITEM_NAME"}, 
				new int[] {android.R.id.text1});
		
		myListView = (ListView) findViewById(R.id.listView1);
		myListView.setAdapter(adapter);
		myListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
                
            	 Intent i = new Intent(getApplicationContext(), SubItemFoldersList.class);
            	 
            	 //String subitemname=myListView.getItemAtPosition(position).getString();
            	 
            	 //Cursor mycursor = (Cursor) getListView().getItemAtPosition(position);
            	 //String subitemname= mycursor.getString(4);
            	 subitems.moveToPosition(position);
                 String subitemname = subitems.getString(subitems.getColumnIndex("SUBITEM_NAME"));
            	//Toast.makeText(getApplicationContext(), subitemname.toString(), Toast.LENGTH_SHORT).show();
            	
            	 i.putExtra("subitem_name",subitemname); // To carry forward name of sub item into next activity. 
	              startActivity(i);
	             
            }
        });
		
			
		myListView.setOnItemLongClickListener(new OnItemLongClickListener() 
		{
	            // setting onItemLongClickListener and passing the position to the function
	                      @Override
	            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int position, long arg3) 
	            {
	                removeItemFromList(position);   
	                
	                return true;
	            }
	        });	
		
		db.close();
	}
	
	 protected void removeItemFromList(int position) {
	        final int deletePosition = position;
	        
	        AlertDialog.Builder alert = new AlertDialog.Builder(SubItemList.this);
	    
	        alert.setTitle("Delete");
	        alert.setMessage("Do you want to delete this item?");
	        alert.setPositiveButton("YES", new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                // TOD O Auto-generated method stub
	                    
	                    // main code on after clicking yes
	            	/*
	                    arr.remove(deletePosition);
	                    adapter.notifyDataSetChanged();
	                    adapter.notifyDataSetInvalidated();
	                    */
	      
	            }
	        });
	        alert.setNegativeButton("CANCEL", new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                // TODO Auto-generated method stub
	                dialog.dismiss();
	            }
	        });
	      
	        alert.show();
	      
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sub_item_list, menu);
		return true;
	}

}
