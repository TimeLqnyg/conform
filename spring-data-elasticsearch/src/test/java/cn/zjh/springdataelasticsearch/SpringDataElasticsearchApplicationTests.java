package cn.zjh.springdataelasticsearch;

import cn.zjh.springdataelasticsearch.model.dao.Book;
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
        Book book=new Book(1,"test");

    }

}
