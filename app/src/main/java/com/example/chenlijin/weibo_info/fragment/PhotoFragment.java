package com.example.chenlijin.weibo_info.fragment;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenlijin.weibo_info.R;
import com.example.chenlijin.weibo_info.image.adapter.PhotoReCyclerViewAdapter;
import com.example.chenlijin.weibo_info.image.moudel.Image;
import com.example.chenlijin.weibo_info.image.moudel.PhotoInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chenlijin on 2015/12/2.
 */
public class PhotoFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<PhotoInfo> mPhotoInfoList;
    private List<Image> imageList1;
    private List<Image> imageList2;
    private List<Image> imageList3;
    private List<Image> imageList4;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_photo);
        mRecyclerView.setLayoutManager(layoutManager);
        initDatas();
        LayoutInflater layoutInflater = getLayoutInflater(savedInstanceState);
        PhotoReCyclerViewAdapter adapter = new PhotoReCyclerViewAdapter(layoutInflater, mPhotoInfoList);
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    private void initDatas() {
        mPhotoInfoList = new ArrayList<>();
        //实验9图
        imageList1 = new ArrayList<>();
        imageList2 = new ArrayList<>();
        imageList3 = new ArrayList<>();
        imageList4 = new ArrayList<>();
        imageList1.add(new Image("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg"));
        imageList2.add(new Image("http://img3.3lian.com/2006/013/08/20051103121420947.gif"));
        imageList2.add(new Image("http://img4.duitang.com/uploads/item/201406/12/20140612211118_YYXAC.jpeg"));
        imageList2.add(new Image("http://img1.imgtn.bdimg.com/it/u=1813558191,3355125224&fm=21&gp=0.jpg"));
        imageList2.add(new Image("http://img4.imgtn.bdimg.com/it/u=1743300897,2564531585&fm=21&gp=0.jpg"));
        imageList2.add(new Image("http://img4.imgtn.bdimg.com/it/u=3003008274,2604144956&fm=21&gp=0.jpg"));
        imageList2.add(new Image("http://img4.duitang.com/uploads/item/201407/29/20140729182035_5ui4J.jpeg"));
        imageList2.add(new Image("http://img2.imgtn.bdimg.com/it/u=1868085000,3976235118&fm=21&gp=0.jpg"));
        imageList2.add(new Image("http://i2.tietuku.com/e21fd0fec600ac74.jpg"));
        imageList3.add(new Image("http://d.hiphotos.baidu.com/image/h%3D220/sign=e55c0de1fb1f4134ff37027c151f95c1/d043ad4bd11373f0c140ff9da20f4bfbfbed04b0.jpg"));
        imageList3.add(new Image("http://pic41.nipic.com/20140529/18243620_101015342117_2.gif"));
        imageList3.add(new Image("http://img3.imgtn.bdimg.com/it/u=680225953,3887619066&fm=21&gp=0.jpg"));
        imageList3.add(new Image("http://www.55zm.com/uploads/allimg/120625/556-120625154INY.png"));
        imageList4.add(new Image("http://img10.3lian.com/sc6/show02/38/65/386515.jpg"));
        PhotoInfo photoInfo1 = new PhotoInfo(imageList1);
        PhotoInfo photoInfo2 = new PhotoInfo(imageList2);
        PhotoInfo photoInfo3 = new PhotoInfo(imageList3);
        PhotoInfo photoInfo4 = new PhotoInfo(imageList4);
        mPhotoInfoList.add(photoInfo1);
        mPhotoInfoList.add(photoInfo2);
        mPhotoInfoList.add(photoInfo3);
        mPhotoInfoList.add(photoInfo4);
        mPhotoInfoList.add(photoInfo2);
        mPhotoInfoList.add(photoInfo3);
        mPhotoInfoList.add(photoInfo1);
        mPhotoInfoList.add(photoInfo4);
    }
}
