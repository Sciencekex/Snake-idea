package io.github.sciencekex.utils

import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Image
import java.awt.Toolkit

object GameUtils {
    //蛇头图片引入
    @kotlin.jvm.JvmField
    var headImg: Image? = Toolkit.getDefaultToolkit().getImage("src/main/resources/img/head.png")

    //蛇身图片引入
    @kotlin.jvm.JvmField
    var bodyImg: Image? = Toolkit.getDefaultToolkit().getImage("src/main/resources/img/body.png")

    //食物图片引入
    @kotlin.jvm.JvmField
    var foodImg: Image? = Toolkit.getDefaultToolkit().getImage("src/main/resources/img/food.png")

    //绘制文字
    @kotlin.jvm.JvmStatic
    fun drawWord(g: Graphics, str: String, color: Color?, size: Int, x: Int, y: Int) {
        g.setColor(color)
        g.setFont(Font("微软雅黑", Font.BOLD, size))
        g.drawString(str, x, y)
    }
}
