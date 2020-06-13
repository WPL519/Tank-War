package com.tank.componentimpl;

import com.tank.Audio;
import com.tank.ResourceMgr;
import com.tank.TankFrame;
import com.tank.abstractcomonent.BaseExplode;

import java.awt.*;

public class ImageExplode extends BaseExplode {

    public static final int explode_width = ResourceMgr.explodes[0].getWidth();
    public static final int explode_height = ResourceMgr.explodes[0].getHeight();
    private int x,y;

    private boolean isAlive = true;
    private TankFrame tf;
    private int step = 0;


    public ImageExplode(int x, int y,  TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Audio("audio/explode.wav").play();
    }

    public boolean isAlive() {
        return isAlive;
    }



    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step >= ResourceMgr.explodes.length)
            tf.explodes.remove(this);

    }

}
