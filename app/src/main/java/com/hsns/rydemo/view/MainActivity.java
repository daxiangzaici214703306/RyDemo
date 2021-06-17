package com.hsns.rydemo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hsns.rydemo.R;
import com.hsns.rydemo.adapter.NaviAdapter;
import com.hsns.rydemo.callback.DataListener;
import com.hsns.rydemo.model.ModelHomeEntrance;
import com.hsns.rydemo.presenter.RyPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NaviAdapter.OnItemViewClickListener, DataListener {
    private List<ModelHomeEntrance> homeEntrances;
    private RecyclerView mRecyclerView;
    private RyPresenter mRyPresenter;
    private static final String TAG = "RyMainActivity";
    private NaviAdapter mRecycleAdapter;
    private int height;//recyclerview的高度
    private int width;//recyclerview的宽度


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mRyPresenter = new RyPresenter();
        mRyPresenter.initData(this);
    }


    private void initView() {
        Log.d(TAG, "initView");
        homeEntrances = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        int position = Integer.parseInt(RyApplication.getApplication().getString(R.string.ry_position));
        boolean isVertical = position == 1;
        setRyParams(isVertical);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, isVertical ? RecyclerView.VERTICAL : RecyclerView.HORIZONTAL, false));
        mRecyclerView.setNestedScrollingEnabled(false);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(mRecyclerView);
        mRecycleAdapter = new NaviAdapter(this, homeEntrances);
        mRecycleAdapter.setOnItemViewClickListener(this);
        mRecyclerView.setAdapter(mRecycleAdapter);
    }

    /**
     * 设置RecyclerView的宽高
     * @param isVertical 是否是垂直方法
     */
    private void setRyParams(boolean isVertical) {
        if (isVertical) {
            height = Integer.parseInt(RyApplication.getApplication().getString(R.string.ry_item_height)) * Integer.parseInt(RyApplication.getApplication().getString(R.string.ry_page_sizes));
            mRecyclerView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height));
        } else {
            width = Integer.parseInt(RyApplication.getApplication().getString(R.string.ry_item_width)) * Integer.parseInt(RyApplication.getApplication().getString(R.string.ry_page_sizes));
            mRecyclerView.setLayoutParams(new FrameLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }


    @Override
    public void onItemViewClick(int position) {
        String content = homeEntrances.get(position).getName() + " phone:" + homeEntrances.get(position).getPhone();
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataCallback(List<ModelHomeEntrance> homeEntrances) {
        Log.d(TAG, "onDataCallback >>" + homeEntrances);
        this.homeEntrances.clear();
        this.homeEntrances.addAll(homeEntrances);
        if(mRecycleAdapter!=null)
        mRecycleAdapter.notifyDataSetChanged();
    }
}
