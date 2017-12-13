package com.book.dfapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.book.dfapp.R;

public class MainActivity extends MyActivity {
	private FragmentTransaction fragmentTransaction;
	private TextView textView,textViewbook,textViewmh;
	private ImageView img;
	private View my_share_line_1,my_share_line_2,my_share_line_3;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findview();
	} 
	public void findview()
	{
		img=(ImageView)findViewById(R.id.img);
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
                        HelpActivity.class);  
                startActivity(intent);
			}
		});
		my_share_line_1=findViewById(R.id.my_share_line_1);
		my_share_line_2=findViewById(R.id.my_share_line_2);
		my_share_line_3=findViewById(R.id.my_share_line_3);
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
			textView.setTextColor(getResources().getColor(R.color.bluebg));
			textViewbook.setTextColor(getResources().getColor(R.color.font_text_color_txt));
			textViewmh.setTextColor(getResources().getColor(R.color.font_text_color_txt));
			my_share_line_1.setVisibility(View.GONE);
			my_share_line_2.setVisibility(View.VISIBLE);
			my_share_line_3.setVisibility(View.GONE);
		}
		if(i==0)
		{
			bookFramage bookFramage =new bookFramage();
			fragmentTransaction = getSupportFragmentManager().beginTransaction();
			fragmentTransaction.replace(R.id.fragment, bookFramage); 
			fragmentTransaction.commitAllowingStateLoss();
			textView.setTextColor(getResources().getColor(R.color.font_text_color_txt));
			textViewbook.setTextColor(getResources().getColor(R.color.bluebg));
			textViewmh.setTextColor(getResources().getColor(R.color.font_text_color_txt));
			my_share_line_1.setVisibility(View.VISIBLE);
			my_share_line_2.setVisibility(View.GONE);
			my_share_line_3.setVisibility(View.GONE);
		}
		if(i==2)
		{
			MhFramage bookFramage =new MhFramage();
			fragmentTransaction = getSupportFragmentManager().beginTransaction();
			fragmentTransaction.replace(R.id.fragment, bookFramage); 
			fragmentTransaction.commitAllowingStateLoss();
			textView.setTextColor(getResources().getColor(R.color.font_text_color_txt));
			textViewbook.setTextColor(getResources().getColor(R.color.font_text_color_txt));
			textViewmh.setTextColor(getResources().getColor(R.color.bluebg));
			my_share_line_1.setVisibility(View.GONE);
			my_share_line_2.setVisibility(View.GONE);
			my_share_line_3.setVisibility(View.VISIBLE);
		}
	}
}
