package com.hifish.app.task;

import com.mydreamplus.smartdevice.dao.jpa.DeviceRepository;
import com.mydreamplus.smartdevice.dao.jpa.SensorDataRepository;
import com.mydreamplus.smartdevice.domain.DeviceStateEnum;
import com.mydreamplus.smartdevice.util.AuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 检查设备的离线状态任务
 */
@Component("task")
public class WechatTask {

    // 一个小时
    private static final long ONE_HOUR = 1000 * 60 * 60;
    private static final long ONE_MINUTE = 1000 * 60;

    private final Logger log = LoggerFactory.getLogger(WechatTask.class);
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private SensorDataRepository sensorDataRepository;

    @Autowired
    private AuthUtil authUtil;

    /**
     * 60分钟执行一次
     */
    @Scheduled(fixedRate = ONE_HOUR, initialDelay = ONE_HOUR)
    public void run() {
        log.info("************************ 更新设备在线状态,每小时执行 ************************");
        // 一小时前的时间
        Date date = new Date(System.currentTimeMillis() - ONE_HOUR);
        this.deviceRepository.updateOfflineState(DeviceStateEnum.OFFLINE, date);
//        this.deviceRepository.findAllByUpdateTimeLessThan(date).forEach(device -> {
//            log.info("更新设备{} {}为离线状态", device.getName(), device.getSymbol());
//            device.setDeviceState(DeviceStateEnum.OFFLINE);
//            this.deviceRepository.save(device);
//        });
    }


    /**
     * 60分钟执行一次
     */
    @Scheduled(fixedRate = ONE_HOUR, initialDelay = ONE_HOUR)
    public void clearCache() {
        log.info("************************ 清楚权限缓存,每小时执行 ************************");
        authUtil.cleanCache();
    }


    /**
     * 每天1点10分30秒触发任务
     */
    @Scheduled(cron = "30 10 15 * * ?")
    public void emptySensorData() {
        log.info("************************ 清除Sensor Data, 每天1点10分30秒触发任务 ************************");
        sensorDataRepository.deleteAll();
    }

}  