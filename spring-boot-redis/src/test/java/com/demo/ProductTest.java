package com.demo;

import com.demo.domain.PmsProduct;
import com.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTest {
    @Autowired
    private ProductService productService;

    @Test
    public void test01(){
        PmsProduct pmsProduct = productService.getProductById(30L);
        System.out.println("pmsProduct = " + pmsProduct);
    }

}
