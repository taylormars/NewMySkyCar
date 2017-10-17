package com.example.administrator.newmyskycar;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.newmyskycar.adapter.TheMainAdapter;
import com.example.administrator.newmyskycar.fragment.ShouyeFragment;
import com.example.administrator.newmyskycar.fragment.SijiFragment;
import com.example.administrator.newmyskycar.fragment.WodeFragment;
import com.example.administrator.newmyskycar.fragment.YundanFragment;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

public class MainShouyeActivity extends AutoLayoutActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ShouyeFragment mShouye;
    private SijiFragment mSiji;
    private YundanFragment mYundan;
    private WodeFragment mWode;
    private List<Fragment> mListFragment;
    private List<String> mListTitle;
    private TheMainAdapter mTheMainAdapter;
    private int mFragment;
    private int mFragmentTo;
    private int[] mTabIcons = {
            R.mipmap.main_left_order_nomal,
            R.mipmap.main_left_order_history_n,
            R.mipmap.main_left_order_public_n,
            R.mipmap.main_left_renmai_nomal
    };
    private int[] mTabIconsPressed = {
            R.mipmap.main_left_order_down,
            R.mipmap.main_left_order_history_d,
            R.mipmap.main_left_order_public_d,
            R.mipmap.main_left_renmai_down
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shouye);
        init();
        initEvent();
    }
    private void init() {
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        //初始化各fragment
        mShouye = new ShouyeFragment();
        mSiji = new SijiFragment();
        mYundan = new YundanFragment();
        mWode = new WodeFragment();
        //将fragment装进列表中
        mListFragment = new ArrayList<>();
        mListFragment.add(mShouye);
        mListFragment.add(mYundan);
        mListFragment.add(mSiji);
        mListFragment.add(mWode);
        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        mListTitle = new ArrayList<>();
        mListTitle.add("首页");
        mListTitle.add("运单");
        mListTitle.add("司机");
        mListTitle.add("我的");
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText(mListTitle.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mListTitle.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mListTitle.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mListTitle.get(3)));
        mTheMainAdapter = new TheMainAdapter(getSupportFragmentManager(), mListFragment, mListTitle);
        mViewPager.setAdapter(mTheMainAdapter);
        //mFragment = getIntent().getExtras().getInt("Fragment");
        //mViewPager.setCurrentItem(mFragment);
        mTabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();
    }
    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setCustomView(getTabView(0));
        mTabLayout.getTabAt(1).setCustomView(getTabView(1));
        mTabLayout.getTabAt(2).setCustomView(getTabView(2));
        mTabLayout.getTabAt(3).setCustomView(getTabView(3));
    }
    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
        TextView txt_title = view.findViewById(R.id.mTxtTitle);
        txt_title.setText(mListTitle.get(position));
        ImageView img_title = view.findViewById(R.id.mImgTitle);
        img_title.setImageResource(mTabIcons[position]);

        if (position == mFragment) {
            txt_title.setTextColor(0xFF0A74B8);
            img_title.setImageResource(mTabIconsPressed[position]);
        } else {
            txt_title.setTextColor(0xFF6a6a77);
            img_title.setImageResource(mTabIcons[position]);
        }
        return view;
    }
    private void initEvent() {
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabSelect(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabNormal(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void changeTabSelect(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        ImageView img_title = view.findViewById(R.id.mImgTitle);
        TextView txt_title = view.findViewById(R.id.mTxtTitle);
        txt_title.setTextColor(0xFF0A74B8);
        switch (tab.getPosition()) {
            case 0://"实时监护"
                img_title.setImageResource(mTabIconsPressed[0]);
                mFragmentTo = 0;
                mViewPager.setCurrentItem(0);
                break;
            case 1://"体征统计"
                img_title.setImageResource(mTabIconsPressed[1]);
                mFragmentTo = 1;
                mViewPager.setCurrentItem(1);
                break;
            case 2://"睡眠报告"
                img_title.setImageResource(mTabIconsPressed[2]);
                mFragmentTo = 2;
                mViewPager.setCurrentItem(2);
                break;
            case 3://"我的"
                img_title.setImageResource(mTabIconsPressed[3]);
                mViewPager.setCurrentItem(3);
                break;
            default:
                break;
        }
    }
    private void changeTabNormal(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        ImageView img_title = view.findViewById(R.id.mImgTitle);
        TextView txt_title = view.findViewById(R.id.mTxtTitle);
        txt_title.setTextColor(0xFF6a6a77);
        switch (tab.getPosition()) {
            case 0://"实时监护"
                img_title.setImageResource(mTabIcons[0]);
                break;
            case 1://"体征统计"
                img_title.setImageResource(mTabIcons[1]);
                break;
            case 2://"睡眠报告"
                img_title.setImageResource(mTabIcons[2]);
                break;
            case 3://"睡眠报告"
                img_title.setImageResource(mTabIcons[3]);
                break;
            default:

                break;
        }
    }
}
