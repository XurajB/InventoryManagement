package com.example.inventory.management;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	TextView tvName, tvCode, tvPrice, tvQuantity, tvA;
	EditText etName, etCode, etPrice, etQuantity, etA;
	private ViewFlipper vf;
	private float lastX;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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

	}

	@Override
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

}
