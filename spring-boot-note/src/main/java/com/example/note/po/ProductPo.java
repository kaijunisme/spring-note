package com.example.note.po;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product")
@DynamicInsert
@DynamicUpdate
public class ProductPo extends BasePo {

    /**
     * 流水號
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private int seq;

    /**
     * 名稱
     */
    @Column(name = "name", unique = true, nullable = false, length = 32)
    private String name;

    /**
     * 售價
     */
    @Column(name = "price", nullable = false)
    private int price;

    /**
     * 庫存
     */
    @Column(name = "stock", nullable = false)
    private int stock;

    /**
     * 狀態
     * Y: 販售
     * N: 停售
     */
    @Column(name = "status", nullable = false)
    private String status;

}
