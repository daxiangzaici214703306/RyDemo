package com.hsns.rydemo.presenter;

import com.hsns.rydemo.callback.DataListener;
import com.hsns.rydemo.model.RyModel;

public class RyPresenter implements IPresenter {
    private RyModel mRyModel;

    public RyPresenter() {
        mRyModel = new RyModel();
    }

    @Override
    public void initData(DataListener l) {
        mRyModel.getInitData(l);
    }
}
