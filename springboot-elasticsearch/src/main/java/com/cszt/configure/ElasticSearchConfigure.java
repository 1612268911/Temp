//package com.cszt.configure;
//
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
///**
// * @author lilin
// * @create 2018/11/26 15:05
// * description:
// */
//@Configuration
//@EnableElasticsearchRepositories(basePackages = {"com.cszt.repository"})
//public class ElasticSearchConfigure {
//    @Value("${elasticsearch.cluster.host}")
//    private String host;
//    @Value("${elasticsearch.cluster.port}")
//    private String port;
//    @Value("${elasticsearch.cluster.name}")
//    private String clusterName;
//
//    @Bean
//    @Primary
//    public Client getClient() throws Exception {
//
//        Settings esSettings = Settings.builder()
//                .put("cluster.name", clusterName)
//                .put("client.transport.sniff",true)
//                .build();
//        return new PreBuiltTransportClient(esSettings)
//                .addTransportAddress(
//                        new InetSocketTransportAddress(InetAddress.getByName(host), Integer.parseInt(port)));
//    }
//    @Bean
//    @Qualifier
//    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
//        return new ElasticsearchTemplate(getClient());
//    }
//}