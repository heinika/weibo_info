package com.example.chenlijin.weibo_info.image.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenlijin.weibo_info.R;
import com.example.chenlijin.weibo_info.tools.CommonTools;
import com.example.chenlijin.weibo_info.tools.Config;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by chenlijin on 2015/12/2.
 */
public class ImageFragment extends Fragment {
    private PhotoView mImageView;
    PhotoViewAttacher mAttacher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_zoom_in,null);
        mImageView = (PhotoView) view.findViewById(R.id.image_zoom_in);
        Bundle bundle = getArguments();
        String url = bundle.getString(Config.IMAGE_URL);
        CommonTools.loadToImageViewZoomIn(url, mImageView);
        mAttacher = new PhotoViewAttacher(mImageView);
        return view;
    }
}
