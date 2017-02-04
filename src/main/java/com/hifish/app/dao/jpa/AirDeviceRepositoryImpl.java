package com.hifish.app.dao.jpa;

import com.hifish.app.domain.AirDeviceDataStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The interface Air device repository.
 */
public class AirDeviceRepositoryImpl {


    private static final Logger logger = LoggerFactory.getLogger(AirDeviceRepositoryImpl.class);
    @PersistenceContext
    private EntityManager em;

    /**
     * Find by open id count by hour list.
     *
     * @param deviceId  the open id
     * @param startTime the start time
     * @param endTime   the end time
     * @return the list
     */
    public List<AirDeviceDataStatistics> customCountByHour(String deviceId, Date startTime, Date endTime) {
        String sql = "SELECT AVG (a.pm2_5) as pm2_5Avg, MAX(a.pm2_5) as pm2_5Max, MIN(a.pm2_5) as pm2_5Min, " +
                "MAX (a.deviceId) as deviceId, MAX(HOUR(a.createTime)) as createTime FROM AirDeviceData a " +
                "WHERE a.deviceId=?1 and a.createTime >= ?2 and a.createTime < ?3 " +
                "group by HOUR(a.createTime)";
        return this.countBy(sql, deviceId, startTime, endTime);
    }

    /**
     * Custom count by day list.
     *
     * @param deviceId  the device id
     * @param startTime the start time
     * @param endTime   the end time
     * @return the list
     */
    public List<AirDeviceDataStatistics> customCountByDay(String deviceId, Date startTime, Date endTime) {
        String sql = "SELECT AVG (a.pm2_5) as pm2_5Avg, MAX(a.pm2_5) as pm2_5Max, MIN(a.pm2_5) as pm2_5Min, " +
                "MAX (a.deviceId) as deviceId, MAX(DAY(a.createTime)) as createTime FROM AirDeviceData a " +
                "WHERE a.deviceId=?1 and a.createTime >= ?2 and a.createTime < ?3 " +
                "group by DAY(a.createTime)";
        return this.countBy(sql, deviceId, startTime, endTime);
    }

    private List<AirDeviceDataStatistics> countBy(String sql, String deviceId, Date startTime, Date endTime) {
        javax.persistence.Query countQuery = em.createQuery(sql);
        countQuery.setParameter(1, deviceId);
        countQuery.setParameter(2, startTime);
        countQuery.setParameter(3, endTime);
        List<Object[]> rows = countQuery.getResultList();
        List<AirDeviceDataStatistics> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            AirDeviceDataStatistics airDeviceDataStatistics = new AirDeviceDataStatistics();
            airDeviceDataStatistics.setPm2_5Avg(((Double) row[0]).intValue());
            airDeviceDataStatistics.setPm2_5Max((Integer) row[1]);
            airDeviceDataStatistics.setPm2_5Min((Integer) row[2]);
            airDeviceDataStatistics.setDeviceId((String) row[3]);
            airDeviceDataStatistics.setCreateTime(String.valueOf(row[4]));
            result.add(airDeviceDataStatistics);
        }
        return result;
    }


}
