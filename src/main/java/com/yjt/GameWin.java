//@作者署名 作者
/*
 * @作者:叶江涛
 * @时间:2024年06月04日 16:02:01
 */

package com.yjt;

import com.yjt.obj.BodyObj;
import com.yjt.obj.FoodObj;
import com.yjt.obj.HeadObj;
import com.yjt.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameWin extends JFrame{
    public Graphics g;
    //蛇头对象
    HeadObj headObj=new HeadObj(GameUtils.headImg,30,570,GameWin.this);

    //蛇的身体集合
    public List<BodyObj> bodyObjList=new ArrayList<>();

    //食物
    public FoodObj foodObj=new FoodObj().getFood();
    public  void launch(){
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setTitle("贪吃蛇");

        //蛇身体的初始化
        bodyObjList.add(new BodyObj(GameUtils.bodyImg,30,570,this));
        bodyObjList.add(new BodyObj(GameUtils.bodyImg,0,570,this));

        while(true){
            repaint();
            try{
                //1秒1000毫秒
                Thread.sleep(200);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        //灰色背景
        g.setColor(Color.GRAY);
        //填充矩形
        g.fillRect(0,0,600,600);
        //网格线
        g.setColor(Color.BLACK);
        for (int i = 0; i < 600; i += 30) {
            g.drawLine(0,i,600,i);
            g.drawLine(i,0,i,600);
        }
        //绘制蛇身体
        for (int i=bodyObjList.size()-1;i>=0;i--){
            bodyObjList.get(i).paintSelf(g);
        }
        //绘制蛇头
        headObj.paintSelf(g);
        //绘制食物
        foodObj.paintSelf(g);
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
