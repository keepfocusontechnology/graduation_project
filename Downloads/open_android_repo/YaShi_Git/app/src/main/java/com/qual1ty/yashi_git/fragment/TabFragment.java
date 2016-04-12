package com.qual1ty.yashi_git.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Tianci on 16/4/12.
 */
public class TabFragment extends Fragment
{
    private String mTitle = "Default";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (getArguments() != null)
        {
            this.mTitle = getArguments().getString("title");
        }

        TextView textView = new TextView(getActivity());
        textView.setTextSize(20.0F);
        textView.setBackgroundColor(Color.parseColor("#ffffffff"));
        textView.setGravity(17);
        textView.setText(this.mTitle);
        return textView;
    }
}