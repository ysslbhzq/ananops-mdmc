package com.ananops.provider.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "task_item")
@Data
@NoArgsConstructor
public class TaskItem {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人ID
     */
    @Column(name = "creator_id")
    private Long creatorId;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 最近操作人
     */
    @Column(name = "last_operator")
    private String lastOperator;

    /**
     * 最后操作人ID
     */
    @Column(name = "last_operator_id")
    private Long lastOperatorId;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 任务ID
     */
    @Column(name = "task_id")
    private Long taskId;

    /**
     * 设备ID
     */
    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "device_name")
    private String deviceName;

    /**
     * 完成维修的时间戳
     */
    @Column(name = "actual_finish_time")
    private Date actualFinishTime;

    /**
     * 开始维修的时间戳
     */
    @Column(name = "actual_start_time")
    private Date actualStartTime;

    /**
     * 故障描述
     */
    private String description;

    /**
     * 故障等级
     */
    private Integer level;

    /**
     * 故障设备位置，纬度
     */
    @Column(name = "device_latitude")
    private BigDecimal deviceLatitude;

    /**
     * 故障设备位置，经度
     */
    @Column(name = "device_longitude")
    private BigDecimal deviceLongitude;

    /**
     * 维修工ID
     */
    @Column(name = "maintainer_id")
    private Long maintainerId;

    /**
     * 当前维修状态
     */
    private Integer status;

    /**
     * 维修建议（维修工填写）
     */
    private String suggestion;

    /**
     * 维修结果（维修工填写）
     */
    private String result;

    public TaskItem(Long id, Integer version, String creator, Long creatorId, Date createdTime, String lastOperator, Long lastOperatorId, Date updateTime, Long taskId, Long deviceId, String deviceName, Date actualFinishTime, Date actualStartTime, String description, Integer level, BigDecimal deviceLatitude, BigDecimal deviceLongitude, Long maintainerId, Integer status, String suggestion, String result) {
        this.id = id;
        this.version = version;
        this.creator = creator;
        this.creatorId = creatorId;
        this.createdTime = createdTime;
        this.lastOperator = lastOperator;
        this.lastOperatorId = lastOperatorId;
        this.updateTime = updateTime;
        this.taskId = taskId;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.actualFinishTime = actualFinishTime;
        this.actualStartTime = actualStartTime;
        this.description = description;
        this.level = level;
        this.deviceLatitude = deviceLatitude;
        this.deviceLongitude = deviceLongitude;
        this.maintainerId = maintainerId;
        this.status = status;
        this.suggestion = suggestion;
        this.result = result;
    }

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 获取版本号
     *
     * @return version - 版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 获取创建人ID
     *
     * @return creator_id - 创建人ID
     */
    public Long getCreatorId() {
        return creatorId;
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 获取最近操作人
     *
     * @return last_operator - 最近操作人
     */
    public String getLastOperator() {
        return lastOperator;
    }

    /**
     * 获取最后操作人ID
     *
     * @return last_operator_id - 最后操作人ID
     */
    public Long getLastOperatorId() {
        return lastOperatorId;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 获取任务ID
     *
     * @return task_id - 任务ID
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * 获取设备ID
     *
     * @return device_id - 设备ID
     */
    public Long getDeviceId() {
        return deviceId;
    }

    /**
     * @return device_name
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * 获取完成维修的时间戳
     *
     * @return actual_finish_time - 完成维修的时间戳
     */
    public Date getActualFinishTime() {
        return actualFinishTime;
    }

    /**
     * 获取开始维修的时间戳
     *
     * @return actual_start_time - 开始维修的时间戳
     */
    public Date getActualStartTime() {
        return actualStartTime;
    }

    /**
     * 获取故障描述
     *
     * @return description - 故障描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 获取故障等级
     *
     * @return level - 故障等级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 获取故障设备位置，纬度
     *
     * @return device_latitude - 故障设备位置，纬度
     */
    public BigDecimal getDeviceLatitude() {
        return deviceLatitude;
    }

    /**
     * 获取故障设备位置，经度
     *
     * @return device_longitude - 故障设备位置，经度
     */
    public BigDecimal getDeviceLongitude() {
        return deviceLongitude;
    }

    /**
     * 获取维修工ID
     *
     * @return maintainer_id - 维修工ID
     */
    public Long getMaintainerId() {
        return maintainerId;
    }

    /**
     * 获取当前维修状态
     *
     * @return status - 当前维修状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 获取维修建议（维修工填写）
     *
     * @return suggestion - 维修建议（维修工填写）
     */
    public String getSuggestion() {
        return suggestion;
    }

    /**
     * 获取维修结果（维修工填写）
     *
     * @return result - 维修结果（维修工填写）
     */
    public String getResult() {
        return result;
    }
}