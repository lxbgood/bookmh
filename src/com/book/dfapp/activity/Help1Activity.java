package com.book.dfapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.example.com.book.dfapp.R;

public class Help1Activity extends MyActivity{
	View help,shengming,about;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.help);
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
