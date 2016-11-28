package study.com.cn.day805_;

import android.support.v4.widget.DrawerLayout;
import android.view.View;

/**
 * Created by ann on 2016/8/5.
 */
public class CreateDrawerLayoutListener {

    private static DrawerLayoutListener drawerLayoutListener;


    public static void setAddDrawerLayoutListener(DrawerLayout drawerLayout) {
        drawerLayoutListener = new DrawerLayoutListener();
        drawerLayout.addDrawerListener(drawerLayoutListener);
    }

    /**
     * 监听类
     */
    static class DrawerLayoutListener implements DrawerLayout.DrawerListener {

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(View drawerView) {

        }

        @Override
        public void onDrawerClosed(View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    }

    /**
     * 取消监听
     */
    public static  void setRemoveDrawerListener(DrawerLayout drawerLayout){
        drawerLayout.removeDrawerListener(drawerLayoutListener);
    }
}
