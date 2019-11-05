package cn.zjh.springdataelasticsearch.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.springdataelasticsearch.model.dao
 * @date:2019/11/5
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private int id;
    private String bookName;
}
