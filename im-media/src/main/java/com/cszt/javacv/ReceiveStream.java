package com.cszt.javacv;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author lilin
 * @create 2018/12/19 11:44
 * description:
 */
public class ReceiveStream implements Runnable{

    private String addr;

    public ReceiveStream(String addr) {
        this.addr = addr;
    }

    @Override
    public void run(){
        try {
            frameRecord(addr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 按帧录制视频
     *
     * @param inputFile-该地址可以是网络直播/录播地址，也可以是远程/本地文件路径
     * @param outputFile
     *            -该地址只能是文件地址，如果使用该方法推送流媒体服务器会报错，原因是没有设置编码格式
     * @throws FrameGrabber.Exception
     * @throws FrameRecorder.Exception
     * @throws FrameRecorder.Exception
     */
    public void frameRecord(String inputFile)
            throws Exception, FrameRecorder.Exception {

        boolean isStart = true;//该变量建议设置为全局控制变量，用于控制录制结束
        // 获取视频源
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputFile);
        grabber.setVideoOption("tune", "zerolatency");
        grabber.setFrameRate(25);
        grabber.setVideoOption("crf", "25");
        grabber.setAudioOption("crf", "25");
        grabber.setFormat("flv");
        grabber.setAudioChannels(2);
        // h264编/解码器
        grabber.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        grabber.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
        CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //canvas.setAlwaysOnTop(true);

        //开始取视频源
        try {//建议在线程中使用该方法
            grabber.start();
            Frame frame = null;
            while ((frame = grabber.grabImage()) != null) {
                canvas.showImage(frame);
//                BufferedImage bufferedImage = VideoGetFrame.FrameToBufferedImage(frame);
//                ImageIO.write(bufferedImage, "png", new File("C:\\Users\\jj\\Desktop\\test.png"));
//                canvas.repaint();
                //Thread.sleep(50);
            }
            grabber.stop();
        } finally {
            if (grabber != null) {
                grabber.stop();
            }
        }
    }
    public static void main(String[] args)
            throws Exception {

//        String inputFile = "rtmp://192.168.138.128:1935/hls/test";
        String sendAddr = "rtmp://172.20.3.14:1935/hls/send";
//        String receiveAddr = "rtmp://172.20.3.14:1935/hls/receive";
//
//        new Thread(new SendStream(sendAddr)).start();
//        Thread.sleep(5000);
        Thread thread = new Thread(new ReceiveStream(sendAddr));
        thread.start();
    }
}