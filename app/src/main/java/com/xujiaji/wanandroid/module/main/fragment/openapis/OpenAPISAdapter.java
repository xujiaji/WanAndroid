package com.xujiaji.wanandroid.module.main.fragment.openapis;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xujiaji.wanandroid.R;
import com.xujiaji.wanandroid.repository.bean.ThreeAPIBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * author: xujiaji
 * created on: 2018/8/18 22:47
 * description:
 */
public class OpenAPISAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_SECTION = 0;
    public static final int TYPE_API = 1;

    @Inject
    public OpenAPISAdapter() {
        super(new ArrayList<MultiItemEntity>());
        addItemType(TYPE_SECTION, R.layout.item_open_api_section);
        addItemType(TYPE_API, R.layout.item_open_api);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_SECTION:
                ThreeAPIBean threeAPIBean = (ThreeAPIBean) item;
                helper.setText(R.id.title_section, threeAPIBean.getName());
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (threeAPIBean.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                expand(helper.getAdapterPosition());
                break;
            case TYPE_API:
                ThreeAPIBean.LinkBean linkBean = (ThreeAPIBean.LinkBean) item;
                helper.setText(R.id.linkName, linkBean.getName());
                break;
        }
    }
}
