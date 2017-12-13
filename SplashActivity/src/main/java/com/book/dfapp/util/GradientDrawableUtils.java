package com.book.dfapp.util;


import com.book.dfapp.activity.MyApplication;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

/**
 *  
 */

public class GradientDrawableUtils {

    private final static int STROKEWIDTH = DensityUtil.dip2px(MyApplication.getInstance(),1);
    private final static int ROUNDRADIUS = DensityUtil.dip2px(MyApplication.getInstance(),5);

    public static void setGradientDrawable(View view, int color, int backColor){
        if(view == null)
            return;
        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setColor(backColor);
        gd.setCornerRadius(ROUNDRADIUS);
        gd.setStroke(STROKEWIDTH, color);
        gd.setGradientType(GradientDrawable.RECTANGLE);
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(gd);
        } else {
            view.setBackground(gd);
        }
    }
    public static void setGradientDrawable(View view, String color, String bgcolor) {
        if (view == null)
            return;
        try {


            GradientDrawable gd = new GradientDrawable();//创建drawable
            gd.setColor(Color.parseColor(bgcolor));
            gd.setCornerRadius(ROUNDRADIUS);
            gd.setStroke(STROKEWIDTH, Color.parseColor(color));
            gd.setGradientType(0);

            int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                view.setBackgroundDrawable(gd);
            } else {
                view.setBackground(gd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
