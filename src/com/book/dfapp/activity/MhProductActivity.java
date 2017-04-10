package com.book.dfapp.activity;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.book.dfapp.util.BookMhMode;
import com.book.dfapp.util.HttpListener;
import com.book.dfapp.util.HttpUtil;
import com.book.dfapp.util.ImageUtil;
import com.example.com.book.dfapp.R;

public class MhProductActivity  extends MyActivity{ 
	ImageView image=null;
	TextView title=null;
	TextView content=null;
	TextView button_start=null;
	String id="";
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.mhactiviity);
		image=(ImageView)findViewById(R.id.img);
		title=(TextView)findViewById(R.id.booktitle);
		content=(TextView)findViewById(R.id.centext);
		button_start=(TextView)findViewById(R.id.button_start);
		 id=getIntent().getStringExtra("id");
		sendhttp();
		button_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MhProductActivity.this,
                        MhActivity.class); 
				intent.putExtra("id", id);
                startActivity(intent);
			}
		});
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	public void sendhttp()
	{
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("fun", "getBookMhById");
		map.put("id", id);
		HttpUtil httpUtil=new HttpUtil(this,map , new HttpListener() {
			
			@Override
			public void OnSucess(String json) {
				// TODO Auto-generated method stub
				Log.i("json", json);
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(json);
					JSONObject mhjson=jsonObject.optJSONObject("mhbook");
					final BookMhMode bookMhMode=new BookMhMode(mhjson);
				     post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub 
							update(bookMhMode);
						}
					});
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void OnFile() {
				// TODO Auto-generated method stub
				
			}
		});
		httpUtil.sendHttp();
	}
	public void update(BookMhMode bookMhMode)
	{
		ImageUtil.setViewImage(this, image, bookMhMode.getCoverpic(), false);
		title.setText(bookMhMode.getName());
		content.setText(bookMhMode.getJianjie());
	}
}
