package contentprovider.day.com.cn.day616_fragmentinterface;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by ann on 2016/6/16.
 */
public class AFragment extends Fragment {
    private ChangeFragment changeFragment;
    interface ChangeFragment{
        public void change();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ChangeFragment) {
            changeFragment= (ChangeFragment) activity;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        Button button = (Button) view.findViewById(R.id.fragment_a_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (changeFragment != null) {
                    changeFragment.change();
                }
            }
        });
        return view;


    }
}
