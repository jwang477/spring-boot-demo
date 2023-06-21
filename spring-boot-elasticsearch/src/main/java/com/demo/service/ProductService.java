package com.demo.service;

import com.demo.doman.entity.PmsProduct;
import com.demo.doman.es.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

     List<EsProduct> queryAllProduct();

      Page<EsProduct> recommend(Long id, Integer pageNum, Integer pageSize);
}
