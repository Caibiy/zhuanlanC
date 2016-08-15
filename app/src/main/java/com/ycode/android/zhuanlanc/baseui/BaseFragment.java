package com.ycode.android.zhuanlanc.baseui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycode.android.zhuanlanc.R;

/**
 * Author:    yangjiadong
 * Time :     2016/8/15
 * Email:      caibiy666@gmail.com
 */
public class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.error_frag,null);
        return view;
    }
}
