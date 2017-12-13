package com.book.dfapp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.widget.ImageView;

import com.book.dfapp.activity.MyApplication;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

public class ImageUtil {
	public static synchronized void setViewImage(final Context context, final ImageView view,  final String imageUrl,final boolean small) {
		 
		if (context == null || view == null ||  TextUtils.isEmpty(imageUrl)) {
			return;
		}
		if(small){//前景图片
			Glide.with(context).load(imageUrl).asBitmap().into(view);
		}else{
//			Glide.with(MyApplication.getInstance()).load(imageUrl).into(view);
			Glide.with(context).load(imageUrl).asBitmap().into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
				@Override
				public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
					Drawable drawable = new BitmapDrawable(resource);
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
						view.setBackground(drawable);
					}else{
						view.setBackgroundDrawable(drawable);
					}
				}
			});
		}
	}

}
