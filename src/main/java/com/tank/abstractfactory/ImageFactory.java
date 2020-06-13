package com.tank.abstractfactory;

import com.tank.Dir;
import com.tank.Group;
import com.tank.TankFrame;
import com.tank.abstractcomonent.BaseBullet;
import com.tank.abstractcomonent.BaseExplode;
import com.tank.abstractcomonent.BaseTank;
import com.tank.componentimpl.ImageBullet;
import com.tank.componentimpl.ImageExplode;
import com.tank.componentimpl.ImageTank;

public class ImageFactory extends GameFactory {


    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        return new ImageBullet(x,y,dir,tankFrame,group);
    }

    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        return new ImageTank(x,y,dir,tankFrame,group);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new ImageExplode(x,y,tankFrame);
    }
}
