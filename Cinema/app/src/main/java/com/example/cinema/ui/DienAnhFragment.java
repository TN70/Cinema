package com.example.cinema.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinema.R;
import com.example.cinema.adapters.DienAnhViewPagerAdapter;
import com.example.cinema.adapters.PhimViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DienAnhFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DienAnhFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    TabLayout tabLayout;
    ViewPager2 viewPager;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DienAnhFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NhomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DienAnhFragment newInstance(String param1, String param2) {
        DienAnhFragment fragment = new DienAnhFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_dien_anh,container,false);
        viewPager = rootView.findViewById(R.id.vpg_dienanh);
        tabLayout = rootView.findViewById(R.id.tab_dienanh);

        // gắn adapter liên kết từ ViewPagerAdapter lên ViewPager
        viewPager.setAdapter(createCardAdapter());
        // Đặt tên các tab
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Bình luận");
//                        tab.setIcon(R.drawable.group);
                        break;
                    case 1:
                        tab.setText("Tin tức");
//                        tab.setIcon(R.drawable.pin);
                        break;
                    case 2:
                        tab.setText("Nhân vật");
//                        tab.setIcon(R.drawable.add_user);
                        break;
                }
            }
        }).attach();
        return rootView;
    }
    private DienAnhViewPagerAdapter createCardAdapter(){
        DienAnhViewPagerAdapter viewPagerAdapter= new DienAnhViewPagerAdapter(getActivity());
        return viewPagerAdapter;
    }
}