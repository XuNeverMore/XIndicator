package com.nevermore.xindicator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.nevermore.xindicator.widget.LineIndicator;
import com.nevermore.xindicator.widget.TriangleIndicator;
import com.nevermore.xindicator.widget.XIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        LineIndicator indicator = (LineIndicator) findViewById(R.id.indicator);
        TriangleIndicator triangleIndicator = (TriangleIndicator) findViewById(R.id.triangleIndicator);

        triangleIndicator.setTabViewDelegate(new XIndicator.TabViewDelegate() {
            @Override
            public View getTabView(int position) {

                return LayoutInflater.from(MainActivity.this).inflate(R.layout.item_tab,null);
            }

            @Override
            public int getTabTextViewId() {
                return R.id.tab_textView;
            }
        });
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        fragmentList = new ArrayList<>();
        fragmentList.add(TextFragment.newInstance("太阴"));
        fragmentList.add(TextFragment.newInstance("太阳"));

        TextAdapter adapter = new TextAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        indicator.setUpWithViewPager(viewPager);
        triangleIndicator.setUpWithViewPager(viewPager);

    }

    private class TextAdapter extends FragmentPagerAdapter{


        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0){
                return "太阴1";

            }else {
                return "太阴2";

            }
        }

        public TextAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
