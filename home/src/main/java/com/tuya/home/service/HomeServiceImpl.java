package com.tuya.home.service;

import com.tuya.smart.api.service.HomeService;
import com.tuya.smart.tymodule_annotation.TYService;

@TYService("com.tuya.smart.api.service.HomeService")
public class HomeServiceImpl extends HomeService {

    @Override
    public void init() {

    }

    @Override
    public int update(int i, int j) {
        return 0;
    }

    @Override
    public void register() {

    }

    @Override
    public String getHomeName() {
        return "HomeServiceImpl";
    }
}
