package j0000;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AddMark {

    private static final String PATH = AddMark.class.getClassLoader().getResource("").getPath()+"r0000/";
    private static final String SRC_FILE_URL = PATH+"test.png";
    private static final String DES_FILE_URL = PATH+"des.png";

    public static void main(String[] args) {

        try{
            //读图片
            BufferedImage img = ImageIO.read(new File(SRC_FILE_URL));
            draw(img,30,80,"1",85,85);
            //写图片
            ImageIO.write(img,"png",new File(DES_FILE_URL));
        } catch (IOException e){
            System.out.println("read or write picture error"+e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * 画图
     * @param img BufferedImage图片对象
     * @param circleR 圆形半径
     * @param fontWeight 字体大小
     * @param num 显示数字
     * @param strLeftOffset 数字与左边界的距离
     * @param strTopOffset 数字与顶边界的距离
     */
    public static void draw(BufferedImage img, int circleR,int fontWeight,String num,int strLeftOffset,int strTopOffset){
        //获取图片宽高
        int height = img.getHeight();
        int width = img.getWidth();
        //计算圆形坐标（图片右上角）
        int circleX = width - circleR*3;
        int circleY = circleR;
        //获取Grpahics2D画笔
        Graphics2D g = img.createGraphics();
        //抗锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        //画圆
        g.setColor(Color.RED);
        g.setStroke(new BasicStroke(circleR*2));
        g.drawOval(circleX,circleY,circleR*2,circleR*2);
        //写字
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.BOLD,fontWeight));
        g.drawString(num,width-strLeftOffset,strTopOffset);
    }

}
