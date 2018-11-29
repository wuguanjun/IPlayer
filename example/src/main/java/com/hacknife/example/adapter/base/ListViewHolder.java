package com.hacknife.example.adapter.base;

import android.view.View;

import com.hacknife.briefness.BindLayout;
import com.hacknife.example.R;
import com.hacknife.example.adapter.base.i.BaseListViewHolder;
import com.hacknife.iplayer.DataSource;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : IPlayer
 */
@BindLayout(R.layout.item_recycler_view)
public class ListViewHolder extends BaseListViewHolder<DataSource, ListViewHolderBriefnessor> {

    public ListViewHolder(View view) {
        super(view);
    }

    @Override
    protected void bindData(DataSource dataSource) {
        briefnessor.iplayer.setDataSource(dataSource);
    }

}
