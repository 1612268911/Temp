package com.cszt.elasticsearch;


import com.alibaba.fastjson.JSONObject;
import com.cszt.domain.User;
import org.apache.lucene.search.SortField;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.Transport;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import sun.security.x509.InvalidityDateExtension;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author lilin
 * @create 2018/11/26 9:55
 * description:
 */
public class ElasticSearchUtil {
    /***
     * 获取es连接
     * @throws Exception
     */
    public static TransportClient getConnection() throws Exception {
        Settings settings = Settings.builder().put("client.transport.sniff", true).build();
        TransportClient client = new PreBuiltTransportClient(settings);
        TransportAddress transportAddress = new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300);
        client.addTransportAddress(transportAddress);
        return client;
    }

    public static void create() throws Exception {
        TransportClient client = getConnection();
        IndexRequestBuilder indexRequestBuilder = client.prepareIndex(CommonConfig.INDEX, CommonConfig.TYPE, "5");
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("name", "jjjjhhj");
//        map.put("age", 15);
//        map.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        indexRequestBuilder.setSource(map);

        User user = new User();
        user.setId(2);
        user.setName("java" + 2);
        user.setPwd("11" + 1);
        user.setCreateTime(new Date());
        indexRequestBuilder.setId(user.getId()+"");
        String jsonStr = JSONObject.toJSONString(user);
        indexRequestBuilder.setSource(jsonStr);
        IndexResponse indexResponse = indexRequestBuilder.get();
        System.out.println(indexResponse);
        client.close();
    }

    public static void get() throws Exception {
        TransportClient client = getConnection();
        GetRequestBuilder getRequestBuilder = client.prepareGet("test", "user", "2");
        GetResponse getFields = getRequestBuilder.get();
        Map<String, Object> source = getFields.getSource();
        System.out.println(source);
        client.close();
    }

    public static void createIndex() throws Exception {
        TransportClient client = getConnection();
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("stu")
                .startObject("id").field("type", "text").endObject()
                .startObject("name").field("type", "text").endObject()
                .startObject("pwd").field("type", "text").endObject()
                .startObject("createTime").field("type", "text").field("fielddata", true).endObject()
                .endObject()
                .endObject();
        IndexResponse indexResponse = client.prepareIndex(CommonConfig.INDEX, "stu")
                .setSource(builder)
                .get();
        String index = indexResponse.getIndex();
        String type = indexResponse.getType();
        String id = indexResponse.getId();
        RestStatus status = indexResponse.status();
        int status1 = status.getStatus();
        String s = status.toString();
        System.out.println("index" + index + "   tyep=" + type + "   id=" + id + "   status=" + status1 + "   s=" + s);
    }

    public static void isIndexInvailed(String index) throws Exception {
        TransportClient client = getConnection();
        IndicesExistsResponse indicesExistsResponse = client.admin().indices().exists(new IndicesExistsRequest(index)).actionGet();
        boolean exists = indicesExistsResponse.isExists();
        System.out.println(exists);
    }

    public static void delete() throws Exception {
        TransportClient client = getConnection();
        DeleteResponse deleteResponse = client.prepareDelete().setIndex(CommonConfig.INDEX).setType("user").setId("5")
                .get();
        String index = deleteResponse.getIndex();
        String type = deleteResponse.getType();
        String id = deleteResponse.getId();
        RestStatus status = deleteResponse.status();
        int status1 = status.getStatus();
        String s = status.toString();
        System.out.println("index" + index + "   tyep=" + type + "   id=" + id + "   status=" + status1 + "   s=" + s);
    }

    public static void update() throws Exception {
        TransportClient client = getConnection();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jked");
        map.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        UpdateResponse updateResponse = client.prepareUpdate(CommonConfig.INDEX, CommonConfig.TYPE, "2")
                .setDoc(new User())
                .get();
        System.out.println(updateResponse.status());
    }

    public static void search() throws Exception {
        TransportClient client = getConnection();
        SearchResponse searchResponse = client
                .prepareSearch(CommonConfig.INDEX)//index
                .setTypes("user")//type
                .setQuery(new WildcardQueryBuilder("name", "*j*"))//name like '%j%'
                .addSort("id", SortOrder.ASC)//根据id降序排序
                .setFrom(1)//从第几条记录开始查，最开始是 0
                .setSize(3)//最大输出
                .get();

        SearchHits searchHits = searchResponse.getHits();
        SearchHit[] searchHitsList = searchHits.getHits();
        for (SearchHit searchHitFields : searchHitsList) {
            //str
            String str = searchHitFields.getSourceAsString();
            //user
            User obj = JSONObject.parseObject(str, User.class);
            System.out.println(obj);
        }
    }
    public void searchDSL() throws Exception {
        TransportClient client = getConnection();
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch();
        searchRequestBuilder.setIndices("test");
        searchRequestBuilder.setTypes("user");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchAllQuery());
        searchRequestBuilder.setQuery(boolQueryBuilder);
        client.close();
    }

    public static void main(String[] args) throws Exception {
        //create();
        //get();
        //createIndex();
        //isIndexInvailed(CommonConfig.INDEX);
        //delete();
        //update();
        search();
    }
}