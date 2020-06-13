package com.tank.componentimpl;

import com.tank.*;
import com.tank.abstractcomonent.BaseBullet;
import com.tank.abstractcomonent.BaseTank;

import java.awt.*;

public class RectBullet extends BaseBullet {

    private static final int SPEED = Integer.parseInt(PropertyMgr.getValue("BulletSpeed"));
    public static final int bullet_width = 10;
    public static final int bullet_height = 10;
    private int x,y;
    private Dir dir;
    private boolean isAlive = true;
    private TankFrame tf;
    private Group group = Group.BAD;
    private Rectangle rect = new Rectangle();

    public RectBullet(int x, int y, Dir dir,TankFrame tf,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        rect.x = x;
        rect.y = y;
        rect.width = bullet_width;
        rect.height = bullet_height;

        tf.bullets.add(this);
    }
    public boolean isAlive() {
        return isAlive;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if(!isAlive){
            tf.bullets.remove(this);//使用foreach遍历会出现：java.util.ConcurrentModificationException。因为在使用foreach时候是使用迭代器进行遍历的，所以删除时候只允许迭代器删除，不允许其他方式的删除
        }

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, bullet_width, bullet_height);
        g.setColor(c);

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

        rect.x = x;
        rect.y = y;

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT)
            isAlive = false;
    }
    @Override
    public void collideWith(BaseTank tank) {

        if(this.group == tank.getGroup()) return ;//是一波的就不碰撞，不做任何操作

        //得到两个对象的方块，完全可以用一个rect记录一个子弹的位置，new出来太多会占用内存.rect跟着坦克和子弹走
//        Rectangle rect1 = new Rectangle(this.x,this.y,bullet_width,bullet_height);
//        Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),Tank.tank_width, Tank.tank_height);
        //是否相交
        if(rect.intersects(tank.rect)){
            tank.die();
            this.die();
            int eX = x + RectTank.tank_width/2 - RectExplode.explode_width/2;
            int eY = y + RectTank.tank_height/2 - RectExplode.explode_height/2;
            tf.explodes.add(tf.gameFactory.createExplode(eX,eY,tf));

        }


    }

    private void die() {
        this.isAlive = false;
    }
}
