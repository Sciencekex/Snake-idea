package io.github.sciencekex.obj

import io.github.sciencekex.GameWin
import io.github.sciencekex.utils.GameUtils
import java.awt.Graphics
import java.awt.Image
import java.util.Random

class FoodObj : GameObj {
    //随机函数
    var r: Random = Random()

    constructor() : super()

    constructor(img: Image?, x: Int, y: Int, frame: GameWin?) : super(img, x, y, frame)

    //获取食物
    fun getFood(): FoodObj {
        return FoodObj(GameUtils.foodImg, r.nextInt(20) * 30, (r.nextInt(19) + 1) * 30, this.frame)
    }

    override fun paintSelf(g: Graphics) {
        super.paintSelf(g)
    }
}
