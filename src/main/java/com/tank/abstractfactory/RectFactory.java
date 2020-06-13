package com.tank.abstractfactory;

import com.tank.Dir;
import com.tank.Group;
import com.tank.TankFrame;
import com.tank.abstractcomonent.BaseBullet;
import com.tank.abstractcomonent.BaseExplode;
import com.tank.abstractcomonent.BaseTank;
import com.tank.componentimpl.RectBullet;
import com.tank.componentimpl.RectExplode;
import com.tank.componentimpl.RectTank;

public class RectFactory extends GameFactory {


    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        return new RectBullet(x,y,dir,tankFrame,group);
    }

    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        return new RectTank(x,y,dir,tankFrame,group);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new RectExplode(x,y,tankFrame);
    }
}
