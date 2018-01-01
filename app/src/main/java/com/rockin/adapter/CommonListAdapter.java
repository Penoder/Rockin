package com.rockin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Penoder
 * @date 2017/11/16
 */
public abstract class CommonListAdapter<T> extends BaseAdapter {

    private List<T> datas = new ArrayList<>();
    private int layoutId;

    public CommonListAdapter(List<T> datas, int layoutId) {
        this.datas = datas;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        onBindView(datas.get(position), holder, position);

        return convertView;
    }

    public abstract void onBindView(T t, ViewHolder holder, int position);

    public class ViewHolder {

        private View itemView;

        private ViewHolder(View itemView) {
            this.itemView = itemView;
        }

        public View getItemView() {
            return itemView;
        }

        public <T extends View> T getView(int viewId) {
            return (T) itemView.findViewById(viewId);
        }
    }
}
