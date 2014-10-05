package com.example.coen268_project;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetSubItemCost extends Activity {
	
	EditText text;
	Button saveButton;
	String subitem_name;
	private ItemsDB db,db_for_cost;
	private Cursor cost_Cursor;
	String cost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_sub_item_cost);
		
		subitem_name=getIntent().getExtras().getString("subitem_name");
		db_for_cost = new ItemsDB(this);
		cost=db_for_cost.getFieldValue(subitem_name,"COST");
		//Toast.makeText(getApplicationContext(), cost, Toast.LENGTH_SHORT).show();
		text = (EditText) findViewById(R.id.editText1);
		
		/*
		db_for_cost = new ItemsDB(this);
		cost_Cursor = db_for_cost.getSubItems(subitem_name);
		*/		
		
		text.setText(cost);
		
		saveButton = (Button) findViewById(R.id.button1);
		
		db_for_cost.close();
		
	}

	public void onSave(View v)
	{
		
		//Toast.makeText(this,text.getText().toString(), Toast.LENGTH_SHORT).show();
		db = new ItemsDB(this);
		//subitems = db.getSubItems(subitem_name); 
        db.updateField(subitem_name,"COST",text.getText().toString());
        Toast.makeText(this," Cost Saved! ", Toast.LENGTH_SHORT).show();
        //db.getFieldValue(subitem_name,"COST");
		db.close();
		finish();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_sub_item_cost, menu);
		return true;
	}

}
