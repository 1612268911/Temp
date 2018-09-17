import java.util.concurrent.CountDownLatch;

/**
 * @author lilin
 * @create 2018/9/17 9:11
 * description: CountDownLatch用法：
 * https://blog.csdn.net/joenqc/article/details/76794356
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(1);
        new Thread(new Test2(cdl)).start();
        new Thread(new Test1(cdl)).start();
        Thread.sleep(2000);
        cdl.countDown();
        System.out.println("线程1启动完毕....");
    }
}
class Test1 implements Runnable{
    private CountDownLatch cdl;

    public Test1(CountDownLatch cdl){
        this.cdl = cdl;
    }
    @Override
    public void run(){
        try {
            cdl.await();
            System.out.println("线程test1启动完毕...."+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Test2 implements Runnable{

    private CountDownLatch cdl;

    public Test2(CountDownLatch cdl){
        this.cdl = cdl;
    }
    @Override
    public void run(){
        try{
            cdl.await();
            System.out.println("线程test2启动完毕...."+System.currentTimeMillis());
        }catch(Exception e){

        }
    }
}
