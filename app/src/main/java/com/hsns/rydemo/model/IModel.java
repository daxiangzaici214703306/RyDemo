package com.hsns.rydemo.model;

import com.hsns.rydemo.callback.DataListener;

public interface IModel {
    /**
     * 获取实际数据
     * @param l 数据监听
     */
    void getInitData(DataListener l);
}
