package contentprovider.day.com.cn.day615_fragmentlife;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ann on 2016/6/16.
 */
public class AFragment extends Fragment {

    private static final String TAG = "AFragmentLife";

    //1.添加关联到activity中
    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach");
        super.onAttach(context);
    }

    //2.创建fragment
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }
    //在加载布局之前
    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
    }

    //3.当fragment创建界面的时候调用
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        return inflater.inflate(R.layout.frament_a,container,false);
    }

    //return view  之后调用
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    //现场保护
    //可在onCreate和onCreateView 及恢复现场恢复
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    //恢复现场
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    //4.当activity执行完onCreate之后调用
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    //5.当Fragment启动时调用
    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    //6.当fragment可以和用户交互的时候调用（执行完后获得焦点）
    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    //7.当fragment被remove，暂停的时候调用
    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    //8.当fragment停止的时候调用
    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    //9.当fragment销毁界面的时候调用
    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView");
        super.onDestroyView();
    }

    //10.当fragment销毁的时候
    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    //11.当fragment与activity取消关联的时候调用
    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach");
        super.onDetach();
    }
}
