import com.tank.*;
import com.tank.componentimpl.ImageTank;

import java.awt.*;

public class Main {

    public static void main(String[] args) throws Exception {
        TankFrame tf = new TankFrame();
        //在主线程每隔50毫秒刷新窗口，调用repaint方法重画
        int initTankCount = Integer.parseInt(PropertyMgr.getValue("initTankCount"));
        for(int i = 0;i < initTankCount;i++){
            tf.tanks.add(tf.gameFactory.createTank((80+80*i),200,Dir.DOWN,tf, Group.BAD));
        }

        while (true){
            Thread.sleep(50);
            tf.repaint();
        }

    }
}
