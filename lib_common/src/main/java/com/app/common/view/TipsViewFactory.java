package com.app.common.view;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

import com.app.common.R;


/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/6/19
 */
public class TipsViewFactory {
    private Context context;
    private View loadingView;
    private View emptyView;
    private View netErrorView;


    public TipsViewFactory(@NonNull Context context) {
        this(context, R.layout.view_loading_data, R.layout.view_empty_data, R.layout.view_net_error);
    }

    public TipsViewFactory(@NonNull Context context, @LayoutRes int loadingLayout, @LayoutRes int emptyLayout, @LayoutRes int errorLayout) {
        this.context = context;
        loadingView = View.inflate(context, loadingLayout, null);
        emptyView = View.inflate(context, emptyLayout, null);
        netErrorView = View.inflate(context, errorLayout, null);
    }


    public void setLoadingView(@LayoutRes int loadingLayout) {
        loadingView = View.inflate(context, loadingLayout, null);
    }

    public void setEmptyView(@LayoutRes int emptyLayout) {
        emptyView = View.inflate(context, emptyLayout, null);
    }

    public void setNetErrorView(@LayoutRes int errorLayout) {
        netErrorView = View.inflate(context, errorLayout, null);
    }


    public void setLoadingView(View loadingView) {
        this.loadingView = loadingView;
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }

    public void setNetErrorView(View netErrorView) {
        this.netErrorView = netErrorView;
    }

    public View getLoadingView() {
        return loadingView;
    }

    public View getEmptyView() {
        return emptyView;
    }

    public View getNetErrorView() {
        return netErrorView;
    }
}
