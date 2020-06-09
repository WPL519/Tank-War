package com.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 1;
    private static final int width = 30;
    private static final int height = 30;
    private int x,y;
    private Dir dir;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x,y,width,height);
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
    }
}
