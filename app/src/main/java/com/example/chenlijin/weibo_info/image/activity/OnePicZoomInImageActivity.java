package com.example.chenlijin.weibo_info.image.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenlijin.weibo_info.R;
import com.example.chenlijin.weibo_info.image.moudel.Image;
import com.example.chenlijin.weibo_info.tools.CommonTools;
import com.example.chenlijin.weibo_info.tools.Config;

import java.util.Date;
import java.util.List;

/**
 * Created by chenlijin on 2015/12/10.
 */
public class OnePicZoomInImageActivity extends AppCompatActivity {
    private TextView mTextViewSaveImage;
    private ImageView mImageViewOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_in_image_just_one);
        //设置statusbar为透明色
        setStatusBarColor();
        mTextViewSaveImage = (TextView) findViewById(R.id.textview_save_image_just_one);
        mImageViewOne = (ImageView) findViewById(R.id.image_zoom_in_just_one);
        Intent intent = getIntent();
        final List<Image> imageList = intent.getParcelableArrayListExtra(Config.IMAGE_URL_LIST);
        //加载图片
        CommonTools.loadToImageViewZoomIn(imageList.get(0).getUrl(),mImageViewOne);
        //保存图片
        mTextViewSaveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                CommonTools.saveImage(imageList.get(0).getUrl(),date.getTime()+"");
            }
        });
    }

    //设置statusbar为透明色
    private void setStatusBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }
}
