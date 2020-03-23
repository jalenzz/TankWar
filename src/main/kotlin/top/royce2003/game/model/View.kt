package top.royce2003.game.model

/**
 * 显示规范
 */
interface View {
    val x:Int
    val y:Int

    val width:Int
    val height:Int

    fun draw()
}