package cn.zjh.conform.dao;

import cn.zjh.conform.model.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

//@Mapper
@Repository
public interface BookMapper {

	@Select("select * from book where id=#{id}")
	Book queryBookById(Integer id);

	@Select("select * from book where name=#{bookName}")
	Book getBookByName(String bookName);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	@Insert("insert into book(name) values (#{bookName})")
	Integer addBook( @Param("bookName") String bookName);

}

