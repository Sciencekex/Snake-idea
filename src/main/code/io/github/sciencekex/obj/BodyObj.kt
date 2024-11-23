package io.github.sciencekex.obj

import io.github.sciencekex.GameWin
import java.awt.Graphics
import java.awt.Image

class BodyObj(img: Image?, x: Int, y: Int, frame: GameWin?) : GameObj(img, x, y, frame) {
    override fun paintSelf(g: Graphics) {
        super.paintSelf(g)
    }
}
