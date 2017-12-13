package com.book.dfapp.activity;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.book.dfapp.util.BookMode;
import com.book.dfapp.util.HttpListener;
import com.book.dfapp.util.HttpUtil;
import com.book.dfapp.util.IndexMode;
import com.example.com.book.dfapp.R;
import com.markmao.pulltorefresh.widget.XListView;

public class bookFramage extends Fragment {
	public View view;
	public XListView listview;
	ListAdapter listAdapter=null; 
	public ArrayList<BookMode> arraylist=new ArrayList<BookMode>(); 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		arraylist.add("1111111111111111111111111");
//		arraylist.add("2222222222222222222222222");
//		arraylist.add("333333333333333333333333333");
//		arraylist.add("44444444444444444444444444444444");
//		arraylist.add("55555555555555555555555555555");
//		arraylist.add("666666666666666666666666666666666666");
//		arraylist.add("111111111111111111777777777777777777777771111111");
//		arraylist.add("888888888888888888888888888888");
//		arraylist.add("9999999999999999999999999999999999");
		 
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		  view = inflater.inflate(R.layout.bookframage, null); 
		  listview=(XListView)view.findViewById(R.id.listview); 
		  listview.setPullRefreshEnable(false);
		  listview.setPullLoadEnable(false);
		  listview.setAutoLoadEnable(false);
		findViews(view);  
		sendhttp();
		
		
		return view;
	}
	public void sendhttp()
	{
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("fun", "booklist");
		HttpUtil httpUtil=new HttpUtil((MyActivity)getActivity(),map , new HttpListener() {
			
			@Override
			public void OnSucess(String json) {
				// TODO Auto-generated method stub
				Log.i("json", json);
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(json);
					IndexMode indexmode=new IndexMode(jsonObject);
					arraylist=indexmode.getBooklist();
					 
				    MyActivity myActivity=(MyActivity)getActivity();
				    myActivity.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
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
	public void  findViews(View view)
	{
		listAdapter=new ListAdapter(arraylist, getActivity().getApplicationContext()); 
		listview.setAdapter(listAdapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
                        TxtActivity.class); 
				intent.putExtra("id", arraylist.get(position-1).getId());
				intent.putExtra("name", arraylist.get(position-1).getName());
                startActivity(intent);
			}
		});
	}

}
