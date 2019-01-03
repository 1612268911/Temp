package com.cszt.javacv;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.*;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * @author lilin
 * @create 2018/12/19 11:24
 * description: 打开摄像头录制视频，推流到服务器
 */
public class SendStream implements Runnable{

    private String addr;

    public SendStream(String addr) {
        this.addr = addr;
    }

    @Override
    public void run(){
        try {
            recordCamera(addr,25);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 按帧录制本机摄像头视频（边预览边录制，停止预览即停止录制）
     *
     * @author eguid
     * @param outputFile -录制的文件路径，也可以是rtsp或者rtmp等流媒体服务器发布地址
     * @param frameRate - 视频帧率
     * @throws Exception
     * @throws InterruptedException
     * @throws FrameRecorder.Exception
     */
    public void recordCamera(String outputFile, double frameRate)
            throws Exception, InterruptedException, FrameRecorder.Exception {
        //Loader.load(JavavcTest.class);
        FrameGrabber grabber = FrameGrabber.createDefault(0);//本机摄像头默认0，这里使用javacv的抓取器，至于使用的是ffmpeg还是opencv，请自行查看源码
        grabber.start();//开启抓取器

        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();//转换器
        opencv_core.IplImage grabbedImage = converter.convert(grabber.grab());//抓取一帧视频并将其转换为图像，至于用这个图像用来做什么？加水印，人脸识别等等自行添加
        int width = grabbedImage.width();
        int height = grabbedImage.height();

        FrameRecorder recorder = FrameRecorder.createDefault(outputFile, width, height);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // avcodec.AV_CODEC_ID_H264，编码
        recorder.setFormat("flv");//封装格式，如果是推送到rtmp就必须是flv封装格式
        recorder.setFrameRate(frameRate);

        recorder.start();//开启录制器
        long startTime=0;
        long videoTS=0;
        CanvasFrame frame = new CanvasFrame("camera", CanvasFrame.getDefaultGamma() / grabber.getGamma());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setAlwaysOnTop(true);
        Frame rotatedFrame=converter.convert(grabbedImage);//不知道为什么这里不做转换就不能推到rtmp
        while ((grabbedImage = converter.convert(grabber.grab())) != null) {
            rotatedFrame = converter.convert(grabbedImage);

            frame.showImage(rotatedFrame);
            if (startTime == 0) {
                startTime = System.currentTimeMillis();
            }
            videoTS = 1000 * (System.currentTimeMillis() - startTime);
            recorder.setTimestamp(videoTS);
            recorder.record(rotatedFrame);
            Thread.sleep(40);
        }
        frame.dispose();
        recorder.stop();
        recorder.release();
        grabber.stop();

    }
    public static BufferedImage FrameToBufferedImage(Frame frame) {
        //创建BufferedImage对象
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        return bufferedImage;
    }
    public static void main(String[] args) throws Exception, InterruptedException, FrameRecorder.Exception {
        //recordCamera("rtmp://192.168.138.128:1935/hls/test",25);
        String sendAddr = "rtmp://172.20.3.14:1935/hls/send";
//        String receiveAddr = "rtmp://172.20.3.14:1935/hls/receive";
        new Thread(new SendStream(sendAddr)).start();
//        Thread.sleep(5000);
////        new Thread(new SendStream(receiveAddr)).start();
//        new Thread(new ReceiveStream(sendAddr)).start();
//        new Thread(new ReceiveStream(sendAddr)).start();
    }
}