package cn.zjh.springdataelasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

@Configuration
public class ESConfig {
	@Bean
	RestHighLevelClient restHighLevelClient() {

		ClientConfiguration clientConfiguration = ClientConfiguration.builder()
				.connectedTo("192.168.136.250:9200", "192.168.136.250:9300")
				.build();

		return RestClients.create(clientConfiguration).rest();
	}
}
