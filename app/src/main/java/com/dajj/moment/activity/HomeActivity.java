package com.dajj.moment.activity;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.dajj.moment.DataGenerator;
import com.dajj.moment.R;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends BaseActvity {
    private TabLayout mTabLayout;
    private Fragment []mFragmensts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mFragmensts = DataGenerator.getFragments("TabLayout Tab");
        initView();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.bottom_tab_layout);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());

                //改变Tab 状态
//                for(int i=0;i< mTabLayout.getTabCount();i++){
//                    if(i == tab.getPosition()){
//                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(DataGenerator.mTabResPressed[i]));
//                    }else{
//                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(DataGenerator.mTabRes[i]));
//                    }
//                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_recommend_selector)).setText(DataGenerator.mTabTitle[0]));
//        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_discover_selector)).setText(DataGenerator.mTabTitle[1]));
//        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_message_selector)).setText(DataGenerator.mTabTitle[2]));
//        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_mine_selector)).setText(DataGenerator.mTabTitle[3]));

        // 提供自定义的布局添加Tab
        for(int i=0;i<4;i++){
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(DataGenerator.getTabView(this,i)));
        }
    }

    private void onTabItemSelected(int position){

        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = mFragmensts[0];
                break;
            case 1:
                fragment = mFragmensts[1];
                break;

            case 2:
                fragment = mFragmensts[2];
                break;
            case 3:
                fragment = mFragmensts[3];
                break;
        }
        if(fragment!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
        }
    }


}