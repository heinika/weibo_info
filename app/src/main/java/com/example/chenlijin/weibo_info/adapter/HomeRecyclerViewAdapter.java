package com.example.chenlijin.weibo_info.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenlijin.weibo_info.R;
import com.example.chenlijin.weibo_info.model.WeiBo;

import java.util.List;

/**
 * Created by chenlijin on 2015/12/10.
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {
    private List<WeiBo> mWeiBoList;
    private Context mContext;

    public HomeRecyclerViewAdapter(Context mContext,List<WeiBo> mWeiBoList) {
        this.mWeiBoList = mWeiBoList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_weibo,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mWeiBoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
