package com.demo.mapper;

import com.demo.domain.PmsProduct;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ProductMapper {
    @Select("select * from pms_product where id = #{productId}")
    PmsProduct getProductById(@Param("productId") Long productId);
}
