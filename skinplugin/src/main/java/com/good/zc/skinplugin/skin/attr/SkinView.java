package com.good.zc.skinplugin.skin.attr;

import android.view.View;

import java.util.List;

/**
 * Created by Zc on 2018/4/26.
 */

public class SkinView {
    private View mView;

    private List<SkinAttr> mAttrs;

    public SkinView(View mView, List<SkinAttr> skinAttrs) {
        this.mView = mView;
        this.mAttrs = skinAttrs;
    }

    public void skin(){
        for (SkinAttr attr : mAttrs) {
            attr.skin(mView);
        }
    }
}
