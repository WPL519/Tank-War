package com.tank.componentimpl;

import com.tank.*;
import com.tank.abstractcomonent.BaseTank;
import com.tank.attackstrategy.AttackStrategy;
import com.tank.attackstrategy.DefaultAttackStrategy;
import com.tank.attackstrategy.RoundAttackStrategy;

import java.awt.*;
import java.util.Random;

public class ImageTank extends BaseTank {



    private int x,y;//定义tank位置坐标
    private Dir dir;//定义tank的方向
    private static final int SPEED = Integer.parseInt(PropertyMgr.getValue("TankSpeed"));//定义tank每次移动的距离
    private boolean moving  = true;//设定tank的状态
    private TankFrame tf ;//引用TankFrame，使自己创建出来的子弹对象能够绘制出来
    public static final int tank_width = ResourceMgr.tankD.getWidth();
    public static final int tank_height = ResourceMgr.tankD.getHeight();
    private boolean isAlive = true;
    private Random random = new Random();
    private Group group = Group.BAD;

    //如果传入到fire方法里面，每次调用，都需要new，因此，应该把每个攻击策略都改写成单例模式
    AttackStrategy as;

    public ImageTank(int x, int y, Dir dir,TankFrame tf,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        rect.x = x;
        rect.y = y;
        rect.width = tank_width;
        rect.height = tank_height;

        if(this.group == Group.GOOD){
            try {
                as = (RoundAttackStrategy)Class.forName(PropertyMgr.getValue("GoodTankAS")).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(this.group == Group.BAD){
            try {
                as = (DefaultAttackStrategy)Class.forName(PropertyMgr.getValue("BadTankAS")).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public TankFrame getTf() {
        return tf;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        if(!isAlive){
            tf.tanks.remove(this);
        }


        //用图片代替tank
        switch (dir){
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            default:
                break;
        }

        //抽象一个新的方法来控制tank的移动
        move();
    }

    private void move() {

        if(!moving) return;
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



        if(this.group == Group.BAD && random.nextInt(100)>95)
            this.fire();
        if(this.group == Group.BAD&&random.nextInt(100)>95)
            randomDir();

        boundsCheck();

        rect.x = x;
        rect.y = y;

    }

    private void boundsCheck() {
        if(this.x < 2)  x = 2;
        if(this.y < 28) y = 28;
        if(this.x > TankFrame.GAME_WIDTH-tank_width - 2) x = TankFrame.GAME_WIDTH - tank_width - 2;
        if(this.y > TankFrame.GAME_HEIGHT-tank_height - 2) y = TankFrame.GAME_HEIGHT - tank_height - 2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void die() {
        this.isAlive = false;
    }

    public void fire() {
        as.fire(this);
    }
}