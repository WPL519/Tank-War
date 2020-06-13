package com.tank.attackstrategy;

//import com.tank.Bullet;
import com.tank.abstractcomonent.BaseTank;
import com.tank.componentimpl.RectBullet;
import com.tank.componentimpl.RectTank;

public class ContinuousAttack implements AttackStrategy<BaseTank> {
    @Override
    public void fire(BaseTank tank) {
        int bX_1 = tank.getX() + RectTank.tank_width/2 - RectBullet.bullet_width/2;
        int bY_1 = tank.getY() + RectTank.tank_height/2 - RectBullet.bullet_height/2;
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
        new RectBullet(bX_1,bY_1,tank.getDir(),tank.getTf(),tank.getGroup());
        new RectBullet(bX_2,bY_2,tank.getDir(),tank.getTf(),tank.getGroup());
    }
}
