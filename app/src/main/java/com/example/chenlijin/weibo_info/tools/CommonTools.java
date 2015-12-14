package com.example.chenlijin.weibo_info.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.chenlijin.weibo_info.R;
import com.example.chenlijin.weibo_info.base.BaseApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by chenlijin on 2015/12/2.
 */
public class CommonTools {
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f) - 15;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static void loadToImageView(String url, ImageView imageView) {
        if (url.endsWith("gif")) {
            loadToImageViewStaticGif(url, imageView);
        } else {
            Glide.with(BaseApplication.getContext())
                    .load(url)
                    .placeholder(R.drawable.loading)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(imageView);
        }
    }

    public static void loadToImageViewFitCenter(String url, ImageView imageView) {
        if (url.endsWith("gif")) {
            loadToImageViewStaticGif(url, imageView);
        } else {
            Glide.with(BaseApplication.getContext())
                    .load(url)
                    .into(imageView);
        }
    }

    public static void loadToImageViewZoomIn(String url, ImageView imageView) {
        if (url.endsWith("gif")) {
            loadToImageViewZoomInGif(url, imageView);
        } else {
            Glide.with(BaseApplication.getContext())
                    .load(url)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    public static void loadToImageViewZoomInGif(String url, ImageView imageView) {
        Glide.with(BaseApplication.getContext())
                .load(url)
                .asGif()
                .fitCenter()
                .into(imageView);
    }


    public static void loadToImageViewStaticGif(String url, ImageView imageView) {
        Glide.with(BaseApplication.getContext())
                .load(url)
                .asBitmap()
                .centerCrop()
                .into(imageView);
    }

    public static void saveImage(String url, final String name) {
        Glide.with(BaseApplication.getContext())
                .load(url)
                .asBitmap()
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                        // Do something with bitmap here.
                        saveBitmap(name,resource);
                    }

                });
    }

    public static void saveBitmap(String bitName, Bitmap mBitmap) {
        File f = new File("/sdcard/Pictures/" + bitName+".png");
        try {
            f.createNewFile();
        } catch (IOException e) {

        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
            Toast.makeText(BaseApplication.getContext(),"保存完成",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(BaseApplication.getContext(),"保存失败",Toast.LENGTH_SHORT).show();
        }
    }
}
