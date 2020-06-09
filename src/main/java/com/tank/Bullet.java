package com.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 5;
    private static final int width = 15;
    private static final int height = 15;
    private int x,y;
    private Dir dir;
    private boolean isAlive = true;
    private TankFrame tf;

    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void paint(Graphics g) {
        if(!isAlive){
            tf.bullets.remove(this);//使用foreach遍历会出现：java.util.ConcurrentModificationException。因为在使用foreach时候是使用迭代器进行遍历的，所以删除时候只允许迭代器删除，不允许其他方式的删除
        }


        Color color = g.getColor();
        g.setColor(Color.RED);
        switch (dir){
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            default:
                break;
        }
        //抽象一个新的方法来控制tank的移动
        move();
    }

    private void move() {


        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT)
            isAlive = false;
    }
}
