package com.example.inventory.management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartPage extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startpage);
		
		
		Thread time = new Thread(){
			public void run(){
				try{
					sleep(5000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openMenu = new Intent("com.example.inventory.management.MENU");
					startActivity(openMenu);
				}
			}
			
		};
		time.start();
		
		
	}

}
