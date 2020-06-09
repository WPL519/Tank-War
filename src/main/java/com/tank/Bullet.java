package com.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 5;
    private static final int width = 10;
    private static final int height = 10;
    private int x,y;
    private Dir dir;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,width,height);
        g.setColor(color);
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
