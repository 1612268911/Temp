package com.cszt.config;

/**
 * @author lilin
 * @create 2018/12/27 17:09
 * description:
 */
public class Remove implements Runnable{
    private TheadTest theadTest;

    public Remove(TheadTest theadTest) {
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
        int i = 100;
        while (true) {
            synchronized (theadTest) {
                if(theadTest.getScore()<=0){
                    try {
                        System.out.println("remove.wait....");
                        theadTest.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(theadTest.getScore()<15){
                    System.out.println("remove.notify....");
                    theadTest.notify();
                }
                theadTest.remove();
                System.out.println("score->"+theadTest.getScore());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}