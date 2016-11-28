package study.com.cn.day813_retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //事件源
        rx.Observable<String> getString=Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onCompleted();
            }
        });
        //监听者
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e("===========", "结束");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("===========", "出现错误 ");
            }

            @Override
            public void onNext(String o) {
                Log.e("===========" ,"可以获取信息");
            }
        };


        //订阅

        getString.subscribe(subscriber);



        //map 把一个事件转换为另一个事件  可以一直map
        Observable<String> observable = Observable.just("哈哈", "嘻嘻").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + "哼哼";
            }
        }).from(new String[]{"哈哈"}).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Action1<String> action = new Action1<String>() {
            @Override
            public void call(String o) {
                Log.e("======","动作的响应事件");
            }
        };

        observable.subscribe(action);


        Subscriber<String> stringSubscriber = new Subscriber<String>() {


            @Override
            public void onStart() {
                super.onStart();

                //可以做准备工作
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };


        final String[] student = {"1,2.,581,5，,42"};
//        Observable.from(savedInstanceState)
//                .flatMap(new Func1<Strudent, Observable<Cursor>>() {
//                    @Override
//                    public Observable<Cursor> call(Strudent strudent) {
//                        return Observable.from(student.);
//                    }
//                });
    }


}
