package contentprovider.day.com.cn.day704_messager_clientservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Messenger servicem;

    public  ServiceConnection conn=new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取服务端信使并且传递参数
            servicem = new Messenger(service);
            Message message = Message.obtain();
            message.obj = "hahahah";
            //把客户端的信使发送给服务器
            message.replyTo = client;
            try {
                servicem.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    //  定义客户端信使1
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //接受服务器传过来的数据
            String content= (String) msg.obj;
            Toast.makeText(MainActivity.this,content, Toast.LENGTH_SHORT).show();
        }
    };
    //  定义客户端信使2
    Messenger client = new Messenger(handler);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, Services1.class);
        bindService(intent,conn, Service.BIND_AUTO_CREATE);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbindService(conn);
    }
}
