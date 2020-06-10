package com.tank.com.tank.attackpattern;

import com.tank.Bullet;
import com.tank.Dir;
import com.tank.Tank;

import java.util.*;

public class RoundAttack implements AttackPattern<Tank> {
    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.tank_width/2 - Bullet.bullet_width/2;
        int bY = tank.getY() + Tank.tank_height/2 - Bullet.bullet_height/2;
        List<Dir> dirs = new ArrayList<>(Arrays.asList(Dir.values()));

        dirs.remove(tank.getDir());


        tank.getTf().getBullets().add(new Bullet(bX,bY,tank.getDir(),tank.getTf(),tank.getGroup()));
        for(int i = 0;i<dirs.size();i++)
            tank.getTf().getBullets().add(new Bullet(bX,bY,dirs.get(i),tank.getTf(),tank.getGroup()));

    }
}
