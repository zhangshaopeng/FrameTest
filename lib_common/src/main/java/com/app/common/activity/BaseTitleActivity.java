package com.app.common.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.app.common.R;
import com.app.common.view.TipsViewFactory;
import com.app.common.view.TitleBarView;


/**
 * p>Describe:
 * p>Company:Dyne
 * p>@Author:zsp
 * p>Data:2019/2/11.
 */
public  class BaseTitleActivity extends BaseActivity {
    protected TipsViewFactory tipsViewFactory;

    protected TitleBarView titleBarView;
    protected FrameLayout flChildContent;
    protected FrameLayout flTipsContainer;

    private void assignViews() {
        titleBarView = (TitleBarView) findViewById(R.id.title_bar_view);
        flChildContent = (FrameLayout) findViewById(R.id.fl_child_content);
        flTipsContainer = (FrameLayout) findViewById(R.id.fl_tips_container);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base_title_bar);
        tipsViewFactory = new TipsViewFactory(this);
        assignViews();

        if (showTitleBar()) {
            setTranslucentStatus(titleBarView);
        } else {
            titleBarView.setVisibility(View.GONE);
        }
    }

    protected void setTitleText(String title) {
        titleBarView.setTitleText(title);
    }

    protected void setTitleText(@StringRes int title) {
        titleBarView.setTitleText(title);
    }

    protected void setRightText(String str) {
        titleBarView.setRightText(str);
    }

    protected TextView getRightText() {
        return titleBarView.getTvRight();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, null);
        this.setContentView(view);
    }

    @Override
    public void setContentView(View view) {
        flChildContent.removeAllViews();
        flChildContent.addView(view);
    }

    /**
     * 是否显示titleBar
     *
     * @return
     */
    protected boolean showTitleBar() {
        return true;
    }


    /**
     * 显示正在加载数据的view
     */
    protected void showLoadingView() {
        flTipsContainer.setClickable(true);
        flTipsContainer.removeAllViews();
        flTipsContainer.addView(tipsViewFactory.getLoadingView());
    }

    /**
     * 显示网络错误的view
     */
    protected void showNetErrorView() {
        flTipsContainer.setClickable(true);
        flTipsContainer.removeAllViews();
        flTipsContainer.addView(tipsViewFactory.getNetErrorView());
        tipsViewFactory.getNetErrorView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                netErrorViewClick();
            }
        });
    }

    /**
     * 显示主要的view
     */
    protected void showChildView() {
        flTipsContainer.setClickable(false);
        flTipsContainer.removeAllViews();
    }


    /**
     * 网络错误的提示view的点击后的执行逻辑，
     */
    protected void netErrorViewClick() {
        showLoadingView();
    }

}
