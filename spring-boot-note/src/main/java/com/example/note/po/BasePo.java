package com.example.note.po;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
public class BasePo {

    /**
     * 建立者
     */
    @CreatedBy
    @Column(name = "create_by", updatable = false)
    private String createBy;

    /**
     * 建立時間
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", updatable = false)
    private Date createTime;

    /**
     * 修改者
     */
    @LastModifiedBy
    @Column(name = "update_by", insertable = false)
    private String updateBy;

    /**
     * 修改時間
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", insertable = false)
    private Date updateTime;

}
