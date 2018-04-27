package com.good.zc.skinplugin.skin.attr;

import android.view.View;

/**
 * Created by Zc on 2018/4/26.
 */

public class SkinAttr {
    private String mResName;
    private SkinType mType;

    public SkinAttr(String resName,SkinType type){
        this.mResName = resName;
        this.mType = type;
    }

    public void skin(View view) {
        mType.skin(view,mResName);
    }
}
