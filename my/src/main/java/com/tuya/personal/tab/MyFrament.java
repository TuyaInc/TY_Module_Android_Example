package com.tuya.personal.tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tuya.demomodule2.R;
import com.tuya.smart.api.base.BaseFragment;
import com.tuya.smart.api.router.UrlBuilder;
import com.tuya.smart.api.router.UrlRouter;

public class MyFrament extends BaseFragment {
    protected String getContent() {
        return "我的";
    }

    protected void onButtonClick() {
        UrlRouter.execute(new UrlBuilder(getActivity(), "setting"));
    }

    protected Button button;

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.my_fragment, container, false);

        ((TextView) contentView.findViewById(R.id.content)).setText(getContent());
        button = (Button) contentView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick();

            }
        });
        return contentView;
    }
}
