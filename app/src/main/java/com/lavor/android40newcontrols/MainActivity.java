package com.lavor.android40newcontrols;

import android.hardware.Camera;
import android.graphics.SurfaceTexture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener{
    private TextureView mTexture;
    private Switch mSwitch;
    private Camera mCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTexture= (TextureView) findViewById(R.id.texture_view);
        mSwitch= (Switch) findViewById(R.id._switch);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Log.i("Switch","打开Switch");
                }else{
                    Log.i("Switch","关闭Switch");
                }
            }
        });
        //为mTexture设置表面结构监听器
        mTexture.setSurfaceTextureListener(this);

    }
    public void openPopupMenu(View view){
        //popupMenu显示在view下面
        PopupMenu popupMenu=new PopupMenu(this,view);
        //从xml文件中加载菜单到popupMenu中
        popupMenu.inflate(R.menu.popup_menu);
        //显示  popupMenu
        popupMenu.show();
    }
    public void rotate0(View view){
        mTexture.setAlpha(1.0f);
        mTexture.setRotation(0.0f);
    }
    public void rotate45(View view){
        mTexture.setAlpha(1.0f);
        mTexture.setRotation(45.0f);
    }
    public void rotate90(View view){
        mTexture.setAlpha(1.0f);
        mTexture.setRotation(90.0f);
    }

    /**
     * TextureView的SurfaceTexture准备开始用
     */
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mCamera = Camera.open();
        try {
            //设置mCamera的表面结构为surface
            mCamera.setPreviewTexture(surface);
            //启动相机预览
            mCamera.startPreview();
        } catch (IOException ioe) {
            // Something bad happened
        }

    }

    /**
     * SurfaceTexture的缓存大小改变了
     */
    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    /**
     * SurfaceTexture销毁了
     */
    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        mCamera.stopPreview();
        mCamera.release();
        return true;
    }

    /**
     * SurfaceTexture更新了
     */
    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
}
