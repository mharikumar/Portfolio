package com.example.coen268_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetSubItemContact extends Activity {
	
	private ItemsDB db,db_for_contact;
	String subitem_name,contact_name,contact_phone,contact_addr;
	EditText name_text,number_text,address_text;
	Button addButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_sub_item_contact);
		
		subitem_name=getIntent().getExtras().getString("subitem_name");
		
		db_for_contact = new ItemsDB(this);
		contact_name=db_for_contact.getFieldValue(subitem_name,"CONTACT_NAME");
		contact_phone=db_for_contact.getFieldValue(subitem_name,"CONTACT_NUMBER");
		contact_addr=db_for_contact.getFieldValue(subitem_name,"CONTACT_ADD");
		
		name_text = (EditText) findViewById(R.id.editText1);
		number_text= (EditText) findViewById(R.id.editText3);
		address_text= (EditText) findViewById(R.id.editText2);
		
        name_text.setText(contact_name);
        number_text.setText(contact_phone);
        address_text.setText(contact_addr);
        
		
		addButton = (Button) findViewById(R.id.button1);
		
		db_for_contact.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_sub_item_contact, menu);
		return true;
	}
	
	public void addContact(View view)
	{
		db = new ItemsDB(this);
		db.updateField(subitem_name,"CONTACT_NAME",name_text.getText().toString());
		db.updateField(subitem_name,"CONTACT_NUMBER",number_text.getText().toString());
		db.updateField(subitem_name,"CONTACT_ADD",address_text.getText().toString());
		
        Toast.makeText(this," Contact Saved! ", Toast.LENGTH_SHORT).show();
		db.close();
		finish();
	}

}
