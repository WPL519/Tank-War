import com.tank.Dir;
import com.tank.Group;
import com.tank.Tank;
import com.tank.TankFrame;

import java.awt.*;

public class Main {

    public static void main(String[] args) throws Exception {
        TankFrame tf = new TankFrame();
        //在主线程每隔50毫秒刷新窗口，调用repaint方法重画
        for(int i = 0;i < 5;i++){
            tf.getTanks().add(new Tank((80+80*i),200,Dir.DOWN,tf, Group.BAD));
        }

        while (true){
            Thread.sleep(50);
            tf.repaint();
        }

    }
}
