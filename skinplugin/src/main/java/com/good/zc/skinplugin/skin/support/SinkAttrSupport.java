package com.good.zc.skinplugin.skin.support;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import com.good.zc.skinplugin.skin.attr.SkinAttr;
import com.good.zc.skinplugin.skin.attr.SkinType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zc on 2018/4/26.
 */

public class SinkAttrSupport {
    public static final String TAG = "SinkAttrSupport";
    public static List<SkinAttr> getSkinAttrs(Context context, AttributeSet attrs) {

        //background   src   textcolor
        List<SkinAttr> skinAttrs = new ArrayList<>();

        int attrSize = attrs.getAttributeCount();
        for (int i =0;i<attrSize;i++) {
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);
            Log.e(TAG,"attrName-->"+attrName+";attrValue-->"+attrValue);
            //获取皮肤类型
            SkinType skinType = getSkinType(attrName);
            if(skinType!=null){
                String resName = getResName(context,attrValue);
                if(TextUtils.isEmpty(resName)){
                    continue;
                }

                SkinAttr skinAttr = new SkinAttr(resName,skinType);
                skinAttrs.add(skinAttr);
            }
        }

        return skinAttrs;
    }

    /**
     *
     * @param context
     * @param attrValue
     * @return
     */
    private static String getResName(Context context, String attrValue) {
        if(attrValue.startsWith("@")){
            attrValue = attrValue.substring(1);
            int resId = Integer.parseInt(attrValue);
            return context.getResources().getResourceEntryName(resId);
        }
        return null;
    }

    /**
     *
     * @param attrName
     * @return
     */
    private static SkinType getSkinType(String attrName) {
        SkinType[] skinTypes = SkinType.values();
        for (SkinType skinType : skinTypes) {
            if(skinType.getResName().equals(attrName)){
                return skinType;
            }
        }
        return null;
    }
}
