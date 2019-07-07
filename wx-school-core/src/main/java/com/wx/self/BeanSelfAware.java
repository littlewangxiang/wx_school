package com.wx.self;

/**
 * Created by wangxiang on 2018/11/21 实现此接口的类可以得到代理类
 */
public interface BeanSelfAware {
    void setSelf(Object proxyBean);
}
