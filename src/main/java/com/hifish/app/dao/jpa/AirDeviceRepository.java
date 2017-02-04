package com.hifish.app.dao.jpa;

import com.hifish.app.domain.AirDeviceDataStatistics;
import com.hifish.app.entity.AirDeviceData;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

/**
 * The interface Air device repository.
 */
public interface AirDeviceRepository extends PagingAndSortingRepository<AirDeviceData, Long> {

    /**
     * Find by open id count by hour list.
     *
     * @param deviceId  the open id
     * @param startTime the start time
     * @param endTime   the end time
     * @return the list
     */
    List<AirDeviceDataStatistics> customCountByHour(String deviceId, Date startTime, Date endTime);

    /**
     * Custom count by day list.
     *
     * @param deviceId  the device id
     * @param startTime the start time
     * @param endTime   the end time
     * @return the list
     */
    List<AirDeviceDataStatistics> customCountByDay(String deviceId, Date startTime, Date endTime);


    /**
     * Find top by open id order by create time desc air device data.
     *
     * @param openId the open id
     * @return the air device data
     */
    AirDeviceData findTopByDeviceIdOrderByCreateTimeDesc(String openId);


}
