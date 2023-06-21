package com.demo.service.impl;

import com.demo.config.MyCacheable;
import com.demo.domain.PmsProduct;
import com.demo.mapper.ProductMapper;
import com.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {


    @Resource
    private ProductMapper productMapper;


    //@Cacheable(value = "product",key = "#productId")
    @MyCacheable(key = "product:#{#productId}",ttl = 100)
    public PmsProduct getProductById(Long productId) {
        PmsProduct pmsProduct = productMapper.getProductById(productId);
        return pmsProduct;
    }


}
