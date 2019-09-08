package cn.zjh.conform.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Book implements Serializable {
	private Integer id;
	private String category;
	private String name;
	private BigDecimal price;
	private String author;
	private String description;
	private String remain;
}
