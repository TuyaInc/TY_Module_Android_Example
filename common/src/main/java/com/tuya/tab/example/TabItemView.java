package com.tuya.tab.example;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuya.smart.common.R;

public class TabItemView extends LinearLayout {

    protected ImageView iconView;
    protected TextView titleView;
    protected int normalColor = 0xffA2A3AA;
    protected int selectedColor = 0xffff3b20;
    protected boolean useCustomIconColor = false;

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
        setOrientation(VERTICAL);
        View.inflate(context, getLayoutId(), this);
        iconView = (ImageView) findViewById(R.id.icon);
        titleView = (TextView) findViewById(R.id.title);
        iconView.setColorFilter(normalColor);
        titleView.setTextColor(normalColor);
    }

    protected int getLayoutId() {
        return R.layout.ty_tab_item;
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            iconView.setColorFilter(selectedColor);
            titleView.setTextColor(selectedColor);
        } else {
            iconView.setColorFilter(normalColor);
            titleView.setTextColor(normalColor);
        }
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

    public void setColor(int normalColor, int selectedColor) {
        this.normalColor = normalColor;
        this.selectedColor = selectedColor;
    }
}
