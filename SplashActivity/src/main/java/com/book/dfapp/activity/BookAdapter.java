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

public class BookAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	public ArrayList<BookMode>  arrayList =new ArrayList<BookMode>();
	private Context mcontext;
	public BookAdapter(ArrayList<BookMode>  namelist, Context context) {
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
			convertView = inflater.inflate(R.layout.bookitem, null);
			viewHolder.text = (TextView) convertView.findViewById(R.id.text);
			viewHolder.img = (ImageView) convertView.findViewById(R.id.img); 
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		MyActivity myActivity=(MyActivity)mcontext;
		ImageUtil.setViewImage(myActivity, viewHolder.img , bean.getCoverpic(), true);
		viewHolder.text.setText(bean.getName());
		return convertView;
	}

	private class ViewHolder {
		TextView text; 
		ImageView img;
	}
}
