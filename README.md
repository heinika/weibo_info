# weibo_info
用了一些新控件，仿的微博界面
主要有向上滑动图片渐变消失，tablayout使用，用recyclerview解决1-9张图片的显示问题，点击图片后进入viewpager进行缩放，保存等操作。
layout: photo

## 效果图：

![2015-12-11-121720.png](http://upload-images.jianshu.io/upload_images/759172-cedfda34c1dd41c4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/620)


![2015-12-11-121824.png](http://upload-images.jianshu.io/upload_images/759172-e8ab1e9e0279725e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/620)


![2015-12-11-121900.png](http://upload-images.jianshu.io/upload_images/759172-59f365aa69a6b265.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/620)

### 应用：
设置向上滚动消失的view
``` xml
<android.support.design.widget.CollapsingToolbarLayout
android:id="@+id/collapsing_toolbar"
android:fitsSystemWindows="true"
app:contentScrim="?attr/colorPrimary"
app:expandedTitleMarginEnd="64dp"
app:expandedTitleMarginStart="48dp"
app:layout_scrollFlags="scroll|exitUntilCollapsed">
<!-- 设置图片-->
<!--添加一个定义了app:layout_collapseMode="parallax" 属性的ImageView，出现和消失会有过度-->
<ImageView
android:id="@+id/backdrop"
android:fitsSystemWindows="true"
android:scaleType="centerCrop"
android:src="@drawable/qingdao"
app:layout_collapseMode="parallax"
/>
```
```java
Toolbar自定义menu：
//actionbar导入menu中的设置栏
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.toolbar_actions, menu);
return super.onCreateOptionsMenu(menu);
}


TabLayout配合viewpager使用配置标题
<!-- 自定义tab-->
    <!-- 设置可滑动-->
<android.support.design.widget.TabLayout
android:id="@+id/tabs"
android:layout_width="match_parent"
android:layout_height="wrap_content" />

    <android.support.v4.view.ViewPager
android:id="@+id/viewpager"
android:layout_width="match_parent"
android:layout_height="match_parent" />
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
/**
 * Created by chenlijin on 2015/12/10. 传入标题的FragmentPagerAdapter
 */
public class InfoAdapter extends FragmentPagerAdapter {
private final List<Fragment> mFragments = new ArrayList<>();
private final List<String> mFragmentTitles = new ArrayList<>();

public InfoAdapter(FragmentManager fm) {
super(fm);
    }

public void addFragment(Fragment fragment, String title) {
mFragments.add(fragment);
mFragmentTitles.add(title);

    }

@Override
public Fragment getItem(int position) {
return mFragments.get(position);
    }

@Override
public int getCount() {
return mFragments.size();
    }

@Override
public CharSequence getPageTitle(int position) {
return mFragmentTitles.get(position);
    }
}
```
Glide加载图片：
```java
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
```
Glide下载图片：
```java
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
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```
1~9张图片的加载：
使用recyclerview加载三种不同的布局。
```java
@Override
public int getItemViewType(int position) {
switch (mShopInfoList.get(position).getImageList().size()) {
case 1:
return PIC_ONE;
case 4:
return PIC_FOUR;
default:
return PIC_OTHER;
    }
}
```
share动画：
1，设置共享元素的标签
android:transitionName="share"
2，通过ActivityOptions，传入要分享的view和标签名
Intent intent = new Intent(mLayoutInflater.getContext(), OnePicZoomInImageActivity.class);
intent.putParcelableArrayListExtra(Config.IMAGE_URL_LIST, (ArrayList<? extends Parcelable>) imageList);
// of both activities are defined with android:transitionName="share"
ActivityOptions options = ActivityOptions
        .makeSceneTransitionAnimation((Activity) mLayoutInflater.getContext(), imageview, "share");
mLayoutInflater.getContext().startActivity(intent,options.toBundle());
普通activity的切换动画：
mLayoutInflater.getContext().startActivity(intent,
        ActivityOptions.makeSceneTransitionAnimation((Activity)mLayoutInflater.getContext()).toBundle());
设置statusbar为透明色
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
给imageview添加手势操作
PhotoViewAttachermAttacher;
mAttacher = new PhotoViewAttacher(mImageView);

### 遇到的问题：
viewpager和photoview一起使用崩溃的问题
java.lang.IllegalArgumentException: pointerIndexoutofrange
```java
public class PictureChildViewPager extends ViewPager {
public PictureChildViewPager(Context context, AttributeSet attrs) {
super(context, attrs);
}

public PictureChildViewPager(Context context) {
super(context);
}

@Override
public boolean onInterceptTouchEvent(MotionEvent ev) {
try {
return super.onInterceptTouchEvent(ev);
} catch (IllegalArgumentException e) {
e.printStackTrace();
return false;
}
}

@Override
public boolean onTouchEvent(MotionEvent arg0) {
try {
return super.onTouchEvent(arg0);
} catch (IllegalArgumentException ex) {
return false;
}
}
```
scrollview下嵌套viewpager，viewpager高度不显示
//        //解决viewpager高度无法显示问题
//        mNestedScrollView.setFillViewport(true);
设置linearlayout在appbarlayout下
<!--底部带动顶部的滑动-->
<!--app:layout_behavior="@string/appbar_scrolling_view_behavior">-->
### 没有解决的问题
share动画，打开的activity是viewpager返回时的图片不对
glide加载网络上的长图时，宽度设置为match失效
glide加载图片时，如果设置图片为match match图片会先显示在上方再移动到中间

# github：
https://heinika.github.io/  
源码：https://github.com/heinika/weibo_info