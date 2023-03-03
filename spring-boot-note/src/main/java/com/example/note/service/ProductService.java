package com.example.note.service;

import com.example.note.dto.ProductDto;
import com.example.note.po.ProductPo;
import com.example.note.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * 取得所有Product 資料
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<ProductDto> getAllProduct() {
        return productRepository.findAll().stream()
                .map(ProductDto::createByPo)
                .collect(Collectors.toList());
    }

    /**
     * 根據名稱取得Product 資料
     *
     * @param name
     * @return
     */
    @Transactional(readOnly = true)
    public ProductDto getProductByName(final String name) {
        return productRepository.findByName(name)
                .map(ProductDto::createByPo)
                .orElseThrow(() -> new RuntimeException("查無相關Product 資料"));
    }

    /**
     * 新增Product 資料
     *
     * @param productDto
     * @return
     */
    @Transactional
    public ProductDto addProduct(ProductDto productDto) {
        if (productRepository.findByName(productDto.getName()).isPresent())
            throw new RuntimeException("已有相同名稱之Product 資料");

        ProductPo productPo = productRepository.save(productDto.getPo());
        return ProductDto.createByPo(productPo);
    }

    /**
     * 修改Product 資料
     *
     * @param productDto
     * @return
     */
    @Transactional
    public ProductDto editProduct(ProductDto productDto) {
        return this.updateProduct(
                () -> {
                    return productRepository.findByName(productDto.getName())
                            .orElseThrow(() -> new RuntimeException("查無相關Product 資料"));
                },
                po -> {
                    if (null != productDto.getStock())
                        po.setStock(productDto.getStock());

                    if (null != productDto.getPrice())
                        po.setPrice(productDto.getPrice());

                    if (StringUtils.hasText(productDto.getStatus()))
                        po.setStatus(productDto.getStatus());
                }
        );
    }

    /**
     * 修改Product 庫存資料
     *
     * @param productDto
     * @return
     */
    @Transactional
    public ProductDto editProductStock(ProductDto productDto) {
        return this.updateProduct(
                () -> {
                    return productRepository.findByName(productDto.getName())
                            .orElseThrow(() -> new RuntimeException("查無相關Product 資料"));
                },
                po -> {
                    if (null != productDto.getStock())
                        po.setStock(productDto.getStock());
                }
        );
    }

    /**
     * 修改Product 狀態資料
     *
     * @param productDto
     * @return
     */
    @Transactional
    public ProductDto editProductStatus(ProductDto productDto) {
        return this.updateProduct(
                () -> {
                    return productRepository.findByName(productDto.getName())
                            .orElseThrow(() -> new RuntimeException("查無相關Product 資料"));
                },
                po -> {
                    if (null != productDto.getStatus())
                        po.setStatus(productDto.getStatus());
                }
        );
    }

    /**
     * 根據指定方法取得ProductPo，再依照指定方法替換ProductPo 內容，最後更新Product 資料
     *
     * @param getter
     * @param setter
     * @return
     */
    private ProductDto updateProduct(Supplier<ProductPo> getter, Consumer<ProductPo> setter) {
        ProductPo productPo = getter.get();
        setter.accept(productPo);

        productRepository.save(productPo);
        return ProductDto.createByPo(productPo);
    }

    /**
     * 根據名稱刪除Product 資料
     *
     * @param name
     */
    @Transactional
    public void removeProduct(final String name) {
        if (productRepository.findByName(name).isEmpty())
            throw new RuntimeException("查無相關Product 資料");

        productRepository.deleteByName(name);
    }

}
