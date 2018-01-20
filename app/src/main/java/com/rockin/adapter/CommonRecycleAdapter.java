package com.rockin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Penoder
 * @date 18-1-20.
 */
public abstract class CommonRecycleAdapter<T> extends RecyclerView.Adapter<CommonRecycleAdapter.ViewHolder> {

    private List<T> datas;

    private int layoutId;

    public CommonRecycleAdapter(List<T> datas, int layoutId) {
        this.datas = datas;
        this.layoutId = layoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, null);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(CommonRecycleAdapter.ViewHolder holder, int position) {
        onConvertView(datas.get(position), holder, position);
    }

    public abstract void onConvertView(T t, ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private View itemView;

        private ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        /**
         * 获取 Item 布局
         *
         * @return
         */
        public View getItemView() {
            return itemView;
        }

        /**
         * 获取 Item 布局中的某个控件
         *
         * @param viewId
         * @param <T>
         * @return
         */
        public <T extends View> T getView(int viewId) {
            return (T) itemView.findViewById(viewId);
        }
    }
}
