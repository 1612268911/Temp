import com.sun.corba.se.impl.protocol.RequestCanceledException;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lilin
 * @create 2018/9/19 10:41
 * description: zookeeper zkClient连接
 * 负载均衡方法 轮询
 */
public class ZkClientTest {
    private ZkClient zkClient;

    private String currentNode;

    private Integer requestCount = 1;

    private List<String> list = new ArrayList<String>();

    private String parentPath = "/root";

    public static void main(String[] args){
        ZkClientTest zkClientTest = new ZkClientTest();
        zkClientTest.init();
        while(true){
            System.out.println("*******************start**********************");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            zkClientTest.getChildNode();
            System.out.println("currentNode--->"+zkClientTest.getCurrentNode());
            System.out.println("requestCount-->"+ zkClientTest.getRequestCount());
        }
    }
    /**
     * @author lilin
     * @description
     * @date: 2018/9/19 14:32
     * @param:
     * @return:
     */
    public void init(){
        //连接
        zkClient = new ZkClient("localhost:2181",3000);
        if(!zkClient.exists(parentPath)){
            //创建持久节点
            zkClient.create(parentPath,"jjjjj".getBytes(), CreateMode.PERSISTENT);
        }
        /**
         * 设置监听
         */
        zkClient.subscribeChildChanges(parentPath, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("s-->"+s+"    list-->"+list);
            }
        });
    }
    /**
     * @author lilin
     * @description 获取所有子节点
     * @date: 2018/9/19 14:32
     * @param:
     * @return:
     */
    public void getChildNode(){
        list = zkClient.getChildren(parentPath);
        if(list == null || list.size()==0){
            System.out.println(parentPath+"当前没有子节点...");
            requestCount++;
            return;
        }
        //轮询
        currentNode = list.get(requestCount%list.size());
        requestCount++;
    }
    public String getCurrentNode(){
        return currentNode;
    }
    public Integer getRequestCount(){
        return requestCount;
    }
}