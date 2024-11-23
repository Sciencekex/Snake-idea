//@作者署名 作者
/*
 * @作者:sciencekex
 * @时间:2024年06月04日 16:02:01
 */

//kotlin开始重构
package io.github.sciencekex

import io.github.sciencekex.obj.BodyObj
import io.github.sciencekex.obj.FoodObj
import io.github.sciencekex.obj.HeadObj
import io.github.sciencekex.utils.GameUtils
import java.awt.Color
import java.awt.Graphics
import java.util.ArrayList
import javax.swing.JFrame

class GameWin : JFrame() {
    @JvmField
    var g: Graphics? = null

    //蛇头对象
    var headObj: HeadObj = HeadObj(GameUtils.headImg, 30, 570, this@GameWin)

    //蛇的身体集合
    @JvmField
    var bodyObjList: MutableList<BodyObj?> = ArrayList<BodyObj?>()

    //食物
    @JvmField
    var foodObj: FoodObj = FoodObj().getFood()
    fun launch() {
        this.setVisible(true)
        this.setSize(600, 600)
        this.setLocationRelativeTo(null)
        this.setTitle("贪吃蛇")

        //蛇身体的初始化
        bodyObjList.add(BodyObj(GameUtils.bodyImg, 30, 570, this))
        bodyObjList.add(BodyObj(GameUtils.bodyImg, 0, 570, this))

        while (true) {
            repaint()
            try {
                //1秒1000毫秒
                Thread.sleep(200)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    override fun paint(g: Graphics) {
        //灰色背景
        g.setColor(Color.GRAY)
        //填充矩形
        g.fillRect(0, 0, 600, 600)
        //网格线
        g.setColor(Color.BLACK)
        run {
            var i = 0
            while (i < 600) {
                g.drawLine(0, i, 600, i)
                g.drawLine(i, 0, i, 600)
                i += 30
            }
        }
        //绘制蛇身体
        for (i in bodyObjList.indices.reversed()) {
            bodyObjList.get(i)!!.paintSelf(g)
        }
        //绘制蛇头
        headObj.paintSelf(g)
        //绘制食物
        foodObj.paintSelf(g)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val gameWin = GameWin()
            gameWin.launch()
        }
    }
}
