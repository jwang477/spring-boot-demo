package com.demo.mapper;

import com.demo.doman.es.EsProduct;

import java.util.List;

public interface ProductMapper {


    List<EsProduct> queryAllProduct(Long id);
}
