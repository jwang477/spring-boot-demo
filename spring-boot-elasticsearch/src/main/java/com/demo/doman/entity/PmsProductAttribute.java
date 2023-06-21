package com.demo.doman.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class PmsProductAttribute implements Serializable {
    private Long id;

    private Long productAttributeCategoryId;

    private String name;

    private Integer selectType;

    private Integer inputType;

    private String inputList;

    private Integer sort;

    private Integer filterType;

    private Integer searchType;

    private Integer relatedStatus;

    private Integer handAddStatus;

    private Integer type;

    private static final long serialVersionUID = 1L;



}