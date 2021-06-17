package com.hsns.rydemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.hsns.rydemo.R;
import com.hsns.rydemo.model.ModelHomeEntrance;
import com.hsns.rydemo.view.RyApplication;

import java.util.List;

public class NaviAdapter extends RecyclerView.Adapter<NaviAdapter.EntranceViewHolder> {

    //加载layout的对象
    private final LayoutInflater mLayoutInflater;
    //数据对象
    private List<ModelHomeEntrance> homeEntrances;
    //首页菜单单页显示数量
    public int mPageSize = 4;
    //总共的显示子item数目
    private int totalSize;
    //加载的方向
    private int position;
    //实际上的总个数
    private int realTotalSize;
    //宽度
    private int width;
    //高度
    private int height;
    //子Item宽度
    private int itemWidth;
    //子Item高度
    private int itemHeight;
    //序号是否循环
    private boolean isSerialNumLoop;


    public NaviAdapter(Context context, List<ModelHomeEntrance> datas) {
        this.homeEntrances = datas;
        mLayoutInflater = LayoutInflater.from(context);
        mPageSize = Integer.parseInt(RyApplication.getApplication().getString(R.string.ry_page_sizes));
        totalSize = Integer.parseInt(RyApplication.getApplication().getString(R.string.ry_total_sizes));
        position = Integer.parseInt(RyApplication.getApplication().getString(R.string.ry_position));
        width = Integer.parseInt(RyApplication.getApplication().getString(R.string.ry_item_width));
        height = Integer.parseInt(RyApplication.getApplication().getString(R.string.ry_item_height));
        isSerialNumLoop = Integer.parseInt(RyApplication.getApplication().getString(R.string.ry_serial_num_loop)) == 1;
    }


    @Override
    public EntranceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout linearLayout = new LinearLayout(parent.getContext());
        setLinearParams(linearLayout);
        addLinearChildView(linearLayout);
        return new EntranceViewHolder(linearLayout);
    }

    /**
     * 添加子view
     * @param linearLayout 根布局
     */
    private void addLinearChildView(LinearLayout linearLayout) {
        for (int i = 0; i < mPageSize; i++) {
            View view = mLayoutInflater.inflate(R.layout.item_navi, null);
            itemWidth = Integer.parseInt(RyApplication.getApplication().getString(R.string.ry_item_width));
            itemHeight = Integer.parseInt(RyApplication.getApplication().getString(R.string.ry_item_height));
            view.setLayoutParams(new ConstraintLayout.LayoutParams(itemWidth, itemHeight));
            view.setTag(i);
            linearLayout.addView(view);
        }
    }


    /**
     *设置根布局的宽高
     * @param linearLayout 根布局
     */
    private void setLinearParams(LinearLayout linearLayout){
        boolean isVertical = position == 1;
        if (isVertical) {
            height = Integer.parseInt(RyApplication.getApplication().getString(R.string.ry_item_height)) * Integer.parseInt(RyApplication.getApplication().getString(R.string.ry_page_sizes));
        } else {
            width = Integer.parseInt(RyApplication.getApplication().getString(R.string.ry_item_width)) * Integer.parseInt(RyApplication.getApplication().getString(R.string.ry_page_sizes));
        }
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        linearLayout.setOrientation(isVertical ? LinearLayout.VERTICAL : LinearLayout.HORIZONTAL);
    }

    @Override
    public void onBindViewHolder(EntranceViewHolder holder, final int position) {
        int itemViewSize = holder.layout.getChildCount();
        for (int i = 0; i < itemViewSize; i++) {
            ConstraintLayout mConstraintLayout = (ConstraintLayout) holder.layout.getChildAt(i);
            setText(position * mPageSize + i, mConstraintLayout, i);
        }
    }


    @Override
    public int getItemCount() {
        if (homeEntrances != null && homeEntrances.size() != 0) {
            int homeEntrancesSize = homeEntrances.size();
            realTotalSize = homeEntrancesSize > totalSize ? totalSize : homeEntrancesSize;
            int size = realTotalSize / mPageSize;
            if (realTotalSize > size * mPageSize) {
                return size + 1;
            } else {
                return size;
            }
        } else {
            return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class EntranceViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;

        public EntranceViewHolder(LinearLayout layout) {
            super(layout);
            this.layout = layout;
        }
    }

    private OnItemViewClickListener mOnItemViewClickListener;

    public interface OnItemViewClickListener {
        void onItemViewClick(int position);
    }

    public void setOnItemViewClickListener(OnItemViewClickListener mOnItemViewClickListener) {
        this.mOnItemViewClickListener = mOnItemViewClickListener;
    }

    /**
     * 设置子item textview的值
     * @param position 位置
     * @param mConstraintLayout 子item布局
     * @param index 子item下标值
     */
    private void setText(int position, ConstraintLayout mConstraintLayout, int index) {
        if (position < realTotalSize && position > -1) {
            TextView textView = (TextView) mConstraintLayout.findViewById(R.id.tv_name);
            TextView textView1 = (TextView) mConstraintLayout.findViewById(R.id.tv_index);
            TextView textView2 = (TextView) mConstraintLayout.findViewById(R.id.tv_value);
            textView.setText(homeEntrances.get(position).getValue(textView.getContentDescription().toString()));
            textView2.setText(homeEntrances.get(position).getValue(textView2.getContentDescription().toString()));
            setIndexValue(textView1,position,index);
            setItemListener(mConstraintLayout,position);
        } else {
            mConstraintLayout.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 设置子item下标
     * @param textView1 下标view
     * @param position 位置
     * @param index  值
     */
    private void setIndexValue(TextView textView1,int position, int index) {
        if (isSerialNumLoop) {
            textView1.setText(index + 1 + "");
        } else {
            textView1.setText(position + 1 + "");
        }
    }

    /**
     * 设置子item的监听
     * @param mConstraintLayout 子item布局
     * @param position 位置
     */
    private void setItemListener(ConstraintLayout mConstraintLayout,final int position) {
        mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("daxiang", "pos==>" + position);
                if (mOnItemViewClickListener != null) {
                    mOnItemViewClickListener.onItemViewClick(position);
                }
            }
        });
    }


}
