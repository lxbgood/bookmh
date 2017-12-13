package com.book.dfapp.activity;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.book.dfapp.util.BookMode;
import com.book.dfapp.util.ImageUtil;
import com.example.com.book.dfapp.R;

public class ListAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	public ArrayList<BookMode>  arrayList =new ArrayList<BookMode>();
	private Context mcontext;
	public ListAdapter(ArrayList<BookMode>  namelist, Context context) {
		// TODO Auto-generated constructor stub
		arrayList=namelist;
		mcontext=context;
		inflater = LayoutInflater.from(context);
	}
	public void setData(ArrayList<BookMode>  list)
	{
		arrayList=list; 
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
	public View getView(final int position, View convertView, ViewGroup parent)  {
		  ViewHolder viewHolder = null;
		  BookMode bean=arrayList.get(position);
		  
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.listitem, null);
			viewHolder.people = (TextView) convertView.findViewById(R.id.people);
			viewHolder.name = (TextView) convertView.findViewById(R.id.name);
			viewHolder.context = (TextView) convertView.findViewById(R.id.context);
			viewHolder.img = (ImageView) convertView.findViewById(R.id.img); 
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.name.setText(bean.getName());
		viewHolder.people.setText(bean.getAuthor());
		viewHolder.context.setText(bean.getJianjie());
		ImageUtil.setViewImage(mcontext, viewHolder.img, bean.getCoverpic(), true);
		return convertView;
	}

	private class ViewHolder {
		TextView people,name,context; 
		ImageView img;
	}
}
