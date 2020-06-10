package com.tank.com.tank.attackpattern;

import com.tank.Bullet;
import com.tank.Tank;

public class ContinuousAttack implements AttackPattern<Tank>{
    @Override
    public void fire(Tank tank) {
        int bX_1 = tank.getX() + Tank.tank_width/2 - Bullet.bullet_width/2;
        int bY_1 = tank.getY() + Tank.tank_height/2 - Bullet.bullet_height/2;
        int bX_2=bX_1,bY_2=bY_1;
        switch (tank.getDir()){
            case DOWN:
                bY_2 = bY_1 + 30;
                break;
            case UP:
                bY_2 = bY_1 - 30;
                break;
            case LEFT:
                bX_2 = bX_1 - 30;
                break;
            case RIGHT:
                bX_2 = bX_1 + 30;
            default:
                break;
        }
        tank.getTf().getBullets().add(new Bullet(bX_1,bY_1,tank.getDir(),tank.getTf(),tank.getGroup()));
        tank.getTf().getBullets().add(new Bullet(bX_2,bY_2,tank.getDir(),tank.getTf(),tank.getGroup()));
    }
}
