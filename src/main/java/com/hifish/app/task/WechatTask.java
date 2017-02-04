package com.hifish.app.task;

import com.hifish.app.util.HttpUtils;
import com.hifish.app.util.WechatTokenHelper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 检查设备的离线状态任务
 */
@Component("task")
public class WechatTask {

    private static final Logger logger = LoggerFactory.getLogger(WechatTask.class);

    // 一个小时
    private static final long ONE_HOUR = 1000 * 60 * 60;
    private static final long ONE_MINUTE = 1000 * 60;

    /**
     * Gets token.
     *
     * @return the token
     * @throws Exception the exception
     */
    public static String getToken() throws Exception {
        Map<String, String> params = new HashMap();
        params.put("grant_type", "client_credential");
        params.put("appid", WechatTokenHelper.APP_ID);
        params.put("secret", WechatTokenHelper.APP_SECRET);
        String jstoken = HttpUtils.sendGet(WechatTokenHelper.TOKEN_URL, params);
        logger.info("获取Basic Token response:{}", jstoken);
        JSONObject jsonObject = new JSONObject(jstoken);
        String access_token = (String) jsonObject.get("access_token");
//        GlobalConstants.interfaceUrlProperties.put("access_token", access_token);
        WechatTokenHelper.setToken(access_token);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "token 为==============================" + access_token);
        return access_token;
    }

    /**
     * 60分钟执行一次
     *
     * @throws Exception the exception
     */
    @Scheduled(fixedRate = ONE_HOUR)
    public void run() throws Exception {
        getToken();
    }


}