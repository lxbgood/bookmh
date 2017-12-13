package com.book.dfapp.activity;

import android.os.Handler;
import android.support.v4.app.FragmentActivity;

public class MyActivity extends   FragmentActivity{

	private Handler handler = new Handler();
	public void post(final Runnable action) {
		if (MyActivity.this.isFinishing()) {
			return;
		}
		handler.post(action);
	}
}
