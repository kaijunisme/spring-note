package com.example.note.repository;

import com.example.note.po.ProductPo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductPo, Integer> {

    public Optional<ProductPo> findByName(String name);

    public void deleteByName(String name);

}
