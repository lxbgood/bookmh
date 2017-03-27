package com.book.dfapp.activity;

import com.example.com.book.dfapp.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends MyActivity {
	private FragmentTransaction fragmentTransaction;
	private TextView textView,textViewbook,textViewmh;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findview();
	} 
	public void findview()
	{
		textView=(TextView)findViewById(R.id.bookdm);
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setframage(1);
			}
		});
		textViewbook=(TextView)findViewById(R.id.book);
		textViewbook.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setframage(0);
			}
		});
		textViewmh=(TextView)findViewById(R.id.dm);
		textViewmh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setframage(2);
			}
		});
		setframage(1);
	}
	public void setframage(int i)
	{
		if(i==1)
		{
			IndexFramage indexFramage =new IndexFramage();
			fragmentTransaction = getSupportFragmentManager().beginTransaction();
			fragmentTransaction.replace(R.id.fragment, indexFramage); 
			fragmentTransaction.commitAllowingStateLoss();
			textView.setTextColor(getResources().getColor(R.color.font_B_highlight_color_red));
			textViewbook.setTextColor(getResources().getColor(R.color.font_A_assistant_color_black));
			textViewmh.setTextColor(getResources().getColor(R.color.font_A_assistant_color_black));
		}
		if(i==0)
		{
			bookFramage bookFramage =new bookFramage();
			fragmentTransaction = getSupportFragmentManager().beginTransaction();
			fragmentTransaction.replace(R.id.fragment, bookFramage); 
			fragmentTransaction.commitAllowingStateLoss();
			textView.setTextColor(getResources().getColor(R.color.font_A_assistant_color_black));
			textViewbook.setTextColor(getResources().getColor(R.color.font_B_highlight_color_red));
			textViewmh.setTextColor(getResources().getColor(R.color.font_A_assistant_color_black));
		}
		if(i==2)
		{
			bookFramage bookFramage =new bookFramage();
			fragmentTransaction = getSupportFragmentManager().beginTransaction();
			fragmentTransaction.replace(R.id.fragment, bookFramage); 
			fragmentTransaction.commitAllowingStateLoss();
			textView.setTextColor(getResources().getColor(R.color.font_A_assistant_color_black));
			textViewbook.setTextColor(getResources().getColor(R.color.font_A_assistant_color_black));
			textViewmh.setTextColor(getResources().getColor(R.color.font_B_highlight_color_red));
		}
	}
}
