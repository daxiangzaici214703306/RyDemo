package com.hsns.rydemo.callback;

import com.hsns.rydemo.model.ModelHomeEntrance;

import java.util.List;

public interface DataListener {
    /**
     * 初始化数据返回给view
     * @param homeEntrances 数据内容
     */
    void onDataCallback(List<ModelHomeEntrance> homeEntrances);
}
