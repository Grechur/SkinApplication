package com.good.zc.skinplugin.skin;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by Zc on 2018/4/26.
 */

public class SkinResource {
    private Resources mResources;
    private String packName;
    private static final String TAG = "SkinResource";

    public SkinResource(Context context,String skinPath) {
        try {
            //读取本地一个.skin里面的资源
            Resources superRes = context.getResources();
            //创建AssetManager
            AssetManager assetManager= AssetManager.class.newInstance();

            //添加本地下载好的资源皮肤
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath",String.class);
//            method.setAccessible(true);
            method.invoke(assetManager, skinPath);
            mResources = new Resources(assetManager,superRes.getDisplayMetrics(),superRes.getConfiguration());
            //获取skinpath包名
            packName = context.getPackageManager().getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES).packageName;
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Drawable getDrawableByName(String resName){
        try {
            Log.e(TAG,"packName-->"+packName+"resName-->"+resName);
            int resId = mResources.getIdentifier(resName,"mipmap",packName);
            Drawable drawable = mResources.getDrawable(resId);
            return drawable;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ColorStateList getColorByName(String resName){
        try {
            int resId = mResources.getIdentifier(resName,"color",packName);
            ColorStateList drawable = mResources.getColorStateList(resId);
            return drawable;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
