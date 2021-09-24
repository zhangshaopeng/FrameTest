package com.app.common.frament;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.app.common.R;
import com.app.common.view.RecyclerViewEmptySupport;
import com.app.common.view.TipsViewFactory;
import com.trello.rxlifecycle2.components.support.RxFragment;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


public abstract class BaseFragment extends RxFragment {

    protected Activity activity;
    protected FrameLayout flContainer;
    /**
     * 是否已经初始化过数据
     */
    private boolean isInitializedData;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (flContainer == null) {
            flContainer = (FrameLayout) inflater.inflate(R.layout.fragment_base, container, false);
            View childView = View.inflate(activity, getLayoutId(), null);
            flContainer.addView(childView);
            onViewFinished(childView);
        } else {
            ViewParent viewParent = flContainer.getParent();
            if (viewParent != null && viewParent instanceof ViewGroup) {
                ((ViewGroup) viewParent).removeView(flContainer);
            }
        }
        return flContainer;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }


    private void initData() {

        if (!isInitializedData && getUserVisibleHint() && getView() != null) {
            isInitializedData = true;
            onLoadData();
        }
    }


    protected abstract int getLayoutId();


    /**
     * 此方法在 {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} 执行时被调用
     * 可以在这里findViewById,  此方法只调用一次
     *
     * @param view 子类通过  {@link #getLayoutId()}  提供的 layout 布局文件 inflate 而成的 view
     */
    protected abstract void onViewFinished(View view);/**
     * Description:
     * Company:
     * Author:Zhangshaopeng
     * Email :1377785991@qq.com
     * Data:2018/6/19
     */

    public abstract class BaseSwipeFragment extends BaseFragment {

        protected int nextPage;

        protected TipsViewFactory tipsViewFactory;
        protected BGARefreshLayout refreshLayout;
        protected RecyclerViewEmptySupport recyclerView;
        protected FrameLayout flTipsContainer;



        @Override
        protected int getLayoutId() {
            tipsViewFactory = new TipsViewFactory(activity);
            return R.layout.fragment_base_swipe;
        }


        @Override
        protected void onViewFinished(View view) {
            assignViews(view);
            initBGARefreshLayout();
            initRecyclerView();
        }

        private void assignViews(View view) {
            refreshLayout = (BGARefreshLayout) view.findViewById(R.id.refresh_layout);
            recyclerView = (RecyclerViewEmptySupport) view.findViewById(R.id.recycler_view);
            flTipsContainer = (FrameLayout) view.findViewById(R.id.fl_tips_container);
        }

        private void initBGARefreshLayout() {
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

            BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(activity, true);
            //设置标准的下拉刷新（新浪微博风格）
            refreshLayout.setRefreshViewHolder(refreshViewHolder);

        }


        protected void initRecyclerView() {
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
            return new LinearLayoutManager(activity);
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
            }, 1000);
        }

        /**
         * 下拉刷新
         */
        protected abstract void onPullDownToRefresh();

        /**
         * 上啦加载更多
         */
        protected abstract void onPullUpToLoadMore();


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
            onPullDownToRefresh();
        }


    }


    /**
     * 当此fragment 第一次完成初始化 并真正显示出来的时候执行，
     * 可以在这里请求数据并加载数据到控件
     * 此方法只会执行一次
     */
    protected abstract void onLoadData();

}