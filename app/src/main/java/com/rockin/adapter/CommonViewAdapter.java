package com.rockin.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager 的适配器，不过装载的不是 Fragment，而是 ImageView 或者其他的 View
 *
 * @author asus
 * @date 2017/11/18
 */
public class CommonViewAdapter extends PagerAdapter {

    private List<View> viewList = new ArrayList<>();

    public CommonViewAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return viewList != null ? viewList.size() : 0;
    }

    /**
     * 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
     * 所以我这里注释掉super()方法,如果现实的图片超过3张,图片也不会被销毁了
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        // 正常情况,当前显示的item两边的会缓存,所以其他的将被销毁时,调用该方法,就将其移出
//        container.removeView(viewList.get(position));
    }

    /**
     * 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，
     * 我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 避免异常: The specified child already has a parent. You must call removeView() on the child's parent first）
        ViewParent parent = viewList.get(position).getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(viewList.get(position));
        }
        container.addView(viewList.get(position));
        // 图片显示的逻辑交给setAdapter去重写
        onBindView(container, viewList.get(position), position);
        return viewList.get(position);
    }

    public void onBindView(ViewGroup container, View itemView, int position) {

    }
}
