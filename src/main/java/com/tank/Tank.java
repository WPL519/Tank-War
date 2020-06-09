package com.tank;

import java.awt.*;

public class Tank {

    private int x,y;//定义tank位置坐标
    private Dir dir;//定义tank的方向
    private static final int SPEED = 10;//定义tank每次移动的距离

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }



    public void paint(Graphics g) {
        g.fillRect(x,y,50,50);
//        x+=10;
//        y+=10;
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
    }
}
