package com.book.dfapp.activity;

import java.io.File;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.book.dfapp.util.CountDownTask;
import com.bumptech.glide.Glide;
import com.example.com.book.dfapp.R;

public class SplashActivity extends MyActivity{
	 
    ImageView image; 
    private TextView jump;
    private String picUrl;
    public  boolean isAndFixAllReady = false;

    // private LocationService locationService;//定位

    private CountDownTask countDownTask,countDownNewTask;
    private int countDownTime;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_layout);
        try {
           
            initView();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
           
        }
    }

    private void initView() {
        image = (ImageView) findViewById(R.id.image);

        jump = (TextView) findViewById(R.id.jump);

                Glide.with(this).load(R.drawable.loading_flash).into(image);
       
        


 

        countDownNewTask = new CountDownTask(new CountDownTask.CountDownCallBack() {
            @Override
            public void onCounting(int currentCount) {
                jump.setText("跳过(" + currentCount + ")");
//                FileUtil.writeFileToSD("testAndFix.txt","闪屏新倒计时"+currentCount+"s"+" 热修复是否ready: "+isAndFixAllReady);
                if(isAndFixAllReady){
                    countDownNewTask.stop();
                    checkAndStartGuildPage();
                }
            }

            @Override
            public void onCountDownOver() {
                jump.setVisibility(View.GONE);
                checkAndStartGuildPage();
            }
        });
        countDownTime =6;

         

        countDownNewTask.start(countDownTime);
    }

  
    protected void  checkAndStartGuildPage() {
         
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        
    }

   
 

    private void stopCountDown() {
        try {
            if (countDownTask != null) {
                countDownTask.stop();
            }
            if (countDownNewTask != null) {
                countDownNewTask.stop();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
 


}
