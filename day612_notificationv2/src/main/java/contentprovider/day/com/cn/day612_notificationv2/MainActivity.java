package contentprovider.day.com.cn.day612_notificationv2;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat managerCompat;
    private NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.open_Notification2).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                builder = new NotificationCompat.Builder(MainActivity.this);
                builder.setContentTitle("DownLoading");
                builder.setContentText("this is a good image");
                builder.setSmallIcon(R.mipmap.ic_launcher);

                managerCompat = NotificationManagerCompat.from(MainActivity.this);

                new DownLoadTask().execute();
            }
        });



        findViewById(R.id.open_Notification).setOnClickListener(new View.OnClickListener() {

            private long when;
            private Bitmap largeIcon;

            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                //这三个必须设置
                builder.setContentTitle("标题");
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentText("内容");

                //可以不设置
                builder.setContentInfo("12");
                largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                builder.setLargeIcon(largeIcon);
                when = System.currentTimeMillis();
                builder.setWhen(when);

                //设置 通知声音，振动，呼吸灯都打开
                //也可以自己设置不同的
                builder.setDefaults(NotificationCompat.DEFAULT_ALL);
                Intent intent = new Intent(MainActivity.this, MainActivity.class);

                //设置通知的意图
                //PendingInent 未执行的意图
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pi);

                //设置通知不能取消
                builder.setOngoing(true);

                //设置通知自动取消，点击后自动取消通知
//                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);

                managerCompat.notify(0, builder.build());
            }
        });


        findViewById(R.id.open_Notification1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                builder.setContentTitle("标题");
                builder.setContentText("内容");
                builder.setSmallIcon(R.mipmap.ic_launcher);


//                NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
//
//                style.setBigContentTitle("大标题");
//                style.setSummaryText("summary");
//                style.addLine("爱离开你拉谁发了啥房间爱");
//                style.addLine("阿大声道");
//                style.addLine("未果后无法按时打算的");
//                style.addLine("然后物色个人");
//                style.addLine("然后物色个人");
//                style.addLine("然后物色个人");
//                style.addLine("然后物色个人");
//                style.addLine("拉链上加大");


//                NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
//                style.bigPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
                style.bigText("山东矿机不女阿里的年份拉升的年份拉上你法拉克是你发了卡机书法家阿里刷卡缴费拉上你房间设计费拉斯加罚款及案例是放假了             ");
                builder.setStyle(style);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);

                managerCompat.notify(0x123, builder.build());
            }
        });

    }


    class  DownLoadTask extends AsyncTask<Void,Integer,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            int progress=0;
            while (progress <= 100) {
                progress++;
                publishProgress(progress,100);
            }

            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            builder.setProgress(values[1], values[0], false);
            managerCompat.notify(0x123,builder.build());

        }


        @Override
        protected void onPostExecute(Void aVoid) {
            builder.setProgress(0,0, false);
            builder.setContentText("finish");
            managerCompat.notify(0x123,builder.build());
        }

    }
}
