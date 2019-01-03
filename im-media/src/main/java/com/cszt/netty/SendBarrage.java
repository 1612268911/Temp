package com.cszt.netty;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import org.bytedeco.javacv.CanvasFrame;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lilin
 * @create 2018/12/25 15:10
 * description: 绘制弹幕
 */
public class SendBarrage implements Runnable {
    private CanvasFrame frame;

    private List<Danmu> msgList;

    private MyPanel jPanel;

    public SendBarrage(CanvasFrame frame, List<Danmu> msgList) {
        this.frame = frame;
        this.msgList = msgList;
        jPanel = new MyPanel(msgList);
        jPanel.setBounds(0, 0, 400, 400);
        //设置透明
        //jPanel.setOpaque(true);
        this.frame.getLayeredPane().add(jPanel, new Integer(Integer.MAX_VALUE));
        new Thread(new Flush(msgList)).start();
    }

    @Override
    public void run() {
        while (true) {
            frame.repaint();
            //jPanel.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CanvasFrame frame = new CanvasFrame("kkkk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100,100 , 400, 400);
        frame.setResizable(true);
        frame.setAlwaysOnTop(true);
        List<Danmu> list = Arrays.asList(new Danmu[]{new Danmu("jjjjj", 200, 200), new Danmu("我是中国人", 200, 200)});
        List<Danmu> danmuList = new ArrayList<>(list);
        new Thread(new SendBarrage(frame, danmuList)).start();
    }
}

class MyPanel extends JPanel {
    private List<Danmu> msgList;

    public MyPanel(List<Danmu> msgList) {
        this.msgList = msgList;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        try {
            g.setColor(Color.RED);
            g.setFont(new Font("宋体",Font.BOLD,20));
            for (Danmu msg : msgList) {
                if (msg.getY() <= 0){
                    msgList.remove(msg);
                }
                g.drawString(msg.getMsg(), msg.getX(), msg.getY());
                msg.setY(msg.getY() - 20);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Flush implements Runnable{

    private List<Danmu> msgList;

    public Flush(List<Danmu> msgList){
        this.msgList = msgList;
    }
    @Override
    public void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            try {
                String s = bufferedReader.readLine();
                //java.lang.UnsupportedOperationException,Arrays.asList获取的list和new ArrayList不同，不支持ArrayList的add，remove方法
                //java.util.ConcurrentModificationException可能出现的异常，在对collection进行操作时不允许修改collection
                msgList.add(new Danmu(s,200,200));
                System.out.println("s --> "+s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}