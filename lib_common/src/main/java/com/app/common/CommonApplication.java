package com.app.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.app.common.arouter.RouterManager;
import com.app.common.module.provider.LoginProviderManager;
import com.shao.app.UtilManager;
import com.shao.app.lib.http.manager.HttpManager;
import com.shao.app.utils.Logger;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.BuglyStrategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;


/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/6/19
 */
public class CommonApplication extends Application {
    public static boolean IS_DEBUG = BuildConfig.DEBUG;
    public static boolean IS_ENCRYPT = false;
    private final static String BUGLY_ID = "93010b2b0d";


    private static CommonApplication commonApplication;

    public static CommonApplication getInstance() {
        return commonApplication;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (isLocalApp()) {
            commonApplication = this;
            syncInit();
            asyncInit(this);
            if (IS_DEBUG) {
                registerActivityLifecycle();
            }
            RouterManager.initARouter(true, this);
//            initBugly(this);
        }
    }

    private void initHttp() {
        HttpManager.setConfigProvider(this, new HttpManager.ConfigProvider() {
            @Override
            public String getUserToken() {
                return UserInfoManager.getInstance().getUserToken();
            }

            @Override
            public boolean isDebug() {
                return IS_DEBUG;
            }

            @Override
            public boolean isEncrypt() {
                return IS_ENCRYPT;
            }
        });
    }

    /**
     * 异步初始化
     */
    private void asyncInit(final CommonApplication application) {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                //  TODO：异步初始化操作
                LoginProviderManager.getProvider().initModule(application, IS_DEBUG);
            }
        }).subscribeOn(Schedulers.newThread()).subscribe();

    }

    /**
     * 同步初始化
     */
    private void syncInit() {
        initBugly(this);
        UtilManager.init(this);
        initHttp();
        RouterManager.initARouter(IS_DEBUG, this);
    }


    protected boolean isLocalApp() {
        return getProcessName().endsWith(getPackageName());
    }


    /**
     * 获取进程号对应的进程名
     *
     * @return 进程名
     */
    protected static String getProcessName() {
        int pid = android.os.Process.myPid();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    // 注册管理
    private void registerActivityLifecycle() {

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Logger.e("onActivityCreated---" + activity.getClass().getName());
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    private void initBugly(Context context) {
//        Beta.autoInit = true;
//        Beta.initDelay = 1000L;
//        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//        Beta.showInterruptedStrategy = false;
        String packageName = context.getPackageName();
        BuglyStrategy buglyStrategy = new BuglyStrategy();
        buglyStrategy.setAppPackageName(packageName);
        buglyStrategy.setAppReportDelay(2000L);
        buglyStrategy.setEnableANRCrashMonitor(true);
        buglyStrategy.setEnableNativeCrashMonitor(true);
        buglyStrategy.setAppChannel(getChannel());
        Bugly.init(context, BUGLY_ID, !BuildConfig.RELEASE, buglyStrategy);
    }

    public String getChannel() {

        return "hjw";
    }
}
