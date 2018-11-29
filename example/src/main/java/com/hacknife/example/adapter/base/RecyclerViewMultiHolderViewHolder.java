package com.hacknife.example.adapter.base;


import android.view.View;

import com.bumptech.glide.Glide;
import com.hacknife.example.BriefnessInjector;
import com.hacknife.example.R;

import com.hacknife.briefness.BindLayout;
import com.hacknife.example.adapter.base.i.BaseRecyclerViewHolder;
import com.hacknife.iplayer.DataSource;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : MVVM
 */
@BindLayout(R.layout.item_recycler_view_multi_holder)
public class RecyclerViewMultiHolderViewHolder extends BaseRecyclerViewHolder<DataSource, RecyclerViewMultiHolderViewHolderBriefnessor> {


    public RecyclerViewMultiHolderViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(DataSource entity) {
        Glide.with(briefnessor.tv_cover).load(entity.getCover()).into(briefnessor.tv_cover);
        BriefnessInjector.injector(briefnessor.tv_title, entity.title());
    }


}