package com.hifish.app.dao.jpa;

import com.hifish.app.entity.Device;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * The interface User repository.
 */
public interface DeviceRepository extends PagingAndSortingRepository<Device, Long> {

    /**
     * Find by device id user info.
     *
     * @param deviceId the device id
     * @return the user info
     */
    Device findByDeviceId(String deviceId);
}