package cn.zjh.springdataelasticsearch.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.springdataelasticsearch.model.dto
 * @date:2019/11/5
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private String id;
    private String bookName;
    private String description;
}
