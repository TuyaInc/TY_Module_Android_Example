package com.tuya.demo.home.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuya.demo.home.R;
import com.tuya.demo.home.tab.activity.FamilyActivity;
import com.tuya.smart.api.MicroContext;
import com.tuya.smart.api.logger.LogUtil;
import com.tuya.smart.api.router.UrlRouter;
import com.tuya.smart.panelcaller.api.AbsPanelCallerService;
import com.tuya.smart.sdk.bean.DeviceBean;

public class HomeFragment extends Fragment {

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.ex_fragment_home, container, false);

        contentView.findViewById(R.id.open_family).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeFragment.this.getContext(), FamilyActivity.class));
            }
        });

        contentView.findViewById(R.id.add_device).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlRouter.execute(HomeFragment.this.getContext(), "tuyaSmart://demo.addDevice");
            }
        });

        contentView.findViewById(R.id.open_device).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbsPanelCallerService panelCallerService = MicroContext
                        .findServiceByInterface(AbsPanelCallerService.class.getName());
                if (panelCallerService != null) {
                    //打开设备控制面板，需要传入真是设备的DeviceBean或者GroupBean
                    DeviceBean deviceBean = new DeviceBean();
                    deviceBean.devId = "xxxxxx";
                    panelCallerService.goPanel(HomeFragment.this.getActivity(), deviceBean);
                }
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
