package com.example.hello.es;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author jack.wen
 * @since 2024/7/3 22:19
 */
public class TestClient {

    public static void main(String[] args) throws Exception {
        //  早期版本的客户端TransportClient对象已经不再推荐使用，且在未来版本中会被删除，改用高级Rest客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        // 关闭es客户端
        esClient.close();
    }

}
