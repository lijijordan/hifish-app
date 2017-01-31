package com.hifish.app.dao.jpa;

import com.hifish.app.entity.AirDevice;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * The interface Air device repository.
 */
public interface AirDeviceRepository extends PagingAndSortingRepository<AirDevice, Long> {
    
}
