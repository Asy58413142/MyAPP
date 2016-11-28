package contentprovider.day.com.cn.day704_messager_clientservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

public class Services1 extends Service {
    public Services1() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    //  定义服务器信使1
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //接受客户端传过来的数据
            String content= (String) msg.obj;
            Toast.makeText(Services1.this,content, Toast.LENGTH_SHORT).show();
            //接受客户端定义的信使
            Messenger client = msg.replyTo;
            Message message = Message.obtain();
            message.obj = "我是服务器的数据";
            try {
                client.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };
    //  定义服务器信使2
    Messenger service = new Messenger(handler);
    @Override
    public IBinder onBind(Intent intent) {

        return service.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
