package com.good.zc.skinplugin.skin;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;

import com.good.zc.skinplugin.skin.attr.SkinAttr;
import com.good.zc.skinplugin.skin.attr.SkinView;
import com.good.zc.skinplugin.skin.support.SinkAttrSupport;
import com.good.zc.skinplugin.skin.support.SkinAppCompatViewInflater;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSkinActivity extends AppCompatActivity implements LayoutInflaterFactory{

    private SkinAppCompatViewInflater mAppCompatViewInflater;
    private static final String TAG = "BaseSkinActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory(layoutInflater,this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        //拦截到view的创建，获取view之后解析
        //1.创建view
        View view = createView(parent,name,context,attrs);
        //2.解析属性 src textcolor background 自定义属性
        Log.e(TAG,view+"");

        if(view != null){
            List<SkinAttr> skinAttrs = SinkAttrSupport.getSkinAttrs(context,attrs);
            SkinView skinView = new SkinView(view,skinAttrs);
            //3.统一交给SkinManager管理
            managerSkinView(skinView);
        }

        return view;
    }

    /**
     * 统一管理skinView
     * @param skinView
     */

    private void managerSkinView(SkinView skinView) {
        List<SkinView> skinViews = SkinManager.getInstance().getSkinViews(this);
        if (skinViews == null) {
            skinViews = new ArrayList<>();
            SkinManager.getInstance().registerSkinView(skinViews, this);
        }
        skinViews.add(skinView);
        // 如果需要换肤
        SkinManager.getInstance().changeSkin(skinView);
    }

    public View createView(View parent, String name, Context context, AttributeSet attrs) {
        final boolean isPre21 = Build.VERSION.SDK_INT < 21;

        if (mAppCompatViewInflater == null) {
            mAppCompatViewInflater = new SkinAppCompatViewInflater();
        }

        // We only want the View to inherit it's context if we're running pre-v21
        final boolean inheritContext = isPre21 && true
                && shouldInheritContext((ViewParent) parent);

        return mAppCompatViewInflater.createView(parent, name, context, attrs, inheritContext,
                isPre21, /* Only read android:theme pre-L (L+ handles this anyway) */
                true /* Read read app:theme as a fallback at all times for legacy reasons */
        );
    }
    private boolean shouldInheritContext(ViewParent parent) {
               if (parent == null) {
                      // The initial parent is null so just return false
                      return false;
                    }
                while (true) {
                       if (parent == null) {
                              // Bingo. We've hit a view which has a null parent before being terminated from
                               // the loop. This is (most probably) because it's the root view in an inflation
                              // call, therefore we should inherit. This works as the inflated layout is only
                             // added to the hierarchy at the end of the inflate() call.
                                return true;
                         } else if (parent == getWindow().getDecorView() || !(parent instanceof View)
                               || ViewCompat.isAttachedToWindow((View) parent)) {
                              // We have either hit the window's decor view, a parent which isn't a View
                               // (i.e. ViewRootImpl), or an attached view, so we know that the original parent
                               // is currently added to the view hierarchy. This means that it has not be
                               // inflated in the current inflate() call and we should not inherit the context.
                               return false;
                           }
                      parent = parent.getParent();
                   }
          }
}
