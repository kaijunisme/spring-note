package com.example.note.dto;

import com.example.note.po.ProductPo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class ProductDto {

    /**
     * 名稱
     */
    private String name;

    /**
     * 售價
     */
    private Integer price;

    /**
     * 庫存
     */
    private Integer stock;

    /**
     * 狀態
     * Y: 販售
     * N: 停售
     */
    private String status;

    /**
     * 將po 類轉為dto 類
     *
     * @param productPo
     * @return
     */
    public static ProductDto createByPo(ProductPo productPo) {
        ProductDto demoDto = new ProductDto();
        BeanUtils.copyProperties(productPo, demoDto);

        return demoDto;
    }

    /**
     * 將dto 類轉為po 類
     *
     * @JsonIgnore
     * @return
     */
    @JsonIgnore
    public ProductPo getPo() {
        ProductPo productPo = new ProductPo();
        BeanUtils.copyProperties(this, productPo);

        return productPo;
    }

}
