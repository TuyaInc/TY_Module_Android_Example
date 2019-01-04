package com.tuya.demo.common;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.tuya.demo.common.util.ProgressUtil;

/**
 * BaseActivity with Toolbar
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static final int TOOLBAR_TEXT_COLOR = 0xff303030;
    protected Toolbar mToolBar;
    protected int mTitleBarColor = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setToolBarColor(int titleColor) {
        mTitleBarColor = titleColor;
        TextView tvTitle = (TextView) mToolBar.findViewById(R.id.toolbar_title);
        if (tvTitle != null) {
            tvTitle.setTextColor(mTitleBarColor);
        } else {
            mToolBar.setTitleTextColor(titleColor);
        }
    }

    protected void initToolbar() {
        if (mToolBar == null) {
            mToolBar = (Toolbar) findViewById(R.id.toolbar_top_view);
            if (mToolBar != null) {
                setToolBarColor(TOOLBAR_TEXT_COLOR);
            }
        }
    }

    protected void hideTitleBarLine() {
        View line = findViewById(R.id.v_title_down_line);
        if (line != null) {
            line.setVisibility(View.GONE);
        }
    }

    public Toolbar getToolBar() {
        return mToolBar;
    }

    protected void setTitle(String title) {
        if (mToolBar != null) {
            TextView tvTitle = (TextView) mToolBar.findViewById(R.id.toolbar_title);
            if (tvTitle != null) {
                tvTitle.setText(title);
            } else {
                mToolBar.setTitle(title);
            }
        }
    }

    @Override
    public void setTitle(int titleId) {
        if (mToolBar != null) {
            TextView tvTitle = (TextView) mToolBar.findViewById(R.id.toolbar_title);
            if (tvTitle != null) {
                tvTitle.setText(titleId);
            } else {
                mToolBar.setTitle(titleId);
            }
        }
    }

    protected void setSubTitle(String title) {
        if (mToolBar != null) {
            mToolBar.setSubtitle(title);
        }
    }

    protected void setLogo(Drawable logo) {
        if (mToolBar != null) {
            mToolBar.setLogo(logo);
        }
    }

    protected void setNavigationIcon(Drawable logo) {
        if (mToolBar != null) {
            mToolBar.setNavigationIcon(logo);
        }
    }

    protected void setMenu(int resId, Toolbar.OnMenuItemClickListener listener) {
        if (mToolBar != null) {
            mToolBar.inflateMenu(resId);
            mToolBar.setOnMenuItemClickListener(listener);
        }
    }

    protected void setDisplayHomeAsUpEnabled(int iconResId, final View.OnClickListener listener) {
        if (mToolBar != null) {
            mToolBar.setNavigationIcon(iconResId);
            if (listener != null) {
                mToolBar.setNavigationOnClickListener(listener);
            } else {
                mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
        }
    }

    protected void setDisplayHomeAsUpEnabled() {
        if (Color.WHITE == mTitleBarColor) {
            setDisplayHomeAsUpEnabled(R.drawable.ex_tesmart_back_white, null);
        } else {
            setDisplayHomeAsUpEnabled(R.drawable.ex_tesmart_back, null);
        }
    }

    protected void setDisplayHomeAsUpEnabled(final View.OnClickListener listener) {
        if (Color.WHITE == mTitleBarColor) {
            setDisplayHomeAsUpEnabled(R.drawable.ex_tesmart_back_white, listener);
        } else {
            setDisplayHomeAsUpEnabled(R.drawable.ex_tesmart_back, listener);
        }
    }

    protected void hideToolBarView() {
        if (mToolBar != null) {
            mToolBar.setVisibility(View.GONE);
        }
    }

    protected void showToolBarView() {
        if (mToolBar != null) {
            mToolBar.setVisibility(View.VISIBLE);
        }
    }

    protected void showLoading(String msg) {
        ProgressUtil.showLoading(this, msg);
    }

    protected void hideLoading() {
        ProgressUtil.hideLoading();
    }
}
