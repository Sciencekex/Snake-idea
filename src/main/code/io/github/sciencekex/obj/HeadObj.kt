package io.github.sciencekex.obj

import io.github.sciencekex.GameWin
import io.github.sciencekex.utils.GameUtils
import io.github.sciencekex.utils.GameUtils.drawWord
import java.awt.Color
import java.awt.Graphics
import java.awt.Image
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class HeadObj(img: Image?, x: Int, y: Int, frame: GameWin?) : GameObj(img, x, y, frame) {
    //方向 up down left right
    var direction: String = "right"

    init {
        //键盘监听事件
        this.frame.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                changeDirection(e)
            }
        })
    }

    //控制移动方向
    fun changeDirection(e: KeyEvent) {
        when (e.getKeyCode()) {
            KeyEvent.VK_UP -> if ("down" != direction) {
                direction = "up"
            }

            KeyEvent.VK_DOWN -> if ("up" != direction) {
                direction = "down"
            }

            KeyEvent.VK_LEFT -> if ("right" != direction) {
                direction = "left"
            }

            KeyEvent.VK_RIGHT -> if ("left" != direction) {
                direction = "right"
            }

            else -> println("输入有误")
        }
    }

    //蛇的移动
    fun move() {
        //蛇身体的移动
        val bodyObjList = this.frame.bodyObjList
        for (i in bodyObjList.size - 1 downTo 1) {
            bodyObjList.get(i)!!.x = bodyObjList.get(i - 1)!!.x
            bodyObjList.get(i)!!.y = bodyObjList.get(i - 1)!!.y
            //碰撞判断
            if (this.x == bodyObjList.get(i)!!.x && this.y == bodyObjList.get(i)!!.y) {
                var g: Graphics
                if (this.frame.g != null) {
                    g = this.frame.g!!
                } else {
                    g = this.frame.getGraphics()
                }
                drawWord(g, "请自行结束", Color.RED, 60, 100, 100)

                //bug，Runtime.getRuntime().halt(0);
            }
        }
        bodyObjList.get(0)!!.x = this.x
        bodyObjList.get(0)!!.y = this.y

        //蛇头的移动
        when (direction) {
            "left" -> x -= width
            "right" -> x += width
            "up" -> y -= height
            "down" -> y += height
            else -> println("输入有误")
        }
    }


    override fun paintSelf(g: Graphics) {
        super.paintSelf(g)
        //食物
        val food = this.frame.foodObj
        //身体最后一节的坐标
        var newX: Int? = null
        var newY: Int? = null

        if (this.x == food.x && this.y == food.y) {
            this.frame.foodObj = food.getFood()
            //获取蛇身的最后一个元素
            val lastBody = this.frame.bodyObjList.get(this.frame.bodyObjList.size - 1)
            newX = lastBody!!.x
            newY = lastBody.y
        }
        move()
        if (newX != null && newY != null) {
            this.frame.bodyObjList.add(BodyObj(GameUtils.bodyImg, newX, newY, this.frame))
        }
        //越界处理
        if (x <= 0) {
            x = 600
        } else if (x > 600) {
            x = 0
        } else if (y < 0) {
            y = 600
        } else if (y > 600) {
            y = 0
        }
    }
}
