package com.tuya.example.appstart.redirect;

import com.tuya.smart.api.service.HomeService;

/**
 * @author huyang
 */
public class RedirectTestService extends HomeService {

    @Override
    public void register() {

    }

    @Override
    public String getHomeName() {
        return "RedirectTest";
    }

    @Override
    public void init() {

    }

    @Override
    public int update(int i, int j) {
        return 0;
    }
}
