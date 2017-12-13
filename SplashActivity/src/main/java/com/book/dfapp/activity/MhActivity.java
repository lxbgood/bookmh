package com.book.dfapp.activity;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.R.string;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.book.dfapp.util.HttpListener;
import com.book.dfapp.util.HttpUtil;
import com.book.dfapp.util.ImageUtil;
import com.example.com.book.dfapp.R;
import com.markmao.pulltorefresh.widget.XListView;
import com.markmao.pulltorefresh.widget.XListView.IXListViewListener;

public class MhActivity extends MyActivity implements IXListViewListener{
	public XListView listview;
//	ImageAdapter listAdapter=null; 
	int page=0;
	ImageButton ib_title_model_back1;
	TextView tv_title_model_text;
	int num=0;
	String filedir="";
	int ennum=0;
	ImageView image;
	public final int RIGHT=1;
	public final int LEFT=2;
	private GestureDetector gestureDetector;
	public ArrayList<String> arraylist=new ArrayList<String>(); 
	private String id="";
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.txtframage);
		ib_title_model_back1=(ImageButton)findViewById(R.id.ib_title_model_back1);
		tv_title_model_text=(TextView)findViewById(R.id.tv_title_model_text);
		 listview=(XListView)findViewById(R.id.txtlistview); 
		 image=(ImageView)findViewById(R.id.image); 
		 image.setVisibility(View.VISIBLE);
		  listview.setPullRefreshEnable(false);
		  listview.setPullLoadEnable(true);
		  listview.setAutoLoadEnable(true);
		  listview.setXListViewListener(this);
		  id=getIntent().getStringExtra("id");
		  gestureDetector = new GestureDetector( this,onGestureListener);
		  findViews();
		  sendhttp(page);
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
					JSONObject mh=jsonObject.optJSONObject("mhbook");
					if(mh!=null)
					{
						final String name=mh.optString("name");
						filedir=mh.optString("filedir"); 
						ennum=mh.optInt("endnum");
						 for (int i = 1; i < ennum+1; i++) {
							 intto(i);
							 arraylist.add(filedir+intto(i));
						}
						post(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								if(arraylist.size()>0)
								ImageUtil.setViewImage(MhActivity.this, image, arraylist.get(0), true);
								tv_title_model_text.setText(name);
							}
						});
//						 listAdapter.setData(arraylist);
//						 listAdapter.notifyDataSetChanged();	
					}
					
					 
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
	public String intto(int  a)
	{
		String n=String.valueOf(a);
		if(n.length()==1)
		{
			n="00"+n+".jpg";
		}
		if(n.length()==2)
		{
			n="0"+n+".jpg";
		}
		if(n.length()==1)
		{
			n=""+n+".jpg";
		}
		return n;
	}
	public void  findViews()
	{
//		listAdapter=new ImageAdapter(arraylist, this); 
//		listview.setAdapter(listAdapter);
	}
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
//		if (num>=ennum) {
//			return ;
//		}
//		num++;
//		 arraylist.add(filedir+intto());
////		 listAdapter.setData(arraylist);
//		 listAdapter.notifyDataSetInvalidated();
	} 
	private GestureDetector.OnGestureListener onGestureListener = 
			new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
					float velocityY) {
				float x = e2.getX() - e1.getX();
				float y = e2.getY() - e1.getY();

				if (x > 0) {
					doResult(RIGHT);
				} else if (x < 0) {
					doResult(LEFT);
				}
				return true;
			}
		};

		public boolean onTouchEvent(MotionEvent event) {
			return gestureDetector.onTouchEvent(event);
		}

		public void doResult(int action) {

			switch (action) {
			case RIGHT:
				num--;
				if(num>=0)
				{
					ImageUtil.setViewImage(MhActivity.this, image, arraylist.get(num), true);
					
				}else {
					num=0;
				}
				 
				Log.i("11111111111","go right");
				break;

			case LEFT:
				num++;
				if(num>=0&&num<arraylist.size())
				{
					ImageUtil.setViewImage(MhActivity.this, image, arraylist.get(num), true);
				}
				 
				Log.i("11111111111","go left");
				break;

			}
		}
	 
}
