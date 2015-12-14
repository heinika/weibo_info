package com.example.chenlijin.weibo_info.image.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.chenlijin.weibo_info.R;
import com.example.chenlijin.weibo_info.image.adapter.ViewPagerAdapter;
import com.example.chenlijin.weibo_info.image.fragment.ImageFragment;
import com.example.chenlijin.weibo_info.image.moudel.Image;
import com.example.chenlijin.weibo_info.tools.CommonTools;
import com.example.chenlijin.weibo_info.tools.Config;
import com.example.chenlijin.weibo_info.view.MyViewPager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZoomInImageActivity extends AppCompatActivity{
    private MyViewPager mViewPager;
    private List<Fragment>  mFragmentList;
    private TextView mTextViewImageNum;
    private TextView mTextViewSaveImage;
    private int mPositionNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_in_image);
        //设置statusbar为透明色
        setStatusBarColor();
        mViewPager = (MyViewPager) findViewById(R.id.viewPager_zoom_in);
        mTextViewImageNum = (TextView) findViewById(R.id.textview_image_num);
        mTextViewSaveImage = (TextView) findViewById(R.id.textview_save_image);

        Intent intent = getIntent();
        final List<Image> imageList = intent.getParcelableArrayListExtra(Config.IMAGE_URL_LIST);
        final int imageNum = intent.getIntExtra(Config.IMAGE_NUM,0);
        mPositionNow = imageNum;
        //viewpager界面设置
        initPagerList(imageList);
        ViewPagerAdapter adapter= new ViewPagerAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(imageNum);
        mTextViewImageNum.setText(imageNum+1+"/"+imageList.size());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTextViewImageNum.setText(position+1+"/"+imageList.size());
                mPositionNow = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //保存图片
        mTextViewSaveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                CommonTools.saveImage(imageList.get(mPositionNow).getUrl(),date.getTime()+"");
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

    private void initPagerList(List<Image> imageList) {
        mFragmentList = new ArrayList<>();
        for(int i=0;i<imageList.size();i++) {
            ImageFragment imageFragment = new ImageFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Config.IMAGE_URL,imageList.get(i).getUrl());
            imageFragment.setArguments(bundle);
            mFragmentList.add(imageFragment);
        }
    }
}
