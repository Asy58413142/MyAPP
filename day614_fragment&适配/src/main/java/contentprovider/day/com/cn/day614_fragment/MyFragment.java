package contentprovider.day.com.cn.day614_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ann on 2016/6/14.
 */
public class MyFragment extends Fragment {


    private Button button2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);

//        View view = View.inflate(getActivity(), R.layout.fragment_my, null);

//        View view = inflater.inflate(R.layout.fragment_my, null);


        button2 = (Button) view.findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(Intent.ACTION_CALL);
//                intent.setData(Uri.parse("tel:18232094799"));
//              /*  if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }*/
//                startActivity(intent);
                // 必须在这个界面return后才能获取，要不然会空指针
                //注意获取控件的时机
                TextView tv = (TextView) getActivity().findViewById(R.id.b_tv);
                CharSequence text = tv.getText();
                button2.setText(text);
            }
        });
        return view;

    }

    public Button getButton2() {
        return button2;
    }
}
