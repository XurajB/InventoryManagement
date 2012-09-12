package com.example.inventory.management;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Find extends Activity {
EditText etFind;
Button bFind;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find);
		initializeVars();
		
	}

	private void initializeVars() {
		// TODO Auto-generated method stub
		etFind= (EditText)findViewById(R.id.etFind);
		bFind= (Button)findViewById(R.id.bFind);
		
	}
}
