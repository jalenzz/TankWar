package top.royce2003.game.model

import org.itheima.kotlin.game.core.Painter
import top.royce2003.game.Config
import top.royce2003.game.business.Blockable
import top.royce2003.game.business.Moveable

/**
 * 水
 */
class Water(override val x: Int, override val y: Int):Blockable {
    //position
//    override var x:Int = 200
//    override var y:Int = 200

    override var height:Int = Config.block
    override var width:Int = Config.block

    override fun draw() {
        Painter.drawImage("img/water.gif",x,y)
    }
}