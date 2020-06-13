package com.tank.abstractfactory;

import com.tank.Dir;
import com.tank.Group;
import com.tank.TankFrame;
import com.tank.abstractcomonent.BaseBullet;
import com.tank.abstractcomonent.BaseExplode;
import com.tank.abstractcomonent.BaseTank;

public abstract class GameFactory {

    public abstract BaseBullet createBullet(int x, int y, Dir dir, TankFrame tankFrame,Group group);
    public abstract BaseTank createTank(int x, int y, Dir dir, TankFrame tankFrame,Group group );
    public abstract BaseExplode createExplode(int x, int y, TankFrame tankFrame);
}
