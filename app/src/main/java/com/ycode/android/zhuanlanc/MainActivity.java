package com.ycode.android.zhuanlanc;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.ycode.android.zhuanlanc.baseui.BaseActivity;
import com.ycode.android.zhuanlanc.fragment.ContentFragment;
import com.ycode.android.zhuanlanc.fragment.GirlFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity{
    @Bind(R.id.custom_Toolbar)
    Toolbar toolbar;
    @Bind(R.id.viewPager)
     ViewPager viewPager;
    @Bind(R.id.tab_layout)
     TabLayout tabLayout;
    private String[]titles={"专栏","妹子"};
    private Class[]fragments={ContentFragment.class, GirlFragment.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return (position==0)?new ContentFragment():new GirlFragment();
            }

            @Override
            public int getCount() {
                return fragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }

        });
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.selectedColor));
    }


}
