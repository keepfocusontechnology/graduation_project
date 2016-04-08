package com.qual1ty.yashi_git.utils;

import com.qual1ty.yashi_git.activity.BaseActivity;

import java.util.LinkedList;

/**
 * Created by Tianci on 16/4/8.
 */
public class ActivityManager {


    private LinkedList<BaseActivity> list;
    private static ActivityManager manager;

    private ActivityManager() {
        list = new LinkedList<BaseActivity>();
    }


    public static ActivityManager getInstance() {
        if (manager == null) {
            synchronized (ActivityManager.class) {
                if (manager == null)
                    manager = new ActivityManager();

            }
        }
        return manager;
    }

    public void add(BaseActivity t) {
        list.add(t);
    }

    public void add(BaseActivity t, int index) {
        list.add(index, t);
    }

    public void removeAll() {
        for (BaseActivity activity :
                list) {
            if (activity != null)
                activity.finish();
        }
        list.clear();
    }

    public void remove(int index) {
        if (list.get(index) != null)
            list.get(index).finish();
        list.remove(index);
    }

    public void remove(BaseActivity t) {
        if (t != null)
            t.finish();
        list.remove(t);
    }


}
