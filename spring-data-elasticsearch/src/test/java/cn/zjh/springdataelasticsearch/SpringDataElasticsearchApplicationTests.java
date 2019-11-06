package cn.zjh.springdataelasticsearch;

import cn.zjh.springdataelasticsearch.model.dao.Book;
import cn.zjh.springdataelasticsearch.model.dto.BookDTO;
import cn.zjh.springdataelasticsearch.utils.VoPoConverter;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static java.util.Collections.singletonMap;
import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;

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

    @Autowired()
    RestHighLevelClient highLevelClient;
//    RestClient lowLevelClient = highLevelClient.lowLevelClient();

    @Test
    public void testRestClient() throws IOException {
        IndexRequest request = new IndexRequest("spring-data", "elasticsearch", "1")
                .source(singletonMap("feature", "high-level-rest-client"))
                .setRefreshPolicy(IMMEDIATE);

        IndexResponse response = highLevelClient.index(request);
        System.out.println(response.toString());
    }

    @Test
    public void getRestClient() throws IOException{
        GetRequest request = new GetRequest("spring-data", "elasticsearch", "1");
        final GetResponse response = highLevelClient.get(request);
        System.out.println(response.getType());
    }

}
