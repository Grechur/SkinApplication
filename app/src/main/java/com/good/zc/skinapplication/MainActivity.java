package com.good.zc.skinapplication;

import android.content.Intent;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;


import com.good.zc.skinplugin.skin.BaseSkinActivity;
import com.good.zc.skinplugin.skin.SkinManager;

import java.io.File;

public class MainActivity extends BaseSkinActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void skin(View view){
        String skinPath = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator
                +"red.apk";
        //换肤
        int result = SkinManager.getInstance().loadSkin(skinPath);
    }
    public void skin1(View view){

        //换肤
        SkinManager.getInstance().restoreDefault();
    }
    public void navigate(View view){
        startActivity(new Intent(this,MainActivity.class));
    }
}
