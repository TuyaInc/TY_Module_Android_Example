package com.tuya.demo.home.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tuya.demo.home.R;
import com.tuya.smart.api.logger.LogUtil;
import com.tuya.smart.api.tab.BaseTabFragment;

public class HomeFragment extends BaseTabFragment {

    protected void onButtonClick() {
        startActivity(new Intent(this.getContext(), FamilyActivity.class));
    }

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.ex_fragment_home, container, false);

        Button button = (Button) contentView.findViewById(R.id.demo_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick();
            }
        });
        return contentView;
    }

    public void onEnter() {
        LogUtil.d("HomeTabGetter", "onEnter");
    }

    public void onLeave() {
        LogUtil.d("HomeTabGetter", "onLeave");
    }
}
