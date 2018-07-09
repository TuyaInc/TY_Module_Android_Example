package com.tuya.demo.tab;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.tuya.demomodule.R;
import com.tuya.smart.api.tab.ITabGetter;

public class TabGetterA implements ITabGetter {
    @Override
    public View getIndicatorView(Context context) {
        View view = View.inflate(context, R.layout.tab_item, null);
        TextView textView = view.findViewById(R.id.title);
        textView.setText("TABA");
        return view;
    }

    @Override
    public Fragment getFragment() {
        return new DemoTabFragmentA();
    }
}
