package com.good.zc.skinplugin.skin;

import android.app.Activity;
import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.good.zc.skinplugin.skin.attr.SkinView;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Zc on 2018/4/26.
 */

public class SkinManager {
    private static SkinManager mInstance;
    private Context mContext;
    private Map<Activity,List<SkinView>> mSkinViews = new ArrayMap<>();
    static {
        mInstance = new SkinManager();
    }

    private SkinResource mSkinResources;

    public static SkinManager getInstance() {
        return mInstance;
    }

    public void init(Context context){
        this.mContext = context.getApplicationContext();
        String path = mContext.getPackageResourcePath();

        mSkinResources = new SkinResource(mContext, path);
    }

    /**
     * 加载皮肤
     * @param skinPath
     * @return
     */
    public int loadSkin(String skinPath) {
        mSkinResources = new SkinResource(mContext,skinPath);
        Set<Activity> keys = mSkinViews.keySet();
        for (Activity key : keys) {
            List<SkinView> skinViews = mSkinViews.get(key);
            for (SkinView skinView : skinViews) {
                changeSkin(skinView);
            }
        }
        return 0;
    }

    /**
     * 恢复默认
     * @return
     */
    public void restoreDefault() {

        String path = mContext.getPackageResourcePath();

        mSkinResources = new SkinResource(mContext, path);
        Set<Activity> keys = mSkinViews.keySet();
        for (Activity key : keys) {
            List<SkinView> skinViews = mSkinViews.get(key);
            for (SkinView skinView : skinViews) {
                changeSkin(skinView);
            }
        }
    }
    public SkinResource getSkinResources() {
        return mSkinResources;
    }

    public List<SkinView> getSkinViews(Activity activity) {
        return mSkinViews.get(activity);
    }

    public void registerSkinView(List<SkinView> skinViews, Activity activity) {
        mSkinViews.put(activity, skinViews);
    }
    /**
     * 移除回调，怕引起内存泄露
     */
    public void unregister(Activity activity) {
        mSkinViews.remove(activity);
    }

    public void changeSkin(SkinView skinView) {
        skinView.skin();
    }
}
