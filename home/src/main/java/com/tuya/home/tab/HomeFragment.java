package com.tuya.home.tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tuya.demomodule.R;
import com.tuya.smart.api.MicroContext;
import com.tuya.smart.api.base.BaseFragment;
import com.tuya.smart.api.router.UrlRouter;
import com.tuya.smart.api.service.MicroService;
import com.tuya.smart.homepage.api.AbstractHomeService;
import com.tuya.smart.sdk.bean.DeviceBean;

public class HomeFragment extends BaseFragment {

    protected String getContent() {
        return "家庭";
    }

    protected void onButtonClick() {
        UrlRouter.execute(UrlRouter.makeBuilder(getActivity(), "home"));

        //go panel
//        AbstractHomeService abstractHomeService = MicroContext.getServiceManager()
//                .findServiceByInterface(AbstractHomeService.class.getName());
//        if (abstractHomeService != null) {
//            DeviceBean deviceBean = null;
//            abstractHomeService.goPanel(getActivity(), deviceBean);
//        }
    }


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
                onButtonClick();

            }
        });
        return contentView;
    }
}
