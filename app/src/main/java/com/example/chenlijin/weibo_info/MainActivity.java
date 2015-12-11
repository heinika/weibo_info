package com.example.chenlijin.weibo_info;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.chenlijin.weibo_info.adapter.InfoAdapter;
import com.example.chenlijin.weibo_info.fragment.HomeFragment;
import com.example.chenlijin.weibo_info.fragment.PhotoFragment;
import com.example.chenlijin.weibo_info.fragment.WeiBoFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        //设置CollapsingToolbarLayout的标题文字
        collapsingToolbar.setTitle(" ");
        //设置ViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        //设置tablayout，viewpager上的标题
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark));
    }
    /**
     * 设置item
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        InfoAdapter adapter = new InfoAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "主页");
        adapter.addFragment(new WeiBoFragment(), "微博");
        adapter.addFragment(new PhotoFragment(), "相册");
        viewPager.setAdapter(adapter);
    }

    //actionbar导入menu中的设置栏
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
