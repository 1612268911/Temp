import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author lilin
 * @create 2018/9/12 14:36
 * description:
 */
public class Provider {
    private ZooKeeper zookeeper;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    //    public void init() throws IOException {
//        countDownLatch = new CountDownLatch(10);
//        zookeeper = new ZooKeeper("localhost:2181", 2000, new Watcher() {
//            @Override
//            public void process(WatchedEvent event) {
//                if(event.getType().equals(Event.EventType.NodeDataChanged) ) {
//                    System.out.println(event.getPath() + "节点发生了改变");
//                }
//                if(event.getType().equals(Event.EventType.NodeCreated)){
//                    System.out.println(event.getPath() + "节点创建了....");
//                }
//                //因为监听器只会监听一次，这样可以一直监听,且只监听"/"目录
//                try {
//                    countDownLatch.await();
//                    zookeeper.getChildren("/", true);
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
    public void startZK() throws Exception {
        System.out.println("startZK----------------------");
        //确保server确实已经开启了，这里是创建client到server的session
        zookeeper = new ZooKeeper("127.0.0.1:2181", 2000,
                new Watcher() {
                    @Override
                    public void process(WatchedEvent watchedEvent) {
                        System.out.println("process--> " + watchedEvent);
                        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                            countDownLatch.countDown();
                            System.out.println("countDown");
                        }
                        try {
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
        System.out.println("state is " + zookeeper.getState());
        System.out.println("zk session begin");
    }

    public void createNode(String path, String content) {
        try {
            String no = zookeeper.create(path, content.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("添加一个服务提供者：" + path);
    }

    public Stat isExecit(String path, boolean isWacher) {
        try {
            return zookeeper.exists(path, isWacher);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delNode(String path) throws KeeperException, InterruptedException {
        zookeeper.delete(path,-1);
    }
    public void setData(String path,String content) throws KeeperException, InterruptedException {
        zookeeper.setData(path,content.getBytes(),-1);
    }

    public void close() {
        if (zookeeper != null) {
            try {
                zookeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Provider provider = new Provider();
        provider.startZK();
        for (int i = 1; i < 6; i++) {
            provider.createNode("/node"+i, "jjjjjjjjjjjjj");
            provider.isExecit("/node"+i, true);
//            Thread.sleep(5000);
//            provider.setData("/node"+i,"mmmmmmmmmmmmmmmmmmmmm");
            Thread.sleep(5000);
            provider.delNode("/node"+i);
        }
    }
}