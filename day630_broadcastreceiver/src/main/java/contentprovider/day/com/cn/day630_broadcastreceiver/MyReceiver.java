package contentprovider.day.com.cn.day630_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;


/**
 * 广播接收器，接收消息的流程
 *
 * 可以接受多个广播
 *
 * 1.发送广播，任何广播接收器都能收到该广播
 * 2.广播接收器收到广播后 会根据广播的Action、Category过滤
 * 3.如果符合IntentFilter   ，则处理该广播，调用 onReceive处理
 */
public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    //超过10秒  就会ANR
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (action.equals("广播的类型1")) {
            //接受短信消息
            Object [] pdus = (Object[]) intent.getExtras().get("pdus");

            SmsMessage[] smsMessages = new SmsMessage[pdus.length];

            for (int i = 0; i < pdus.length; i++) {

                smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                String body = smsMessages[i].getDisplayMessageBody();
                String address = smsMessages[i].getDisplayOriginatingAddress();


            }
            SmsManager smsManager = SmsManager.getDefault();

            smsManager.sendTextMessage("10086", null, "短信的内容", null, null);
        } else if (action.equals("广播的类型2")) {

            ConnectivityManager connectivityManager =
                     (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                int type = networkInfo.getType();
                switch (type) {
                    case ConnectivityManager.TYPE_WIFI:
                        Toast.makeText(context, "WiFi连接", Toast.LENGTH_SHORT).show();
                        break;

                    case ConnectivityManager.TYPE_MOBILE:
                        Toast.makeText(context, "手机连接", Toast.LENGTH_SHORT).show();
                        break;

                }
            } else {
                Toast.makeText(context, "无网络连接", Toast.LENGTH_SHORT).show();

            }
        }

        System.currentTimeMillis();
    }
}
