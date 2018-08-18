package com.tuya.smart.api.service;


public abstract class HomeService extends MicroService {

    public abstract void init();

    public abstract int update(int i, int j);

    public abstract void register();

    public abstract String getHomeName();
}
