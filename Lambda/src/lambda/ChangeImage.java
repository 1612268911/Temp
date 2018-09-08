package lambda; /**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ChangeImage
 * Author:   jj
 * Date:     2018/8/3 15:59
 * Description: 根据审批步骤，画流程图
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈修改图片〉
 *
 * @author jj
 * @create 2018/8/3
 * @since 1.0.0
 */
public class ChangeImage {
    public static void main(String[] args){
        File file = new File("C:/Users/jj/Desktop/test.jpg");
        FileOutputStream out = null;
        BufferedImage bufferedImage = null;
        try{
            out = new FileOutputStream("C:/Users/jj/Desktop/testNew.jpg");
            bufferedImage = ImageIO.read(file);
            Graphics g = bufferedImage.getGraphics();
            g.setColor(Color.RED);
            g.drawRect(100,100,100,100);
            ImageIO.write(bufferedImage,"jpg",out);
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