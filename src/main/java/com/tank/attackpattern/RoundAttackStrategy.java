package com.tank.attackpattern;

import com.tank.Bullet;
import com.tank.Dir;
import com.tank.Tank;

public class RoundAttackStrategy implements AttackStrategy<Tank> {
    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.tank_width/2 - Bullet.bullet_width/2;
        int bY = tank.getY() + Tank.tank_height/2 - Bullet.bullet_height/2;

        Dir[] dirs = Dir.values();
        for(Dir dir : dirs)
            new Bullet(bX,bY,dir,tank.getTf(),tank.getGroup());


    }
}
