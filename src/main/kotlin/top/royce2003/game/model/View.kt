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

    //检查碰撞
    fun checkCollision(x1:Int, y1:Int, w1:Int, h1:Int,
                       x2:Int, y2:Int, w2:Int, h2:Int):Boolean {

        return when {
            y2 + h2 <= y1 ->
                false                   //阻挡在坦克上方
            y1 + h1 <= y2 ->
                false                   //阻挡在坦克下方
            x2 + w2 <= x1 ->
                false                   //阻挡在坦克左方
            else -> x1 + w1 > x2   //阻挡在坦克右方?
        }

    }


}