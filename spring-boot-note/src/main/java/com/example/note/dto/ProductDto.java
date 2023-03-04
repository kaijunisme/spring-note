package com.example.note.dto;

import com.example.note.po.ProductPo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

@Getter
@Setter
public class ProductDto {

    /**
     * 名稱
     */
    @NotBlank(message = "產品名稱不可為空")
    private String name;

    /**
     * 售價
     */
    @NotNull(groups = CreateProductAction.class, message = "產品售價不可為空")
    @Min(groups = {CreateProductAction.class, UpdateProductAction.class}, value = 1, message = "產品售價最小值為1元")
    private Integer price;

    /**
     * 庫存
     */
    @NotNull(groups = CreateProductAction.class, message = "產品庫存不可為空")
    @Min(groups = {CreateProductAction.class, UpdateProductAction.class, UpdateProductStockAction.class}, value = 0, message = "產品庫存最小值為0個")
    private Integer stock;

    /**
     * 狀態
     * Y: 販售
     * N: 停售
     */
    @NotBlank(groups = CreateProductAction.class, message = "產品狀態不可為空")
    @Pattern(groups = {CreateProductAction.class, UpdateProductAction.class, UpdateProductStatusAction.class}, regexp = "^Y|N$", message = "產品狀態僅可為Y或N")
    private String status;

    /**
     * 將po 類轉為dto 類
     *
     * @param po
     * @return
     */
    public static ProductDto createByPo(ProductPo po) {
        ProductDto dto = new ProductDto();
        dto.setName(po.getName());
        dto.setPrice(po.getPrice());
        dto.setStock(po.getStock());
        dto.setStatus(po.getStatus());

        return dto;
    }

    /**
     * 將dto 類轉為po 類
     *
     * @JsonIgnore
     * @return
     */
    @JsonIgnore
    public ProductPo getPo() {
        ProductPo po = new ProductPo();
        po.setName(this.getName());
        po.setPrice(this.getPrice());
        po.setStock(this.getStock());
        po.setStatus(this.getStatus());

        return po;
    }

    public interface CreateProductAction extends Default {};
    public interface UpdateProductAction extends Default {};
    public interface UpdateProductStockAction extends Default {};
    public interface UpdateProductStatusAction extends Default {};

}
