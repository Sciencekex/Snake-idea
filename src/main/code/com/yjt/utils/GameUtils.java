package com.yjt.utils;

import javax.swing.*;
import java.awt.*;

public class GameUtils {

    //蛇头图片引入
    public static Image headImg= Toolkit.getDefaultToolkit().getImage("src/main/resources/img/head.png");
    //蛇身图片引入
    public static Image bodyImg= Toolkit.getDefaultToolkit().getImage("src/main/resources/img/body.png");
    //食物图片引入
    public static Image foodImg= Toolkit.getDefaultToolkit().getImage("src/main/resources/img/food.png");

    //绘制文字
    public static void drawWord(Graphics g,String str,Color color,int size,int x,int y){
        g.setColor(color);
        g.setFont(new Font("微软雅黑",Font.BOLD,size));
        g.drawString(str,x,y);
    }
}
