/*
MEENAKSHY HARIKUMAR 
SCU ID :1001341
COEN 268- Project
*/
package com.example.coen268_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import android.widget.Toast;


public class ItemsDB extends SQLiteOpenHelper {
	
	
	public static final String ID_COLUMN = "_id";
	public static final String ITEM_NAME = "ITEM_NAME";
	public static final String SUBITEM_NAME = "SUBITEM_NAME";
	
	public static final String COST="COST";
	public static final String CONTACT_NAME="CONTACT_NAME";
	public static final String CONTACT_NUMBER="CONTACT_NUMBER";
	public static final String CONTACT_ADD="CONTACT_ADD";
	public static final String IS_REMINDER="IS_REMINDER";
	public static final String REMINDER_DATE="REMINDER_DATE";
	//public static final String FILE_PATH_COLUMN = "FILE_PATH_COLUMN";
	public static final String DATABASE_TABLE = "SubItemTable";
	public static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = String.format(
	"CREATE TABLE %s (" +
	" %s integer primary key autoincrement, " +
	" %s text," +
	" %s text,"+
	" %s text,"+
	" %s text,"+
	" %s text,"+
	" %s text,"+
	" %s text,"+
	" %s text)",
	DATABASE_TABLE, ID_COLUMN, ITEM_NAME, SUBITEM_NAME,COST,CONTACT_NAME,CONTACT_NUMBER,CONTACT_ADD,IS_REMINDER,REMINDER_DATE);
	
	@Override
	public void onCreate(SQLiteDatabase db) {
	db.execSQL(DATABASE_CREATE);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
	onCreate(db);
	}
	
	public ItemsDB(Context context) {
		super(context, DATABASE_TABLE, null, DATABASE_VERSION);
		}
	
	public Cursor getSubItems(String item, String field_name) {

		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = {"0 _id", "ITEM_NAME", "SUBITEM_NAME", "CONTACT_NAME", "CONTACT_NUMBER", "CONTACT_ADD", "COST"}; 
		String sqlTables = "SubItemTable";
        
		String where = " ITEM_NAME= ? AND " + field_name + " IS NOT NULL";
		String whereArgs[] = {item};
		
		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, where, whereArgs, null, null, null);

		c.moveToFirst();
		return c;

	}

	public void updateField(String subitem_name,String field_name,String field_value)
	{
		SQLiteDatabase sdb = getWritableDatabase();
		 ContentValues cv = new ContentValues();
		// if(field_name.equals("COST"))
		// {
		    cv.put(field_name, field_value);

		                 /* use COLUMN NAMES here */                     
		    String where = "SUBITEM_NAME = ?";
		                 /* bind VALUES here */
		    String[] whereArgs = { subitem_name };
		    sdb.update(DATABASE_TABLE, cv, where, whereArgs);
		// }
	}
	
	public String getFieldValue(String subitem_name,String field_name)
	{
			
	//	Log.d ("MY_DEBUG2", "Reached ");
	SQLiteDatabase db = getReadableDatabase();
	SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

	//String [] sqlSelect = {"0 _id", "ITEM_NAME", "SUBITEM_NAME", "COST"}; 
	String [] sqlSelect = {"0 _id", field_name}; 
	
	String sqlTables = "SubItemTable";
    
	String where = " SUBITEM_NAME= ?";
	String whereArgs[] = {subitem_name};
	
	qb.setTables(sqlTables);
	Cursor c = qb.query(db, sqlSelect, where, whereArgs,
			null, null, null);

	c.moveToFirst();
	Log.d ("MY_DEBUG2", "cost = " + c.getString(1));
	return c.getString(1);
	
	}
	

	/*
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.items_db, menu);
		return true;
	}
	*/

}

