package com.book.dfapp.util;

import java.util.HashMap;

import com.book.dfapp.activity.MyActivity;
import com.book.dfapp.activity.MyApplication;
import com.example.com.book.dfapp.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import okhttp3.Call;

public class HttpUtil {
	public HashMap<String, String> parmrMap;
	public HttpListener httplistener;
	public String urlhost="http://www.baidu.com";
	private ViewGroup modal;

	private ViewGroup rootFrameLayout;

	private ProgressBar progressBar;
	private LinearLayout linearLayout1;
	private MyActivity activity=null;
	public HttpUtil(MyActivity act,HashMap<String, String> map,HttpListener listener)
	{
		activity=act;
		parmrMap=map;
		httplistener=listener;
	}
	public void  sendHttp()
	{
		firstMission();
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
						getJsonGet(urlhost);
			}
		});
		thread.start();
		
	}
	private  void getJsonGet(String urlStr)
	{
		Log.i("HttpGroup","-get- url -- json--->> " + urlStr);
		
			OkHttpUtils
					.get()
					.url(urlStr)
					.params(parmrMap)
					.build()
					.execute(new StringCallback() {
						@Override
						public void onError(Call arg0,
											Exception arg1, int arg2) {
							// TODO Auto-generated method stub
							Log.i("HttpGroup", "- StringCallback-Exception -->> "
											+ arg1.getMessage());
							httplistener.OnFile();
							remove();
						}

						@Override
						public void onResponse(String arg0, int arg1) {
							// TODO Auto-generated method stub
						 
							Log.i("HttpGroup",
									"id:- StringCallback -->> "
											+ arg0);
							if (!TextUtils.isEmpty(arg0)) {
								 
								try {
									httplistener.OnSucess(arg0); 
								} catch (Exception e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								} 
							}
							remove();
						}
					});
	}
	private ViewGroup getModal() {
		if (null != modal) {
			return modal;
		}

		modal = new RelativeLayout(activity);
		// 禁止触屏（用这种方式实现感觉不太爽）
		modal.setOnTouchListener(new ViewGroup.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) { 
				return false;
			}
		});
 
		return modal;
	}
	private ViewGroup getRootFrameLayout() {

		if (null != rootFrameLayout) {
			return rootFrameLayout;
		}
		
		rootFrameLayout = (ViewGroup) activity.getWindow().peekDecorView();
		if (null == rootFrameLayout) {// 可能界面还没绘制或者还没设置。
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
			rootFrameLayout = getRootFrameLayout();
		}

		return rootFrameLayout;

	}
	private void firstMission() {
			
			final ViewGroup rootFrameLayout = getRootFrameLayout();
			final ViewGroup modal = getModal();
			newProgressBar();
			
			activity.post(new Runnable() {// 不能使用attemptRunOnUiThread方法，因为如果当前是UI线程那么可能在①还未执行时，此处先执行了，导致同一个modal被addView多次的异常。
						public void run() {

							 
							rootFrameLayout.addView(modal, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
							rootFrameLayout.invalidate();

							 

						}
					}); 

	}
	private void newProgressBar() {
		activity.post(new Runnable() {
			@Override
			public void run() {
				modal.removeView(linearLayout1); 
				
				if(linearLayout1==null)
				{
					View loading = LayoutInflater.from(MyApplication.getInstance()).inflate(R.layout.loading_layout, null); 
					LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(dip2px(MyApplication.getInstance().getApplicationContext(),140),dip2px(MyApplication.getInstance().getApplicationContext(),84));
					loading.setLayoutParams(layoutParams);
 
					linearLayout1 =new LinearLayout(MyApplication.getInstance());
					LinearLayout.LayoutParams layoutParams1=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
					linearLayout1.setLayoutParams(layoutParams1);
					linearLayout1.setOrientation(LinearLayout.VERTICAL);
					linearLayout1.setGravity(Gravity.CENTER);
					linearLayout1.addView(loading); 
				}
				
				modal.addView(linearLayout1);
			}
		});
	}
	public   int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    } 
	public void remove()
	{
		// 真正撤销遮罩了
					final ViewGroup rootFrameLayout = getRootFrameLayout();
					final ViewGroup modal = getModal();
					activity.post(new Runnable() {// 放到任务队列，但是还未执行①
								@Override
								public void run() {

									 
									rootFrameLayout.removeView(modal);
									rootFrameLayout.invalidate();

									 

								}
							});
	}
}
