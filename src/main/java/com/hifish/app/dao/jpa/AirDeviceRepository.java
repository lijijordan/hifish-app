package com.hifish.app.dao.jpa;

import com.hifish.app.entity.AirDeviceData;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * The interface Air device repository.
 */
public interface AirDeviceRepository extends PagingAndSortingRepository<AirDeviceData, Long> {

    /**
     * Find top by open id order by create time desc air device data.
     *
     * @param openId the open id
     * @return the air device data
     */
    AirDeviceData findTopByDeviceIdOrderByCreateTimeDesc(String openId);
}
