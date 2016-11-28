package contentprovider.day.com.cn.day630_broadcastreceiver;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection conn =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //连接以后

            BindService.MyBind myBind = (BindService.MyBind) service;


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //断开连接后
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //发送广播
        Intent intent = new Intent();
        intent.setAction("广播的名字");
        intent.putExtra("","");//可以传递数据
        sendBroadcast(intent);
        //开启一个IntentService
        Intent service = new Intent(this,IntentServiceP.class);
        startService(service);

        //开启一个BindService
        Intent bindService = new Intent(this, BindService.class);
        bindService(bindService, conn, Service.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(conn);
    }
}
