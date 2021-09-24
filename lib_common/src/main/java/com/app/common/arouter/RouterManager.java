package com.app.common.arouter;

import android.app.Application;
import android.util.ArrayMap;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Description:路由管理者 first you need init,use method :{@link #initARouter(boolean, Application)}
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/6/19
 */
public class RouterManager {

    private static ArrayMap<String, String> map = new ArrayMap<>();

    public static void initARouter(boolean debug, Application application) {

        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (debug) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(application);

        NewUriIntentService.startService(application);

    }


    public synchronized static ArrayMap<String, String> getNewUriMap() {
        return map;

    }


    /**
     * 通过uri 得到对应的映射对象
     *
     * @param uri
     * @return
     */
    public static Class<?> getTargetClass(String uri) {
        if (map.get(uri) != null) {
            uri = map.get(uri);
        }

        Postcard postcard = ARouter.getInstance().build(uri);
        LogisticsCenter.completion(postcard);
        return postcard.getDestination();
    }
}
