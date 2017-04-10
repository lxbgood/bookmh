package com.book.dfapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.example.com.book.dfapp.R;

public class HelpActivity extends MyActivity{
	View help,shengming,about;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.my);
		help=findViewById(R.id.help);
		shengming=findViewById(R.id.shengming);
		about=findViewById(R.id.about);
		help.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HelpActivity.this,
                        Help1Activity.class);  
                startActivity(intent);
			}
		});
		shengming.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HelpActivity.this,
                        ShenActivity.class);  
                startActivity(intent);
			}
		});
		about.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HelpActivity.this,
                        AboutActivity.class);  
                startActivity(intent);
			}
		});
		 ImageButton ib_title_model_back1=(ImageButton)findViewById(R.id.ib_title_model_back1);
		  ib_title_model_back1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
