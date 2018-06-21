package com.tuya.smart.demoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by letian on 2018/6/19.
 */

public class DemoFragment extends Fragment {
    public static synchronized Fragment newInstance() {
        return new DemoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.demo_layout, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
