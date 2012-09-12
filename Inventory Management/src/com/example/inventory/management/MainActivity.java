package com.example.inventory.management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.inventory.db.DatabaseHelper;

public class MainActivity extends Activity implements OnGestureListener {
	TextView tvName, tvCode, tvPrice, tvQuantity, tvA;
	EditText etName, etCode, etPrice, etQuantity, etA;
	private ViewFlipper vf;
	Button summary, scan;
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

		summary = (Button) findViewById(R.id.bSummary);
		summary.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				summary();
			}
		});

		scan = (Button) findViewById(R.id.bScan);
		scan.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				startScan();
			}
		});

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

	

	private void summary() {
		// TODO Auto-generated method stub
		String productName = etName.getText().toString().trim(); // add if else
		// to check
		// product
		// name
		//String productCode = etCode.getText().toString().trim(); // add if else
		// to check
		// product
		// code
		//Double price = Double.parseDouble(etPrice.getText().toString().trim());
		//Integer quantity = Integer.parseInt(etQuantity.getText().toString()
				//.trim());
		
		Bundle holder = new Bundle();
		holder.putString("key", productName);
		Intent a = new Intent(MainActivity.this, ViewInfo.class);
		a.putExtras(holder);
		startActivity(a);
	}

	public void startScan() {
		// TODO Auto-generated method stub

		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE",
				"QR_CODE_MODE");
		startActivityForResult(intent, 0);

	}

	

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");
				// String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				populateScan(contents);
				// Handle successful scan
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
			}
		}
	}

	private void populateScan(String contents) {
		// TODO Auto-generated method stub
		etCode.setText(contents);
	}
}
