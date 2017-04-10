package com.book.dfapp.activity;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.book.dfapp.util.BookMhMode;
import com.book.dfapp.util.ImageUtil;
import com.example.com.book.dfapp.R;

public class ImageAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	public ArrayList<String>  arrayList =new ArrayList<String>();
	private Context mcontext;
	public ImageAdapter(ArrayList<String>  namelist, Context context) {
		// TODO Auto-generated constructor stub
		arrayList=namelist;
		mcontext=context;
		inflater = LayoutInflater.from(context);
	}
	public void setData(ArrayList<String>  list)
	{
		arrayList=list; 
		for (int i = 0; i < list.size(); i++) {
			Log.i("list", list.get(i));
		}
		Log.i("----------------------", "---------------------------------------");
	} 
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arrayList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView( int position, View convertView, ViewGroup parent)  {
		  ViewHolder viewHolder = null;
		  String bean=arrayList.get(position);
		  
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.imageitem, null);
			viewHolder.image = (ImageView) convertView.findViewById(R.id.image); 
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		} 
		
		ImageUtil.setViewImage(mcontext, viewHolder.image , bean, true);
		return convertView;
	}

	private class ViewHolder { 
		ImageView image;
	}
}
