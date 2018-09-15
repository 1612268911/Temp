import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author lilin
 * @create 2018/9/12 14:36
 * description:
 */
public class Consumer {
    private static final String CONNECT_STRING = "localhost:2181";
    private static final int SESSION_TIMEOUT = 5000;

    ZooKeeper zkClient = null;

    CountDownLatch countDownLatch = new CountDownLatch(1);

    @Before
    public void init() throws Exception {
        zkClient = new ZooKeeper(CONNECT_STRING, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // 收到事件通知后的回调函数（应该是我们自己的事件处理逻辑）
                Event.EventType type = event.getType();
                Event.KeeperState state = event.getState();
                System.out.println(event.getType() + "---" + event.getPath());

                if(type ==Event.EventType.None){
                    countDownLatch.countDown();
                }else if(type == Event.EventType.NodeCreated){
                    System.out.println("创建节点："+event.getPath()+"成功！");
                }else if(type == Event.EventType.NodeDataChanged){
                    System.out.println("修改节点数据："+event.getPath()+"成功！");
                }else if(type == Event.EventType.NodeDeleted){
                    System.out.println("删除节点："+event.getPath()+"成功！");
                }

            }
        });
        countDownLatch.await();
    }

    /**
     * 数据的增删改查
     */

    // 创建数据节点到zk中
    @Test
    public void testCreate() throws KeeperException, InterruptedException {
        // 参数1：要创建的节点的路径 参数2：节点大数据 参数3：节点的权限 参数4：节点的类型
        String nodeCreated = zkClient.create("/node/strNode", "helloworld".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //上传的数据可以是任何类型，但都要转成byte[]
    }

    //判断znode是否存在
    @Test
    public void testExist() throws Exception{
        Stat stat = zkClient.exists("/node/strNode", false);
        System.out.println(stat==null?"not exist":"exist");
    }

    // 获取子节点
    @Test
    public void getChildren() throws Exception {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

    //获取znode的数据
    @Test
    public void getData() throws Exception {
        byte[] data = zkClient.getData("/node/strNode", false, null);
        System.out.println(new String(data));
    }

    //删除znode
    @Test
    public void deleteZnode() throws Exception {
        //参数2：指定要删除的版本，-1表示删除所有版本
        zkClient.delete("/node", -1);
    }

    //删除znode
    @Test
    public void setData() throws Exception {
        zkClient.setData("/node", "imissyou angelababy".getBytes(), -1);
        byte[] data = zkClient.getData("/node", false, null);
        System.out.println(new String(data));
    }
    public static void main(String[] args) throws Exception {
        Consumer consumer = new Consumer();
        consumer.init();
        consumer.testCreate();
        consumer.testExist();
        Thread.sleep(10000);
    }
}