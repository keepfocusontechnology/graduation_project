package com.qual1ty.yashi_git.interactor;

import android.app.Activity;

/**
 * Created by Tianci on 16/4/13.
 */
public interface NavigationCommand {
    void navigate(Activity src, Class<?> clazz);
}
