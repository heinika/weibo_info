package com.example.chenlijin.weibo_info.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.chenlijin.weibo_info.R;
import com.example.chenlijin.weibo_info.adapter.HomeRecyclerViewAdapter;
import com.example.chenlijin.weibo_info.model.WeiBo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chenlijin on 2015/12/2.
 */
public class WeiBoFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<WeiBo> mWeoBoList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weibo,null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_weibo);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        initWeiBoList();
        HomeRecyclerViewAdapter adapter = new HomeRecyclerViewAdapter(getContext(),mWeoBoList);
        mRecyclerView.setLayoutManager(layoutManager);
        RecyclerViewHeader header = (RecyclerViewHeader) view.findViewById(R.id.header_weibo);
        header.attachTo(mRecyclerView, true);
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    private void initWeiBoList() {
        mWeoBoList = new ArrayList<>();
        for(int i=0;i<20;i++) {
            WeiBo weibo = new WeiBo();
            mWeoBoList.add(weibo);
        }
    }
}
