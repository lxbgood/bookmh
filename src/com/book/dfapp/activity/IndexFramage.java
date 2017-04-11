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

import com.book.dfapp.util.BookMhMode;
import com.book.dfapp.util.BookMode;
import com.book.dfapp.util.HttpListener;
import com.book.dfapp.util.HttpUtil;
import com.book.dfapp.util.IndexMode;
import com.example.com.book.dfapp.R;
import com.markmao.pulltorefresh.widget.MyGridView;

public class IndexFramage extends Fragment {
	public View view;
	public MyGridView gridview,mangrid;
	public BookAdapter bookAdapter=null;
	public BookmhAdapter manAdapter=null;
	public ArrayList<BookMode> arraylist=new ArrayList<BookMode>();
	public ArrayList<BookMhMode> manarraylist=new ArrayList<BookMhMode>();
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
//		manarraylist.add("ttttttttt1111111111111111111111111");
//		manarraylist.add("ttttt2222222222222222222222222");
//		manarraylist.add("tttt333333333333333333333333333");
//		manarraylist.add("tt44444444444444444444444444444444");
//		manarraylist.add("ttt55555555555555555555555555555");
//		manarraylist.add("tt666666666666666666666666666666666666");
//		manarraylist.add("ttt111111111111111111777777777777777777777771111111");
//		manarraylist.add("ttt888888888888888888888888888888");
//		manarraylist.add("ttttt9999999999999999999999999999999999");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		  view = inflater.inflate(R.layout.indexframage, null); 
		  gridview=(MyGridView)view.findViewById(R.id.manhuagridView1);
		  mangrid=(MyGridView)view.findViewById(R.id.gridView1);
		findViews(view);  
		 inihttp();
		
		
		return view;
	}
	public void  findViews(View view)
	{
		bookAdapter=new BookAdapter(arraylist, getActivity());
		gridview.setAdapter(bookAdapter);
		manAdapter=new BookmhAdapter(manarraylist, getActivity());
		mangrid.setAdapter(manAdapter);
		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
                        TxtActivity.class); 
				intent.putExtra("id", arraylist.get(position).getId());
				intent.putExtra("name", arraylist.get(position).getName());
                startActivity(intent);
			}
		});
		mangrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
                        MhProductActivity.class); 
				intent.putExtra("id", manarraylist.get(position).getId());
                startActivity(intent);
			}
		});
	}
	public void inihttp()
	{
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("fun", "index");
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
					manarraylist=indexmode.getMhlist();
				    MyActivity myActivity=(MyActivity)getActivity();
				    myActivity.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							bookAdapter.setData(arraylist);
							bookAdapter.notifyDataSetChanged();
							manAdapter.setData(manarraylist);
							manAdapter.notifyDataSetChanged();
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

}
