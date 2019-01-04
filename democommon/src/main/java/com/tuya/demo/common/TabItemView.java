package com.tuya.demo.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Common tab item view
 */
public class TabItemView extends FrameLayout {

    protected ImageView iconView;
    protected TextView titleView;
    protected View redPointView;
    protected int selectedColor;
    protected int normalColor;

    public TabItemView(Context context) {
        super(context);
        init(context);
    }

    public TabItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TabItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TabItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    protected void init(Context context) {
        View.inflate(context, getLayoutId(), this);
        iconView = (ImageView) findViewById(R.id.icon);
        titleView = (TextView) findViewById(R.id.title);
        redPointView = findViewById(R.id.red_point);
        normalColor = getResources().getColor(R.color.ex_common_tab_normal);
        selectedColor = getResources().getColor(R.color.ex_common_tab_selected);
    }

    protected int getLayoutId() {
        return R.layout.ex_tab_item;
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        iconView.setColorFilter(selected ? selectedColor : normalColor);
    }

    public void setIconImageResource(int resId) {
        iconView.setImageResource(resId);
    }

    public void setIconDrawable(Drawable drawable) {
        iconView.setImageDrawable(drawable);
    }

    public void setTitle(String title) {
        titleView.setText(title);
    }

    public TextView getTitleView() {
        return titleView;
    }

    public ImageView getIconView() {
        return iconView;
    }

    public View getRedPointView() {
        return this.redPointView;
    }

    public void setRedPointViewVisible(boolean visible) {
        redPointView.setVisibility(visible ? VISIBLE : GONE);
    }

}