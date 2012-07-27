package com.example.inventory.management;

import com.example.inventory.db.DatabaseHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements OnClickListener,
		OnGestureListener {
	TextView tvName, tvCode, tvPrice, tvQuantity, tvA;
	EditText etName, etCode, etPrice, etQuantity, etA;
	private ViewFlipper vf;
	Button addItem, scan;
	private float lastX;

	private GestureDetector myGesture;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myGesture = new GestureDetector(this);
		initializeVars();

		
	}

	private void initializeVars() {
		// TODO Auto-generated method stub
		vf = (ViewFlipper) findViewById(R.id.view_flipper);

		tvName = (TextView) findViewById(R.id.tvName);
		tvCode = (TextView) findViewById(R.id.tvCode);
		tvPrice = (TextView) findViewById(R.id.tvPrice);
		tvQuantity = (TextView) findViewById(R.id.tvQuantity);
		tvA = (TextView) findViewById(R.id.tvA);

		etName = (EditText) findViewById(R.id.etName);
		etCode = (EditText) findViewById(R.id.etCode);
		etPrice = (EditText) findViewById(R.id.etPrice);
		etQuantity = (EditText) findViewById(R.id.etQuantity);
		etA = (EditText) findViewById(R.id.etA);

		addItem = (Button) findViewById(R.id.bAdd);
		addItem.setOnClickListener(this);
		
		scan = (Button) findViewById(R.id.bScan);
		addItem.setOnClickListener(this);


	}

	public boolean onTouchEvent(MotionEvent touchevent) {

		switch (touchevent.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			lastX = touchevent.getX();
			break;
		}
		case MotionEvent.ACTION_UP: {
			float currentX = touchevent.getX();
			if (lastX < currentX) {
				if (vf.getDisplayedChild() == 0)
					break;
				vf.setInAnimation(this, R.anim.in_from_left);
				vf.setOutAnimation(this, R.anim.out_to_right);
				vf.showNext();
			}
			if (lastX > currentX) {
				if (vf.getDisplayedChild() == 1)
					break;
				vf.setInAnimation(this, R.anim.in_from_right);
				vf.setOutAnimation(this, R.anim.out_to_left);
				vf.showPrevious();
			}
			break;
		}
		}
		return false;
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		try {
			if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
				return false;
			// right to left swipe
			if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				Toast.makeText(getApplicationContext(), "Left Swipe",
						Toast.LENGTH_SHORT).show();
			} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				Toast.makeText(getApplicationContext(), "Right Swipe",
						Toast.LENGTH_SHORT).show();
			} else if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				Toast.makeText(getApplicationContext(), "Swipe up",
						Toast.LENGTH_SHORT).show();
			} else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				Toast.makeText(getApplicationContext(), "Swipe down",
						Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			// nothing
		}

		return true;
	}

	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId()){
		case R.id.bAdd:
			addItem();
			String name = etName.getText().toString();
			Bundle holder = new Bundle();
			holder.putString("key", name);
			Intent a = new Intent(MainActivity.this,ViewInfo.class );
			a.putExtras(holder);
			startActivity(a);
			break;
			
		case R.id.bScan:
			
			break;
		}

	}
	private void addItem(){
		DatabaseHelper db = new DatabaseHelper(this);
		String productName = etName.getText().toString().trim(); //add if else to check product name
		String productCode= etCode.getText().toString().trim(); //add if else to check product code
		Double price = Double.parseDouble(etPrice.getText().toString().trim()) ; 
		Integer quantity = Integer.parseInt(etQuantity.getText().toString().trim());
		db.insertItem(productName, productCode, price, quantity);
		db.close();
	}
}
