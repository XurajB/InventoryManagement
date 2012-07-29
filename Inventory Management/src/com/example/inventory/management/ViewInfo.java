package com.example.inventory.management;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewInfo extends Activity{

	TextView tvInfoName;
	String receivedData;
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
		tvInfoName = (TextView) findViewById(R.id.tvInfoName);
	}
	
	

}
