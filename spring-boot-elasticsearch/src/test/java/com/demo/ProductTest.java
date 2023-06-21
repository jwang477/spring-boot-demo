package com.demo;

import com.demo.doman.entity.PmsProduct;
import com.demo.doman.es.EsProduct;
import com.demo.repository.ProductRepository;
import com.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductService productService;

    @Resource
    private ProductRepository productRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    //@Test
    public void test01(){
        System.out.println("productService = " + productService);
        List<EsProduct> pmsProducts = productService.queryAllProduct();
        for (EsProduct pmsProduct : pmsProducts) {
            //pmsProduct.setAttrValueList(null);
        }
        productRepository.saveAll(pmsProducts);
    }

    @Test
    public void test02(){
        System.out.println("productService = " + productService);
        Page<EsProduct> pmsProducts = productService.recommend(30L,0,20);
        for (EsProduct pmsProduct : pmsProducts) {
            System.out.println("pmsProduct = " + pmsProduct);
        }

    }


}
