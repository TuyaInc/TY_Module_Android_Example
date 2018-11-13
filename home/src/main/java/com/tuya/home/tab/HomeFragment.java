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
import com.tuya.smart.api.logger.LogUtil;
import com.tuya.smart.api.router.UrlRouter;
import com.tuya.smart.api.tab.ITabChangeListener;
import com.tuya.smart.panelcaller.api.AbsPanelCallerService;
import com.tuya.smart.sdk.bean.DeviceBean;

public class HomeFragment extends BaseFragment implements ITabChangeListener{

    protected String getContent() {
        return "家庭";
    }

    protected void onButtonClick() {
        UrlRouter.execute(UrlRouter.makeBuilder(getActivity(), "example_home"));

        //go panel
//        AbsPanelCallerService absPanelCallerService = MicroContext.getServiceManager()
//                .findServiceByInterface(AbsPanelCallerService.class.getName());
//        if (absPanelCallerService != null) {
//            DeviceBean deviceBean = null;
//            absPanelCallerService.goPanel(getActivity(), deviceBean);
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

    @Override
    public void onEnter() {
        LogUtil.d("HomeFragment", "onEnter");
    }

    @Override
    public void onLeave() {
        LogUtil.d("HomeFragment", "onLeave");
    }
}
