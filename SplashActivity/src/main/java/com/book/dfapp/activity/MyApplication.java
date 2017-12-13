package com.book.dfapp.activity;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;

public class MyApplication extends Application {
	public static MyApplication mInstance = null;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mInstance=this; 
	}
	public static MyApplication getInstance()
	{ 
		return mInstance;
	}
	
}
