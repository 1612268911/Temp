package lambda; /**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: VerificationCode
 * Author:   jj
 * Date:     2018/8/5 14:50
 * Description: 生成图片验证码
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 〈一句话功能简述〉<br>
 * 〈生成图片验证码〉
 *
 * @author jj
 * @create 2018/8/5
 * @since 1.0.0
 */
public class VerificationCode {
//    public void getImage(HttpServletResponse response){
//        int width = 100;
//        int height = 50;
//        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
//        Graphics g = bufferedImage.getGraphics();
//        g.setColor(Color.blue);
//        g.fillRect(0,0,width,height);
//        String str = "";
//        for(int i=0;i<5;i++){
//            int math = (int)(Math.random()*10);
//            str+=math;
//        }
//        g.setFont(new Font("宋体", Font.BOLD,18));
//        g.setColor(Color.RED);
//        g.drawString(str,30,15);
//        try{
//            ImageIO.write(bufferedImage,"jpg",response.getOutputStream());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}