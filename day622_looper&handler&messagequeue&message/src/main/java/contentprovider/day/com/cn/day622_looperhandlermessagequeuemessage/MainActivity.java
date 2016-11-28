package contentprovider.day.com.cn.day622_looperhandlermessagequeuemessage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            /***
             * 1. 初始化Looper对象 new Looper(quitAllowed)
             * 2. 创建MessageQueue mQueue = new MessageQueue(quitAllowed);
             * 3. 每一个线程中只能有一个Looper对象  Only one Looper may be created per thread
             * 4. 当一个线程有Looper对象，这个线程也可以叫LooperThread
             */
            Looper.prepare();
            /**
             * 1. 获取当前线程中的Looper对象 mLooper = Looper.myLooper();
             * 2. 获取当前线程中的消息队列  mQueue = mLooper.mQueue;
             */
            Handler handler = new Handler();

            Looper.loop();

        }
    }

}
