package com.example.inventory.management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Menu extends Activity implements OnClickListener {
	Button bEnter, bFind, bView, bOptions, bExit;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		initializeVars();
	}

	private void initializeVars() {
		// TODO Auto-generated method stub
		bEnter = (Button) findViewById(R.id.bEnterItem);
		bFind = (Button) findViewById(R.id.bFind);
		bView = (Button) findViewById(R.id.bView);
		bOptions = (Button) findViewById(R.id.bOptions);
		bExit = (Button) findViewById(R.id.bExit);

		bEnter.setOnClickListener(this);
		bFind.setOnClickListener(this);
		bView.setOnClickListener(this);
		bOptions.setOnClickListener(this);
		bExit.setOnClickListener(this);

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bEnterItem:
			Intent a = new Intent(Menu.this,MainActivity.class);
			startActivity(a);
			break;
		case R.id.bFind:
			Intent b = new Intent(Menu.this,Find.class);
			startActivity(b);
			break;
		case R.id.bView:
			Intent c = new Intent(Menu.this,MainActivity.class);
			startActivity(c);
			break;
		case R.id.bOptions:
			Intent d = new Intent(Menu.this,MainActivity.class);
			startActivity(d);
			break;
		case R.id.bExit:
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			break;
		}
	}

}
