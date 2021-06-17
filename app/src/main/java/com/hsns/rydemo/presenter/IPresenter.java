package com.hsns.rydemo.presenter;

import com.hsns.rydemo.callback.DataListener;

public interface IPresenter {
    /**
     * 初始化数据接口
     */
    void initData(DataListener l);
}
