/*
MEENAKSHY HARIKUMAR 
SCU ID :1001341
COEN 268- Project
*/
package com.example.coen268_project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyAssetDb extends SQLiteAssetHelper {
	
	 private static final String DATABASE_NAME = "plannersubitems.db";
	    private static final int DATABASE_VERSION = 1;

	    public MyAssetDb(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }
	    
	    public Cursor getSubItems(String state, String community) {

			SQLiteDatabase db = getReadableDatabase();
			SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

			String [] sqlSelect = {"0 _id", "Item", "State", "Community", "SubItem"}; 
			String sqlTables = "subitemtable";
            
			String where = "State = ? AND Community = ?";
			String whereArgs[] = {state,community};
			
			qb.setTables(sqlTables);
			Cursor c = qb.query(db, sqlSelect, where, whereArgs,
					null, null, null);

			c.moveToFirst();
			return c;

		}

}
