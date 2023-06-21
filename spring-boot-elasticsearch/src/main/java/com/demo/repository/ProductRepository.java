package com.demo.repository;

import com.demo.doman.es.EsProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<EsProduct, Long> {

}
