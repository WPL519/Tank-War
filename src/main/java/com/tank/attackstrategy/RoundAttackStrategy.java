package com.tank.attackstrategy;

//import com.tank.Bullet;
import com.tank.Dir;
import com.tank.abstractcomonent.BaseTank;
import com.tank.componentimpl.ImageBullet;
import com.tank.componentimpl.ImageTank;

public class RoundAttackStrategy implements AttackStrategy<BaseTank> {
    @Override
    public void fire(BaseTank tank) {
        int bX = tank.getX() + ImageTank.tank_width/2 - ImageBullet.bullet_width/2;
        int bY = tank.getY() + ImageTank.tank_height/2 - ImageBullet.bullet_height/2;

        Dir[] dirs = Dir.values();
        for(Dir dir : dirs)
            new ImageBullet(bX,bY,dir,tank.getTf(),tank.getGroup());


    }


}
