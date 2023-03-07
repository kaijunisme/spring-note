package com.example.note.controller;

import com.example.note.dto.ProductDto;
import com.example.note.dto.ResponseDto;
import com.example.note.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    /**
     * 取得所有Product 資料
     *
     * @return
     */
    @GetMapping
    public ResponseDto<List<ProductDto>> getAllProduct() {
        return super.ok(productService.getAllProduct());
    }

    /**
     * 根據名稱取得Product 資料
     *
     * @param name
     * @return
     */
    @GetMapping(path = "/{name}")
    public ResponseDto<ProductDto> getProductByName(@PathVariable(name = "name") String name) {
        return super.ok(productService.getProductByName(name));
    }

    /**
     * 新增Product 資料
     *
     * @param productDto
     * @return
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<ProductDto> addProduct(@Validated(ProductDto.CreateProductAction.class) @RequestBody ProductDto productDto) {
        return super.ok(productService.addProduct(productDto));
    }

    /**
     * 修改Product 資料
     *
     * @param productDto
     * @return
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<ProductDto> editProduct(@Validated(ProductDto.UpdateProductAction.class) @RequestBody ProductDto productDto) {
        return super.ok(productService.editProduct(productDto));
    }

    /**
     * 修改Product 庫存資料
     *
     * @param productDto
     * @return
     */
    @PutMapping(path = "/stock",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<ProductDto> editProductStock(@Validated(ProductDto.UpdateProductStockAction.class) @RequestBody ProductDto productDto) {
        return super.ok(productService.editProductStock(productDto));
    }

    /**
     * 修改Product 狀態資料
     *
     * @param productDto
     * @return
     */
    @PutMapping(path = "/status",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<ProductDto> editProductStatus(@Validated(ProductDto.UpdateProductStatusAction.class) @RequestBody ProductDto productDto) {
        return super.ok(productService.editProductStatus(productDto));
    }

    /**
     * 根據名稱刪除Product 資料
     *
     * @param name
     */
    @DeleteMapping(path = "/{name}")
    public void removeProduct(@PathVariable(name = "name") String name) {
        productService.removeProduct(name);
    }

}
