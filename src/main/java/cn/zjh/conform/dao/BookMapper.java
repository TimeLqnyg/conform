package cn.zjh.conform.dao;

import cn.zjh.conform.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

//@Mapper
@Repository
public interface BookMapper {

	@Select("select * from book where id=#{id}")
	Book queryBookById(Integer id);

	@Select("select * from book where name=#{id}")
	Book getBookByName(String bookName);

}

