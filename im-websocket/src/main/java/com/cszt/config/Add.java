package com.cszt.config;

/**
 * @author lilin
 * @create 2018/12/27 15:53
 * description:
 */
public class Add implements Runnable {
    private TheadTest theadTest;

    public Add(TheadTest theadTest) {
        this.theadTest = theadTest;
    }

    public TheadTest getTheadTest() {
        return theadTest;
    }

    public void setTheadTest(TheadTest theadTest) {
        this.theadTest = theadTest;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (theadTest) {
                if(theadTest.getScore()>=15){
                    try {
                        System.out.println("add.wait....");
                        theadTest.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if(theadTest.getScore()>0){
                    System.out.println("add.notify....");
                    theadTest.notify();
                }
                theadTest.add();
                System.out.println("score->"+theadTest.getScore());
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TheadTest th = new TheadTest();
        Add add = new Add(th);
        Remove remove = new Remove(th);
        new Thread(add).start();
        new Thread(remove).start();
    }
}