import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author lilin
 * @create 2018/9/14 10:08
 * description:
 */
public class MyWather implements Watcher {
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private ZooKeeper zooKeeper;

    public void init() throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("localhost:2181",2000,this);
        countDownLatch.await();
    }

    public void process(WatchedEvent event) {
        // 事件状态
        Event.KeeperState state = event.getState();
        //事件路径
        String watchPath = event.getPath();
        //判断为连接状态
        if (event.getState() == Event.KeeperState.SyncConnected) {
            //事件类型
            Event.EventType eventType = event.getType();
            //建立连接成功
            if(Event.EventType.None==eventType){
                System.out.println("连接成功....");
                countDownLatch.countDown();
            }else if(Event.EventType.NodeCreated==eventType){
                System.out.println("新增节点成功....");
            }else if(Event.EventType.NodeDeleted==eventType){
                System.out.println("删除节点成功....");
            }
        }
    }
    public void createNode(String path) throws KeeperException, InterruptedException {
        String result = zooKeeper.create(path,"jjjjjjj".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        System.out.println("create node :"+result);
    }

    public List<String> getChildren(String path) throws KeeperException,InterruptedException {
        //监听该节点子节点的变化情况
        return this.zooKeeper.getChildren(path, this);
    }
    public Stat setData(String path, byte[] data, int version)throws KeeperException, InterruptedException {
        return this.zooKeeper.setData(path, data, version);
    }
    public void delNode(String path) throws KeeperException, InterruptedException {
        zooKeeper.delete(path,-1);
        System.out.println("delete node :"+path);
    }
    public byte[] getData(String path) throws KeeperException, InterruptedException {
        return zooKeeper.getData(path,false,null);
    }
    public void exit(String path) throws KeeperException, InterruptedException {
        zooKeeper.exists(path,true);
    }
    public void close() {
        if (zooKeeper != null) {
            try {
                zooKeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws Exception {
        MyWather wather = new MyWather();
        wather.init();
        wather.exit("/node");
        wather.createNode("/node");
        wather.exit("/node");
        wather.delNode("/node");
        Thread.sleep(Integer.MAX_VALUE);
    }
}