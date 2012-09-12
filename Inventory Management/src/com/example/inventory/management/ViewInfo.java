package com.example.inventory.management;

import com.example.inventory.db.DatabaseHelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewInfo extends Activity{

	TextView tvInfoName;
	String receivedData;
	Button addItem;
	EditText etName, etCode,etPrice,etQuantity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewinfo);
		initializeVars();
		Bundle receiver = getIntent().getExtras();
		receivedData = receiver.getString("key");
		tvInfoName.setText(receivedData);
		
		
	}
	
	

	private void initializeVars() {
		// TODO Auto-generated method stub
		
		etName = (EditText)findViewById(R.id.etName);
		etCode = (EditText)findViewById(R.id.etCode);
		etPrice = (EditText)findViewById(R.id.etPrice);
		etQuantity = (EditText)findViewById(R.id.etQuantity);
		tvInfoName = (TextView) findViewById(R.id.tvInfoName);
		addItem = (Button)findViewById(R.id.bAddItem);
		
		addItem.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				addItem();
			}
		});
		
	}
	
	private void addItem() {
		DatabaseHelper db = new DatabaseHelper(this);

		String productName = etName.getText().toString().trim(); // add if else
																	// to check
																	// product
																	// name
		String productCode = etCode.getText().toString().trim(); // add if else
																	// to check
																	// product
																	// code
		Double price = Double.parseDouble(etPrice.getText().toString().trim());
		Integer quantity = Integer.parseInt(etQuantity.getText().toString()
				.trim());
		db.insertItem(productName, productCode, price, quantity);
		db.close();
	}

}
