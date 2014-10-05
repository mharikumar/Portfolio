package com.example.coen268_project;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ContactDetails extends Activity {
	
	String contact_name;
	String contact_number;
	String contact_add;
	TextView name_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_details);
		
		// Get the message from the intent
	    Intent intent = getIntent();
	    
	    //Create bundle with name,image and description.
        Bundle extras=intent.getExtras();
        contact_name=extras.getString("EXTRA_NAME");
        contact_number=extras.getString("EXTRA_NUMBER");
        contact_add=extras.getString("EXTRA_ADD");
        
        name_text = (TextView) findViewById(R.id.textView1);
        name_text.setText(contact_name);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_details, menu);
		return true;
	}
	
	//Function to dial 555-5555 	
		public void call_contact(View v)
		{
			//String number = "tel:555-5555";
	        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+contact_number)); 
	        startActivity(callIntent);
		}

	// Function to launch Google Map
		public void map_contact(View v)
		{
			//String contactAddress="San Jose";			
			Intent intent = new Intent(
					android.content.Intent.ACTION_VIEW,
					Uri.parse(
					"http://www.google.com/maps/place/"
					+ contact_add));
			startActivity(intent);
		}
}
