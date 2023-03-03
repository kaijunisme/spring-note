package com.example.note.controller;

import com.example.note.dto.ProductDto;
import com.example.note.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 取得所有Product 資料
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    /**
     * 根據名稱取得Product 資料
     *
     * @param name
     * @return
     */
    @GetMapping(path = "/{name}")
    public ResponseEntity<ProductDto> getProductByName(@PathVariable(name = "name") String name) {
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    /**
     * 新增Product 資料
     *
     * @param productDto
     * @return
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.addProduct(productDto));
    }

    /**
     * 修改Product 資料
     *
     * @param productDto
     * @return
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> editProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.editProduct(productDto));
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
    public ResponseEntity<ProductDto> editProductStock(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.editProductStock(productDto));
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
    public ResponseEntity<ProductDto> editProductStatus(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.editProductStatus(productDto));
    }

    /**
     * 根據名稱刪除Product 資料
     *
     * @param name
     */
    @DeleteMapping(path = "/{name}")
    public ResponseEntity removeProduct(@PathVariable(name = "name") String name) {
        productService.removeProduct(name);

        return ResponseEntity.ok(null);
    }

}
