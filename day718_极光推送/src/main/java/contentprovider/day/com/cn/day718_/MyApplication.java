package contentprovider.day.com.cn.day718_;

import android.app.Application;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

import cn.com.qubaoyang.R;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by ann on 2016/7/18.
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);


        HashSet<String> set = new HashSet<String>();
        set.add("A");
        set.add("B");
        set.add("C");
        //设置标签和别名
        JPushInterface.setAliasAndTags(this, "哈哈", set, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                if (i == 0) {
                    Toast.makeText(MyApplication.this,"OK", Toast.LENGTH_SHORT).show();
                }
            }
        });


        HashSet<Integer> day=new HashSet<Integer>();
        day.add(1);
        day.add(2);
        day.add(3);
        day.add(4);
        day.add(5);
        //设置用户可以接受到通知的时间段
        //day:1/2/3/4/5/6/7/
        //hour 0-24
        JPushInterface.setPushTime(this,day,12,20);

        //可以改变通知效果
//        BasicPushNotificationBuilder builder=new BasicPushNotificationBuilder(this);
//        builder.statusBarDrawable = R.mipmap.ic_launcher;
//        builder.notificationDefaults = NotificationCompat.DEFAULT_ALL;
        //
        CustomPushNotificationBuilder builder = new CustomPushNotificationBuilder(this,R.layout.customer_notitfication_layout,
                R.id.icon,
                R.id.title,
                R.id.text);
        builder.statusBarDrawable = R.drawable.ic_action_guitar;
        builder.layoutIconDrawable = R.drawable.ic_action_guitar;
        JPushInterface.setPushNotificationBuilder(0,builder);
    }
}
