package study.com.cn.day727_viewpagerviewpager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.com.cn.day727_viewpagerviewpager.NewViewPager;
import study.com.cn.day727_viewpagerviewpager.R;
import study.com.cn.day727_viewpagerviewpager.adapter.HomeViewPagerAdapter;

/**
 * Created by ann on 2016/7/27.
 */
public class HomeFragment extends Fragment implements NewViewPager.onViewPagerClick1 {
    private static final String[] url = new String[]{
            "http://g.hiphotos.baidu.com/image/h%3D200/sign=8e5397a66d380cd7f91ea5ed9144ad14/48540923dd54564eac758c2fbbde9c82d1584f97.jpg",
            "http://h.hiphotos.baidu.com/image/h%3D200/sign=53067de0f81f3a2945c8d2cea925bce3/9345d688d43f87947b909410da1b0ef41bd53aa5.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1191474944,1114731785&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=452676373,386681503&fm=21&gp=0.jpg"
    };
    @BindView(R.id.vp)
    NewViewPager viewPager;
    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null, false);
        ButterKnife.bind(this, view);

        List<View> viewList=new ArrayList<View>();
        for (int i = 0; i < url.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            Picasso.with(getActivity()).load(url[i]).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewList.add(imageView);

        }
        viewPager.setAdapter(new HomeViewPagerAdapter(viewList, new HomeViewPagerAdapter.onViewPagerListener() {
            @Override
            public void onViewPagerClickListener() {
                Toast.makeText(getActivity(), "==="+viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();
            }
        }));
        viewPager.setmOnClick(this);
        return view;
    }

    @Override
    public void onViewPagerClickListener1() {
        Toast.makeText(getActivity(), "==="+viewPager.getCurrentItem()+"=======", Toast.LENGTH_SHORT).show();

    }
}
