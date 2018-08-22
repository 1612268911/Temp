/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ChangeImage
 * Author:   jj
 * Date:     2018/8/3 15:59
 * Description: 根据审批步骤，画流程图
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈根据审批步骤，画流程图〉
 *
 * @author jj
 * @create 2018/8/3
 * @since 1.0.0
 */
public class ChangeImage {
    public static void main(String[] args){
        ChangeImage.changeImage(20,20,20,20);
    }
    public static void changeImage(int x,int y,int width,int height){
        File file = new File("C:/Users/jj/Desktop/test.png");
        FileOutputStream out = null;
        BufferedImage bufferedImage = null;
        try{
            out = new FileOutputStream("C:/Users/jj/Desktop/testNew.png");
            bufferedImage = ImageIO.read(file);
            Graphics g = bufferedImage.getGraphics();
            g.setColor(Color.RED);
            g.drawRect(x,y,width,height);
            ImageIO.write(bufferedImage,"png",out);
            file.delete();
            System.out.println("执行完成...");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}