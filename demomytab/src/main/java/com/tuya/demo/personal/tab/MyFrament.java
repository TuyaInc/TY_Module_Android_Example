package com.tuya.demo.personal.tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tuya.demo.my.R;
import com.tuya.smart.api.router.UrlBuilder;
import com.tuya.smart.api.router.UrlRouter;
import com.tuya.smart.api.tab.BaseTabFragment;

public class MyFrament extends BaseTabFragment {

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.ex_my_fragment, container, false);

        Button button = (Button) contentView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick();

            }
        });
        ((TextView) contentView.findViewById(R.id.action_bar_layout).findViewById(
                R.id.toolbar_title)).setText(R.string.ex_my);

        return contentView;
    }


    protected void onButtonClick() {
        UrlRouter.execute(new UrlBuilder(getActivity(), "demo.personal"));
    }
}
