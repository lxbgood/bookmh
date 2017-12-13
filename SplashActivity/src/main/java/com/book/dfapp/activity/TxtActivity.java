package com.book.dfapp.activity;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.book.dfapp.util.HttpListener;
import com.book.dfapp.util.HttpUtil;
import com.example.com.book.dfapp.R;
import com.markmao.pulltorefresh.widget.XListView;
import com.markmao.pulltorefresh.widget.XListView.IXListViewListener;

public class TxtActivity extends MyActivity implements IXListViewListener{
	public XListView listview;
	TextAdapter listAdapter=null; 
	int page=0;
	ImageButton ib_title_model_back1;
	TextView tv_title_model_text;
	String id="";
	String name="";
	public ArrayList<String> arraylist=new ArrayList<String>(); 
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.txtframage);
		
		tv_title_model_text=(TextView)findViewById(R.id.tv_title_model_text);
		 listview=(XListView)findViewById(R.id.txtlistview); 
		 id=getIntent().getStringExtra("id");
		 name=getIntent().getStringExtra("name");
		 tv_title_model_text.setText(name);
		  listview.setPullRefreshEnable(false);
		  listview.setPullLoadEnable(true);
		  listview.setAutoLoadEnable(true);
		  listview.setXListViewListener(this);
		  findViews();
		  sendhttp(page);
		  ib_title_model_back1=(ImageButton)findViewById(R.id.ib_title_model_back1);
		  ib_title_model_back1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	public void sendhttp(int page)
	{
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("fun", "getBookTxt");
		map.put("id", id);
		map.put("p", page+"");
		HttpUtil httpUtil=new HttpUtil(this,map , new HttpListener() {
			
			@Override
			public void OnSucess(String json) {
				// TODO Auto-generated method stub
				Log.i("json", json);
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(json);
					 String book=jsonObject.optString("mhbook");
					 arraylist.add(book);
					 post(new  Runnable() {
						public void run() {
							listAdapter.setData(arraylist);
							 listAdapter.notifyDataSetChanged();
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
	public void  findViews()
	{
		listview.setVisibility(View.VISIBLE);
		listAdapter=new TextAdapter(arraylist, this); 
		listview.setAdapter(listAdapter);
	}
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		page++;
		sendhttp(page);
	} 

	 
}
