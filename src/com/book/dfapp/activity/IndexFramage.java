package com.book.dfapp.activity;

import java.util.ArrayList;

import com.example.com.book.dfapp.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class IndexFramage extends Fragment {
	public View view;
	public GridView gridview,mangrid;
	BookAdapter bookAdapter=null;
	BookAdapter manAdapter=null;
	public ArrayList<String> arraylist=new ArrayList<String>();
	public ArrayList<String> manarraylist=new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		arraylist.add("1111111111111111111111111");
		arraylist.add("2222222222222222222222222");
		arraylist.add("333333333333333333333333333");
		arraylist.add("44444444444444444444444444444444");
		arraylist.add("55555555555555555555555555555");
		arraylist.add("666666666666666666666666666666666666");
		arraylist.add("111111111111111111777777777777777777777771111111");
		arraylist.add("888888888888888888888888888888");
		arraylist.add("9999999999999999999999999999999999");
		manarraylist.add("ttttttttt1111111111111111111111111");
		manarraylist.add("ttttt2222222222222222222222222");
		manarraylist.add("tttt333333333333333333333333333");
		manarraylist.add("tt44444444444444444444444444444444");
		manarraylist.add("ttt55555555555555555555555555555");
		manarraylist.add("tt666666666666666666666666666666666666");
		manarraylist.add("ttt111111111111111111777777777777777777777771111111");
		manarraylist.add("ttt888888888888888888888888888888");
		manarraylist.add("ttttt9999999999999999999999999999999999");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		  view = inflater.inflate(R.layout.indexframage, null); 
		  gridview=(GridView)view.findViewById(R.id.gridView1);
		  mangrid=(GridView)view.findViewById(R.id.manhuagridView1);
		findViews(view);  
		 
		
		
		return view;
	}
	public void  findViews(View view)
	{
		bookAdapter=new BookAdapter(arraylist, getActivity().getApplicationContext());
		gridview.setAdapter(bookAdapter);
		manAdapter=new BookAdapter(manarraylist, getActivity().getApplicationContext());
		mangrid.setAdapter(manAdapter);
	}

}
