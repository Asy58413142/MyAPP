package contentprovider.day.com.cn.day630_broadcastreceiver;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Toast;

/**
 * Created by ann on 2016/7/1.
 */
public class IntentServiceP extends IntentService {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            Toast.makeText(IntentServiceP.this, "sssss", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * @param name Used to name the worker thread, important only for debugging.
     *             为了方便调试，作为一个标记使用
     */
    public IntentServiceP(String name) {
        super(name);
    }

    /**
     * his method is invoked on the worker thread with a request to process.
     * Only one Intent is processed at a time, but the processing happens on
     * a worker thread that runs independently from other application logic.
     * So, if this code takes a long time, it will hold up other requests to
     * the same IntentService, but it will not hold up anything else. When all
     * requests have been handled, the IntentService stops itself, so you should
     * not call stopSelf.
     * <p/>
     * 工作在工作线程中
     *
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            SystemClock.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        handler.sendEmptyMessage(0);
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(IntentServiceP.this, "sssssss", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
