package cn.zjh.springdataelasticsearch;

import cn.zjh.springdataelasticsearch.model.dao.Book;
import cn.zjh.springdataelasticsearch.model.dto.BookDTO;
import cn.zjh.springdataelasticsearch.utils.VoPoConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class SpringDataElasticsearchApplicationTests {


    @Test
    void contextLoads() {
    }

    @Test
    public void testConvert(){
        Book book=new Book(1,"test","测试不同名字的属性");
        BookDTO dto = VoPoConverter.copyProperties(book, BookDTO.class);
        //可以拼装的
        dto.setDescription(book.getDesc());
        System.out.println(dto.toString());
    }

}
