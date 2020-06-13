package com.tank.abstractcomonent;

import com.tank.*;

import java.awt.*;
import java.util.Random;

public abstract class BaseTank {

    public Rectangle rect = new Rectangle();

    public Group group = Group.BAD;
    public abstract void paint(Graphics graphics);
    public abstract void fire();

    public Group getGroup() {
        return group;
    }

    public abstract void die();

    public abstract int getX();

    public abstract int getY();

    public abstract TankFrame getTf();

    public abstract Dir getDir();

    public abstract void setMoving(boolean b);

    public abstract void setDir(Dir left);
}
