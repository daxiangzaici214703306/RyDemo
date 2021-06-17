package com.hsns.rydemo.model;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hsns.rydemo.R;
import com.hsns.rydemo.callback.DataListener;
import com.hsns.rydemo.manager.ExecutorsThreadManager;
import com.hsns.rydemo.view.RyApplication;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RyModel implements IModel {
    private List<ModelHomeEntrance> homeEntrances;
    private DataListener mDataListener;

    @Override
    public void getInitData(DataListener l) {
        mDataListener = l;
        homeEntrances = new ArrayList<>();
        getRealData();
    }

    /**
     * 获取真正的数据
     */
    private void getRealData() {
        ExecutorsThreadManager.getInstance().submitThread(new Runnable() {
            @Override
            public void run() {
                String jsonResult = RyApplication.getApplication().getString(R.string.ry_data_content);
                Type type = new TypeToken<List<ModelHomeEntrance>>() {}.getType();
                homeEntrances = new Gson().fromJson(jsonResult, type);
                Message msg=Message.obtain();
                msg.obj=homeEntrances;
                mHandler.sendMessage(msg);
            }
        });
    }


    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            mDataListener.onDataCallback((List<ModelHomeEntrance>)msg.obj);
        }
    };
}
