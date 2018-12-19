package com.longfor.gaia.gfs.demo.dorado.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DictDTO implements Serializable {

	private static final long serialVersionUID = 7383812893345752450L;

	private Long id;

    private String code;

    private String name;
}
