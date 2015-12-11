package com.example.chenlijin.weibo_info.image.moudel;

import java.util.List;

/**
 * Created by chenlijin on 2015/12/2.
 */
public class PhotoInfo {
    private List<Image> imageList;

    public PhotoInfo(List<Image> imageList) {
        this.imageList = imageList;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}
