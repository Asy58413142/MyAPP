package contentprovider.day.com.cn.day629_viewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<TextView> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        views = new ArrayList<TextView>();

        for (int i = 0; i < 5; i++) {
            TextView textView = new TextView(this);
            textView.setText("Item" + i);
            views.add(textView);
        }

        viewPager.setAdapter(new viewPagerAdapter());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int p = position % views.size();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置viewpager开始在中间位置显示，这样可以使向左无限滑动
        viewPager.setCurrentItem(Integer.MAX_VALUE / 8 * views.size());
    }

    class viewPagerAdapter extends PagerAdapter{
        //一共有几个页
        @Override
        public int getCount() {
            //无限循环
            return Integer.MAX_VALUE;
        }
        //设置 position页的显示效果
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView view = views.get(position%views.size());
            container.addView(view);
            return view;
        }
        //销毁 或取消 position页的显示效果
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position%views.size()));
        }
        // view 和 object 是同一个对象，才会在viewpager中显示  调用多次
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }

}
