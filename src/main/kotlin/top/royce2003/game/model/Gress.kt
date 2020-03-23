package top.royce2003.game.model

import org.itheima.kotlin.game.core.Painter
import top.royce2003.game.Config

/**
 * 草坪
 */
class Gress(override val x: Int, override val y: Int):View {
    //position
//    override var x:Int = 200
//    override var y:Int = 200

    override var height:Int = Config.block
    override var width:Int = Config.block

    override fun draw() {
        Painter.drawImage("img/grass.gif",x,y)
    }
}