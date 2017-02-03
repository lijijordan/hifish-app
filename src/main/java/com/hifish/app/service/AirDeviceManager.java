package com.hifish.app.service;

import com.hifish.app.dao.jpa.AirDeviceRepository;
import com.hifish.app.domain.AirDeviceDataStatistics;
import com.hifish.app.entity.AirDeviceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * The type Air device manager.
 */
@Service
public class AirDeviceManager {


    @Autowired
    private AirDeviceRepository airDeviceRepository;

    /**
     * Save air device data.
     *
     * @param data the data
     */
    public void saveAirDeviceData(AirDeviceData data) {
        this.airDeviceRepository.save(data);
    }

    /**
     * Query by Day.
     *
     * @param start the start
     * @param end   the end
     * @return the list
     */
    public List<AirDeviceDataStatistics> queryByDay(Date start, Date end, String deviceId) {
        return null;
    }


    /**
     * Query by hour.
     *
     * @param start the start
     * @param end   the end
     * @return the list
     */
    public List<AirDeviceDataStatistics> queryByHour(Date start, Date end, String deviceId) {
        return null;
    }


    /**
     * Gets current.
     *
     * @param deviceId the device id
     * @return the current
     */
    public AirDeviceData getCurrent(String deviceId) {
        return this.airDeviceRepository.findTopByDeviceIdOrderByCreateTimeDesc(deviceId);
    }

}
