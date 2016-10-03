package com.hifish.app.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 16/7/19
 * Time: 下午11:22
 * To change this template use File | Settings | File Templates.
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    @Column(insertable = true, updatable = false)
    private Date createTime;
    @Column(insertable = false, updatable = true)
    private Date updateTime;

    /**
     * Gets update time.
     *
     * @return the update time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets update time.
     *
     * @param updateTime the update time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * Gets create time.
     *
     * @return the create time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Sets create time.
     *
     * @param createTime the create time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * On create.
     */
    @PrePersist
    void onCreate() {
        this.setCreateTime(new Date());
    }

    /**
     * On update.
     */
    @PreUpdate
    void onUpdate() {
        this.setUpdateTime(new Date());
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getID() {
        return ID;
    }

    /**
     * Sets id.
     *
     * @param ID the id
     */
    public void setID(Long ID) {
        this.ID = ID;
    }
}
