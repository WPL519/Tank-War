package com.tank.abstractcomonent;

import com.tank.*;

import java.awt.*;

public  abstract class BaseBullet {

    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank tank);
}
