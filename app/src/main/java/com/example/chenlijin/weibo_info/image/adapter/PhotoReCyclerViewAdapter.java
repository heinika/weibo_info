package com.example.chenlijin.weibo_info.image.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.chenlijin.weibo_info.R;
import com.example.chenlijin.weibo_info.image.activity.OnePicZoomInImageActivity;
import com.example.chenlijin.weibo_info.image.activity.ZoomInImageActivity;
import com.example.chenlijin.weibo_info.image.moudel.Image;
import com.example.chenlijin.weibo_info.image.moudel.PhotoInfo;
import com.example.chenlijin.weibo_info.tools.CommonTools;
import com.example.chenlijin.weibo_info.tools.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlijin on 2015/12/2.
 */
public class PhotoReCyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int PIC_ONE = 0x01;
    private final int PIC_FOUR = 0x02;
    private final int PIC_OTHER = 0x03;
    private LayoutInflater mLayoutInflater;
    private List<PhotoInfo> mShopInfoList;

    private List<Image> imageListNine;

    public PhotoReCyclerViewAdapter(LayoutInflater mLayoutInflater, List<PhotoInfo> mShopInfoList) {
        this.mLayoutInflater = mLayoutInflater;
        this.mShopInfoList = mShopInfoList;
    }

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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case PIC_ONE:
                return new OneImageViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_image_1, null));
            case PIC_FOUR:
                return new FourImageViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_image_4, null));
            default:
                return new Two2NineImageViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_image_2to9, null));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OneImageViewHolder) {
            final List<Image> imageListJustOne = mShopInfoList.get(position).getImageList();
            CommonTools.loadToImageViewFitCenter(imageListJustOne.get(0).getUrl(), ((OneImageViewHolder) holder).imageViewJustOne);
            startZoomInImageActivityOne(imageListJustOne,0,((OneImageViewHolder) holder).imageViewJustOne);
        } else if (holder instanceof FourImageViewHolder) {
            List<Image> imageListFour = mShopInfoList.get(position).getImageList();
            CommonTools.loadToImageView(imageListFour.get(0).getUrl(), ((FourImageViewHolder) holder).imageViewFour_1);
            CommonTools.loadToImageView(imageListFour.get(1).getUrl(), ((FourImageViewHolder) holder).imageViewFour_2);
            CommonTools.loadToImageView(imageListFour.get(2).getUrl(), ((FourImageViewHolder) holder).imageViewFour_3);
            CommonTools.loadToImageView(imageListFour.get(3).getUrl(), ((FourImageViewHolder) holder).imageViewFour_4);
            startZoomInImageActivity(imageListFour,0,((FourImageViewHolder) holder).imageViewFour_1);
            startZoomInImageActivity(imageListFour,1,((FourImageViewHolder) holder).imageViewFour_2);
            startZoomInImageActivity(imageListFour,2,((FourImageViewHolder) holder).imageViewFour_3);
            startZoomInImageActivity(imageListFour,3,((FourImageViewHolder) holder).imageViewFour_4);
        } else if (holder instanceof Two2NineImageViewHolder) {
            imageListNine = mShopInfoList.get(position).getImageList();
            if (imageListNine.size() > 1) {
                CommonTools.loadToImageView(imageListNine.get(0).getUrl(), ((Two2NineImageViewHolder) holder).imageViewOne);
                CommonTools.loadToImageView(imageListNine.get(1).getUrl(), ((Two2NineImageViewHolder) holder).imageViewTwo);
                startZoomInImageActivity(imageListNine,0,((Two2NineImageViewHolder) holder).imageViewOne);
                startZoomInImageActivity(imageListNine,1,((Two2NineImageViewHolder) holder).imageViewTwo);
            }
            if (imageListNine.size() > 2) {
                CommonTools.loadToImageView(imageListNine.get(2).getUrl(), ((Two2NineImageViewHolder) holder).imageViewThree);
                startZoomInImageActivity(imageListNine,2,((Two2NineImageViewHolder) holder).imageViewThree);
            }
            if (imageListNine.size() > 3) {
                ((Two2NineImageViewHolder) holder).tableRow2.setVisibility(View.VISIBLE);
                CommonTools.loadToImageView(imageListNine.get(3).getUrl(), ((Two2NineImageViewHolder) holder).imageViewFour);
                CommonTools.loadToImageView(imageListNine.get(4).getUrl(), ((Two2NineImageViewHolder) holder).imageViewFirve);
                startZoomInImageActivity(imageListNine,3,((Two2NineImageViewHolder) holder).imageViewFour);
                startZoomInImageActivity(imageListNine,4,((Two2NineImageViewHolder) holder).imageViewFirve);
            }
            if (imageListNine.size() > 5) {
                ((Two2NineImageViewHolder) holder).tableRow2.setVisibility(View.VISIBLE);
                CommonTools.loadToImageView(imageListNine.get(5).getUrl(), ((Two2NineImageViewHolder) holder).imageViewSix);
                startZoomInImageActivity(imageListNine,5,((Two2NineImageViewHolder) holder).imageViewSix);
            }
            if (imageListNine.size() > 6) {
                ((Two2NineImageViewHolder) holder).tableRow3.setVisibility(View.VISIBLE);
                CommonTools.loadToImageView(imageListNine.get(6).getUrl(), ((Two2NineImageViewHolder) holder).imageViewSeven);
                startZoomInImageActivity(imageListNine,6,((Two2NineImageViewHolder) holder).imageViewSeven);
            }
            if (imageListNine.size() > 7) {
                CommonTools.loadToImageView(imageListNine.get(7).getUrl(), ((Two2NineImageViewHolder) holder).imageViewEight);
                startZoomInImageActivity(imageListNine,7,((Two2NineImageViewHolder) holder).imageViewEight);
            }
            if (imageListNine.size() > 8) {
                CommonTools.loadToImageView(imageListNine.get(8).getUrl(), ((Two2NineImageViewHolder) holder).imageViewNine);
                startZoomInImageActivity(imageListNine,8,((Two2NineImageViewHolder) holder).imageViewNine);
            }
        }
    }

    public void startZoomInImageActivityOne(final List<Image> imageList, final int clickNum, final ImageView imageview) {
        imageview.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mLayoutInflater.getContext(), OnePicZoomInImageActivity.class);
                intent.putParcelableArrayListExtra(Config.IMAGE_URL_LIST, (ArrayList<? extends Parcelable>) imageList);
                // of both activities are defined with android:transitionName="share"
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation((Activity) mLayoutInflater.getContext(), imageview, "share");
                mLayoutInflater.getContext().startActivity(intent,options.toBundle());
            }
        });
    }

    public void startZoomInImageActivity(final List<Image> imageList, final int clickNum, final ImageView imageview) {
        imageview.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mLayoutInflater.getContext(), ZoomInImageActivity.class);
                intent.putParcelableArrayListExtra(Config.IMAGE_URL_LIST, (ArrayList<? extends Parcelable>) imageList);
                intent.putExtra(Config.IMAGE_NUM, clickNum);
                mLayoutInflater.getContext().startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation((Activity)mLayoutInflater.getContext()).toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mShopInfoList.size();
    }

    public class OneImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewJustOne;

        public OneImageViewHolder(View itemView) {
            super(itemView);
            imageViewJustOne = (ImageView) itemView.findViewById(R.id.imageViewJustOne);
        }
    }

    public class FourImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewFour_1;
        private ImageView imageViewFour_2;
        private ImageView imageViewFour_3;
        private ImageView imageViewFour_4;
        private TableLayout tableLayout4;

        public FourImageViewHolder(View itemView) {
            super(itemView);
            imageViewFour_1 = (ImageView) itemView.findViewById(R.id.imagefour_1);
            imageViewFour_2 = (ImageView) itemView.findViewById(R.id.imagefour_2);
            imageViewFour_3 = (ImageView) itemView.findViewById(R.id.imagefour_3);
            imageViewFour_4 = (ImageView) itemView.findViewById(R.id.imagefour_4);
            tableLayout4 = (TableLayout) itemView.findViewById(R.id.tablelayout_image4);
        }
    }

    public class Two2NineImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewOne;
        ImageView imageViewTwo;
        ImageView imageViewThree;
        ImageView imageViewFour;
        ImageView imageViewFirve;
        ImageView imageViewSix;
        ImageView imageViewSeven;
        ImageView imageViewEight;
        ImageView imageViewNine;
        TableRow tableRow2;
        TableRow tableRow3;
        TableLayout tableLayout9;

        public Two2NineImageViewHolder(View itemView) {
            super(itemView);
            imageViewOne = (ImageView) itemView.findViewById(R.id.image1);
            imageViewTwo = (ImageView) itemView.findViewById(R.id.image2);
            imageViewThree = (ImageView) itemView.findViewById(R.id.image3);
            imageViewFour = (ImageView) itemView.findViewById(R.id.image4);
            imageViewFirve = (ImageView) itemView.findViewById(R.id.image5);
            imageViewSix = (ImageView) itemView.findViewById(R.id.image6);
            imageViewSeven = (ImageView) itemView.findViewById(R.id.image7);
            imageViewEight = (ImageView) itemView.findViewById(R.id.image8);
            imageViewNine = (ImageView) itemView.findViewById(R.id.image9);
            tableRow2 = (TableRow) itemView.findViewById(R.id.table_row2);
            tableRow3 = (TableRow) itemView.findViewById(R.id.table_row3);
            tableLayout9 = (TableLayout) itemView.findViewById(R.id.tablelayout_image9);
        }
    }
}