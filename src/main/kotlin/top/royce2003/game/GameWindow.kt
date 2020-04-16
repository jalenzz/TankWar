package top.royce2003.game

import javafx.scene.input.KeyCode

import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Window
import top.royce2003.game.business.AutoMoveable
import top.royce2003.game.enums.Direction
import top.royce2003.game.model.*
import top.royce2003.game.business.Blockable
import top.royce2003.game.business.Destroyable
import top.royce2003.game.business.Moveable
import java.io.File
import java.util.concurrent.CopyOnWriteArrayList

class GameWindow: Window (
    title = "Tank 1.0",
    icon = "/img/camp.gif",
    width = Config.gameWith,
    height = Config.gameHeight
) {

    //管理全部元素
//    private val views = arrayListOf<View>()

    // 线程安全集合
    private val views = CopyOnWriteArrayList<View>()

    private lateinit var tank:Tank


    override fun onCreate() {
        //地图

        val file = File(javaClass.getResource("/map/1.map").path)
        val lines:List<String> = file.readLines()

        var lineNum = 0

        lines.forEach { it ->
            var columNum = 0
            it.toCharArray().forEach {
                when(it) {
                    //Z砖 T铁 C 草 S空
                    'Z' -> views.add(Wall(columNum * Config.block, lineNum * Config.block))
                    'T' -> views.add(Steel(columNum * Config.block, lineNum * Config.block))
                    'C' -> views.add(Gress(columNum * Config.block, lineNum * Config.block))
                    'S' -> views.add(Water(columNum * Config.block, lineNum * Config.block))
                }
                columNum++
            }
            lineNum++
        }

        tank = Tank(Config.block * 10, Config.block * 12)
        views.add(tank)

    }


    override fun onDisplay() {

        // 绘制
        views.forEach {
            it.draw()
        }

        println(views.size)

    }


    override fun onKeyPressed(event: KeyEvent) {

        when(event.code) {

            // 移动
            KeyCode.W -> tank.move(Direction.UP)
            KeyCode.S -> tank.move(Direction.DOWN)
            KeyCode.A -> tank.move(Direction.LEFT)
            KeyCode.D -> tank.move(Direction.RIGHT)

            // 发射子弹
            KeyCode.SPACE -> views.add(tank.shot())

        }

    }


    override fun onRefresh() {

        //业务逻辑
        views.filter { it is Moveable }.forEach { move ->

            move as Moveable

            var badDirection: Direction? = null
            var badBlock:Blockable? = null

            views.filter { it is Blockable }.forEach blockTag@{ block ->
                block as Blockable

                val direction:Direction? = move.willCollision(block)

                direction?.let {
                    badDirection = direction
                    badBlock = block
                    return@blockTag
                }
            }

            move.notice(badDirection, badBlock)

        }

        // 自动移动
        views.filter { it is AutoMoveable }.forEach {
            (it as AutoMoveable).autoMove()
        }

        // 销毁
        views.filter { it is Destroyable }.forEach {

            // 是否出界
            if ((it as Destroyable).isDestroyed()) {
                views.remove(it)
            }

        }

    }
}