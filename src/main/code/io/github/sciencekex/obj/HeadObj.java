package io.github.sciencekex.obj;

import io.github.sciencekex.GameWin;
import io.github.sciencekex.utils.GameUtils;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class HeadObj extends GameObj{
    //方向 up down left right
    public String direction="right";

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public HeadObj(Image img, int x, int y, GameWin frame) {
        super(img, x, y, frame);
        //键盘监听事件
        this.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                changeDirection(e);
            }
        });
    }

    //控制移动方向
    public void changeDirection(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                if (!"down".equals(direction)){
                    direction="up";
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!"up".equals(direction)){
                    direction="down";
                }
                break;
            case KeyEvent.VK_LEFT:
                if (!"right".equals(direction)){
                    direction="left";
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!"left".equals(direction)){
                    direction="right";
                }
                break;
            default:
                System.out.println("输入有误");
        }
    }
    //蛇的移动
    public void move(){
        //蛇身体的移动
        List<BodyObj> bodyObjList=this.frame.bodyObjList;
        for(int i=bodyObjList.size()-1;i>=1;i--){
            bodyObjList.get(i).x=bodyObjList.get(i-1).x;
            bodyObjList.get(i).y=bodyObjList.get(i-1).y;
            //碰撞判断
            if(this.x==bodyObjList.get(i).x&&this.y==bodyObjList.get(i).y){
                Graphics g;
                if(this.frame.g!=null){
                    g=this.frame.g;
                }else{
                    g=this.frame.getGraphics();
                }
                GameUtils.drawWord(g, "请自行结束", Color.RED, 60, 100, 100);
                //bug，Runtime.getRuntime().halt(0);

            }
        }
        bodyObjList.get(0).x=this.x;
        bodyObjList.get(0).y=this.y;

        //蛇头的移动
        switch (direction){
            case "left":
                x-=width;
                break;
            case "right":
                x+=width;
                break;
            case "up":
                y-=height;
                break;
            case "down":
                y+=height;
                break;
                default:
                    System.out.println("输入有误");
        }
    }


    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        //食物
        FoodObj food=this.frame.foodObj;
        //身体最后一节的坐标
        Integer newX=null;
        Integer newY=null;

        if(this.x==food.x&&this.y==food.y){
            this.frame.foodObj=food.getFood();
            //获取蛇身的最后一个元素
            BodyObj lastBody=this.frame.bodyObjList.get(this.frame.bodyObjList.size()-1);
            newX= lastBody.x;
            newY= lastBody.y;

        }
        move();
        if(newX!=null&&newY!=null){
            this.frame.bodyObjList.add(new BodyObj(GameUtils.bodyImg,newX,newY,this.frame));
        }
        //越界处理
        if (x<=0) {
            x = 600;
        }else if(x>600){
            x=0;
        }else if(y<0){
            y=600;
        }else if(y>600){
            y=0;
        }

    }
}
