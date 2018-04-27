package com.good.zc.skinplugin.skin.attr;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.good.zc.skinplugin.skin.SkinManager;

/**
 * Created by Zc on 2018/4/26.
 */

public enum  SkinType {
    TEXT_COLOR("textColor") {
        @Override
        public void skin(View view, String resName) {
            ColorStateList colorStateList = SkinManager.getInstance().getSkinResources().getColorByName(resName);
            if(colorStateList!=null) {
                if(view instanceof TextView){
                    ((TextView) view).setTextColor(colorStateList);
                }
            }
        }
    },BACKGROUND("background") {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void skin(View view, String resName) {
            ColorStateList colorStateList = SkinManager.getInstance().getSkinResources().getColorByName(resName);
            if(colorStateList!=null) {
                int color = colorStateList.getColorForState(view.getDrawableState(), 0);
                view.setBackgroundColor(color);
                return;
            }
            Drawable drawable = SkinManager.getInstance().getSkinResources().getDrawableByName(resName);
            if(drawable!=null) {
                view.setBackground(drawable);
            }
        }
    },SRC("src") {
        @Override
        public void skin(View view, String resName) {
            Drawable drawable = SkinManager.getInstance().getSkinResources().getDrawableByName(resName);
            if(drawable!=null) {
                ImageView view1 = (ImageView) view;
                view1.setImageDrawable(drawable);
            }

        }
    };
    private String mResName;
    SkinType(String resName){
        this.mResName = resName;
    }

    public String getResName() {
        return mResName;
    }

    public abstract void skin(View view, String resName);


}
