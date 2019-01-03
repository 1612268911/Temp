package com.cszt.javacv;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.Manager;
import javax.media.Player;

@SuppressWarnings("restriction")
public class AudioPlayerDemo {
    private Player audioPlayer = null;//建立一个播放接口
    public AudioPlayerDemo(URL url) throws Exception{//创建一个准备Player,准备好播放
        System.out.println(url);
        audioPlayer = Manager.createRealizedPlayer(url);
    }
    @SuppressWarnings("deprecation")
    public AudioPlayerDemo(File file) throws MalformedURLException, Exception{//将本地文件改为URL
        this(file.toURL());
        System.out.println(file.toURL());
    }

    public void play(){//直接调用播放方法就可以
        audioPlayer.start();
    }

    public void stop(){//停止的时候一定要释放资源
        audioPlayer.stop();
        audioPlayer.close();
    }

    public static void main(String [] args) throws MalformedURLException, Exception{
        File file = new File("C:\\Users\\jj\\Music\\华晨宇 - 国王与乞丐 (Live).mp3");
        AudioPlayerDemo Player = new AudioPlayerDemo(file);
        Player.play();
    }
}