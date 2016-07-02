package com.qiu.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;


/**
 * 闪屏页面 分支测试一下
 *
 * @author qiu create at 2016/6/30 15:23
 */
public class SplashActivity extends Activity {
    /**
     * The Rl root.
     */
    RelativeLayout rlRoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rlRoot = (RelativeLayout) findViewById(R.id.root);
        startAnim(); //调用动画

    }

    /**
     * 开始一个动画
     *
     * @author qiu
     * create at 2016/6/30 15:23
     */
    private void startAnim() {
        //设置动画同时运行，动画集合
        AnimationSet set = new AnimationSet(false);
        //rotate是一个旋转的动画,从0度旋转360度，围绕图的中心（0.5f和0.5f表示正中心）
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);//设置旋转动画的时间是一秒
        rotate.setFillAfter(true);//代表保持动画后的一个状态
        //设置动画缩放
        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(1000);//设置时间为1秒
        //渐变，从完全透明到完全不透明
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(2000);
        alpha.setFillAfter(true);
        //添加动画进动画集合
        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);
        //设置动画监听
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            //动画执行结束
            @Override
            public void onAnimationEnd(Animation animation) {
                jumpNextPage();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        rlRoot.startAnimation(set); //开始动画
    }
    /**  
     * 跳转到下一个页面
     * 时间：2016/7/2 23:18
     * 博客：www.qiuchengjia.cn
     * @author qiu
    */
    private void jumpNextPage(){
        //判断之前有没有显示过新手引导
        SharedPreferences sharedPreferences = getSharedPreferences("config",MODE_PRIVATE);
        boolean userGuide = sharedPreferences.getBoolean("is_user_guide_showed",false);
        if(!userGuide){
            //跳转到新手引导页
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
        }else{
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }
        finish();
    }
}
