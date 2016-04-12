package com.qual1ty.yashi_git;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.ViewConfiguration;

import com.qual1ty.yashi_git.fragment.TabFragment;
import com.qual1ty.yashi_git.widget.ChangeColorIconWithTextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity
        implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList();
    private FragmentPagerAdapter mAdapter;
    private String[] mTitles = {"First Fragment!",
            "Second Fragment!", "Third Fragment!", "Fourth Fragment!"};

    private List<ChangeColorIconWithTextView> mTabIndicator = new ArrayList();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setOverflowShowingAlways();
        if (getActionBar() != null)
            getActionBar().setDisplayShowHomeEnabled(false);
        this.mViewPager = ((ViewPager) findViewById(R.id.id_viewpager));

        initDatas();

        this.mViewPager.setAdapter(this.mAdapter);
        this.mViewPager.setOnPageChangeListener(this);
    }

    private void initDatas() {
        for (String title : this.mTitles) {
            TabFragment tabFragment = new TabFragment();
            Bundle args = new Bundle();
            args.putString("title", title);
            tabFragment.setArguments(args);
            this.mTabs.add(tabFragment);
        }

        this.mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            public int getCount() {
                return MainActivity.this.mTabs.size();
            }

            public Fragment getItem(int arg0) {
                return (Fragment) MainActivity.this.mTabs.get(arg0);
            }
        };
        initTabIndicator();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void initTabIndicator() {
        ChangeColorIconWithTextView one = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_one);
        ChangeColorIconWithTextView two = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_two);
        ChangeColorIconWithTextView three = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_three);
        ChangeColorIconWithTextView four = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_four);

        this.mTabIndicator.add(one);
        this.mTabIndicator.add(two);
        this.mTabIndicator.add(three);
        this.mTabIndicator.add(four);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);

        one.setIconAlpha(1.0F);
    }

    public void onPageSelected(int arg0) {
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset > 0.0F) {
            ChangeColorIconWithTextView left = (ChangeColorIconWithTextView) this.mTabIndicator.get(position);
            ChangeColorIconWithTextView right = (ChangeColorIconWithTextView) this.mTabIndicator.get(position + 1);

            left.setIconAlpha(1.0F - positionOffset);
            right.setIconAlpha(positionOffset);
        }
    }

    public void onPageScrollStateChanged(int state) {
    }

    public void onClick(View v) {
        resetOtherTabs();

        switch (v.getId()) {
            case 2131230721:
                ((ChangeColorIconWithTextView) this.mTabIndicator.get(0)).setIconAlpha(1.0F);
                this.mViewPager.setCurrentItem(0, false);
                break;
            case 2131230722:
                ((ChangeColorIconWithTextView) this.mTabIndicator.get(1)).setIconAlpha(1.0F);
                this.mViewPager.setCurrentItem(1, false);
                break;
            case 2131230723:
                ((ChangeColorIconWithTextView) this.mTabIndicator.get(2)).setIconAlpha(1.0F);
                this.mViewPager.setCurrentItem(2, false);
                break;
            case 2131230724:
                ((ChangeColorIconWithTextView) this.mTabIndicator.get(3)).setIconAlpha(1.0F);
                this.mViewPager.setCurrentItem(3, false);
        }
    }

    private void resetOtherTabs() {
        for (int i = 0; i < this.mTabIndicator.size(); i++) {
            ((ChangeColorIconWithTextView) this.mTabIndicator.get(i)).setIconAlpha(0.0F);
        }
    }

    public boolean onMenuOpened(int featureId, Menu menu) {
        if ((featureId == 8) && (menu != null)) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", new Class[]{Boolean.TYPE});
                    m.setAccessible(true);
                    m.invoke(menu, new Object[]{Boolean.valueOf(true)});
                } catch (Exception localException) {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}