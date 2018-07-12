package com.tuya.demo.tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tuya.demomodule.R;
import com.tuya.smart.api.router.UrlRouter;

public abstract class DemoFragment extends Fragment {

    protected Button button;

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_demo, container, false);

        ((TextView) contentView.findViewById(R.id.demo_content)).setText(getContent());
        button = (Button) contentView.findViewById(R.id.demo_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlRouter.execute(getActivity(), UrlRouter.SCHEME_PREFIX + UrlRouter.MODULE_APP
                        + "=demo");
            }
        });
        return contentView;
    }

    protected abstract String getContent();
}
