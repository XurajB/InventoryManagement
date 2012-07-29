package com.example.inventory.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	static private final String DATABASE_NAME = "inventory.db";
	static private final int DATABASE_VERSION = 1;
	static private final String TB_INVENTORY_VIEW = "inventory_view";
	static private final String COL_INVENTORY_ID = "_id";
	static private final String COL_INVENTORY_PRICE = "price";
	static private final String COL_INVENTORY_QUANTITY = "quantity";
	static private final String COL_INVENTORY_PRODUCT_NAME = "product_name";
	static private final String COL_INVENTORY_PRODUCT_CODE = "product_code";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE " + TB_INVENTORY_VIEW + " ("
                + COL_INVENTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_INVENTORY_PRODUCT_NAME + " TEXT NOT NULL,"
                + COL_INVENTORY_PRODUCT_CODE + " TEXT NOT NULL,"
                + COL_INVENTORY_PRICE + " REAL,"
                + COL_INVENTORY_QUANTITY + " INTEGER"
                + ");");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//DROP TABLE AND UPDATE TABLE 
	}

	public long insertItem(String productName, String productCode){
		ContentValues cv = new ContentValues();
		cv.put(COL_INVENTORY_PRODUCT_NAME, productName);
		cv.put(COL_INVENTORY_PRODUCT_CODE, productCode);
	
		return getWritableDatabase().insertOrThrow(TB_INVENTORY_VIEW,null, cv);
		
	}
	public long insertItem(String productName, String productCode, Double price,Integer quantity ){
		ContentValues cv = new ContentValues();
		cv.put(COL_INVENTORY_PRODUCT_NAME, productName);
		cv.put(COL_INVENTORY_PRODUCT_CODE, productCode);
		cv.put(COL_INVENTORY_PRICE, price.doubleValue());
		cv.put(COL_INVENTORY_QUANTITY, quantity.intValue());
	
		return getWritableDatabase().insertOrThrow(TB_INVENTORY_VIEW,null, cv);
		
	}

	
}
