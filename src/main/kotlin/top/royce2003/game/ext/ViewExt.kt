package top.royce2003.game.ext

import top.royce2003.game.model.View

fun View. checkCollision(view:View): Boolean {

    return checkCollision(x,y,width,height,view.x,view.y,view.width,view.height)

}