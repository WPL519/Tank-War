package com.tank.com.tank.attackpattern;

import com.tank.Bullet;
import com.tank.Tank;
import com.tank.TankFrame;

public class SingleAttack implements AttackPattern<Tank> {
    @Override
    public void fire(Tank tank) {


        int bX = tank.getX() + Tank.tank_width/2 - Bullet.bullet_width/2;
        int bY = tank.getY() + Tank.tank_height/2 - Bullet.bullet_height/2;
        tank.getTf().getBullets().add(new Bullet(bX,bY,tank.getDir(),tank.getTf(),tank.getGroup()));
    }
}
