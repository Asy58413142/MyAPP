package contentprovider.day.com.cn.day612_indexablelistview;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;
import android.widget.Toast;

import com.zhzq.android.IndexableListView;
import com.zhzq.android.StringMatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ann on 2016/6/12.
 */
public class IndexListViewFragment extends Fragment {
    private ArrayList<String> mItems;
    private com.zhzq.android.IndexableListView mListView;
    private ContentAdapter adapter;
    private IndexableListView index_listView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mItems = new ArrayList<String>();
        mItems.add("Assdasda");
        mItems.add("Basdas");
        mItems.add("Casdasd");
        mItems.add("Dasdasd");
        mItems.add("The Hunger Games");
        mItems.add("The LEGO Ideas Book");
        mItems.add("Easdasd");
        mItems.add("Cs");
        mItems.add("Eleed");
        mItems.add("Eld");
        mItems.add("Dn Fever");
        mItems.add("Berley");
        mItems.add("Dever");
        mItems.add("Diarr");
        mItems.add("Stev");
        mItems.add("Inle)");
        mItems.add("11/22/63: A Novel");
        mItems.add("Elrrd");
        mItems.add("T");
        mItems.add("LEGO Ideas Book");
        mItems.add("Plum Novel");
        mItems.add("Catching");
        mItems.add("E");
        mItems.add("De");
        Collections.sort(mItems);

        adapter = new ContentAdapter(getActivity(), R.layout.list_item, mItems);



    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_indexlistview, null);

        index_listView = (IndexableListView) view.findViewById(R.id.index_listView);
        index_listView.setAdapter(adapter);
        index_listView.setFastScrollEnabled(true);



        index_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity().getApplicationContext(), "arg2 : " + arg2 + ",arg2 : " + arg2, Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    private class ContentAdapter extends ArrayAdapter<String> implements SectionIndexer {

        private String mSections = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        public ContentAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public int getPositionForSection(int section) {
            // If there is no item for current section, previous section will be selected
            for (int i = section; i >= 0; i--) {
                for (int j = 0; j < getCount(); j++) {
                    if (i == 0) {
                        // For numeric section
                        for (int k = 0; k <= 9; k++) {
                            if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(k))) {
//                                index_listView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                return j;
                            }

                        }
                    } else {
                        if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(mSections.charAt(i)))) {
//                            index_listView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                            return j;
                        }
                    }
                }
            }
            return 0;
        }

        @Override
        public int getSectionForPosition(int position) {
            return 0;
        }

        @Override
        public Object[] getSections() {
            String[] sections = new String[mSections.length()];
            for (int i = 0; i < mSections.length(); i++)
                sections[i] = String.valueOf(mSections.charAt(i));
            return sections;
        }
    }

}
