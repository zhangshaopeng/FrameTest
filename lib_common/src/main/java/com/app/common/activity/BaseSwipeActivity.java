package com.app.common.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.app.common.R;
import com.app.common.view.RecyclerViewEmptySupport;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * p>Describe:
 * p>Company:Dyne
 * p>@Author:zsp
 * p>Data:2019/2/11.
 */
public abstract class BaseSwipeActivity extends BaseTitleActivity {
    protected  int nextPage;

    protected BGARefreshLayout refreshLayout;
    protected RecyclerViewEmptySupport recyclerView;

    private void assignViews() {
        refreshLayout = (BGARefreshLayout) findViewById(R.id.refresh_layout);
        recyclerView = (RecyclerViewEmptySupport) findViewById(R.id.recycler_view);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_swipe);
        assignViews();

        initRecyclerView();
        initBGARefreshLayout();
        showLoadingView();
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        recyclerView.setLayoutManager(layoutManager);

        if (getDividerItem() != null) {
            recyclerView.addItemDecoration(getDividerItem());
        }

        //设置RecyclerView的空view
        View emptyView = getEmptyView();
        ((ViewGroup) recyclerView.getParent()).addView(emptyView);
        recyclerView.setEmptyView(emptyView);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.Adapter adapter = getAdapter();
        recyclerView.setAdapter(adapter);
    }

    /**
     * 默认线性
     *
     * @return 默认线性
     */
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    /**
     * @return item分割线
     */
    protected RecyclerView.ItemDecoration getDividerItem() {
        return null;
    }


    /**
     * @return recyclerView的空view
     */
    protected View getEmptyView() {
        return tipsViewFactory.getEmptyView();
    }

    /**
     * 设置recyclerView的适配器
     *
     * @return recyclerView的适配器
     */
    protected abstract RecyclerView.Adapter getAdapter();

    private void initBGARefreshLayout() {
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        //设置标准的下拉刷新（新浪微博风格）
        refreshLayout.setRefreshViewHolder(refreshViewHolder);
        refreshLayout.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                onPullDownToRefresh();
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (hasMore()) {
                    onPullUpToLoadMore();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    /**
     * @param pullDownRefreshEnable 设置下拉刷新可用
     */
    protected void setPullDownRefreshEnable(boolean pullDownRefreshEnable) {
        refreshLayout.setPullDownRefreshEnable(pullDownRefreshEnable);
    }

    /**
     * 是否有更多数据，返回值将决定是否显示上啦加载的view 和 接受回调
     *
     * @return
     */
    protected abstract boolean hasMore();

    /**
     * 开始下拉刷新
     */
    protected void beginPullDownToRefresh() {
        refreshLayout.beginRefreshing();
    }


    /**
     * 结束下拉刷新
     */
    protected void endPullDownToRefresh() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.endRefreshing();
            }
        }, 1500);
    }

    /**
     * 通过代码方式控制进入加载更多状态
     */
    protected void beginPullUpToLoadMore() {
        refreshLayout.beginLoadingMore();
    }

    /**
     * 结束上啦加载更多
     */
    protected void endPullUpToLoadMore() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.endLoadingMore();
            }
        }, 1500);
    }

    /**
     * 下拉刷新
     */
    protected abstract void onPullDownToRefresh();

    /**
     * 上啦加载更多
     */
    protected abstract void onPullUpToLoadMore();

    @Override
    protected void netErrorViewClick() {
        super.netErrorViewClick();
        onPullDownToRefresh();
    }


}
