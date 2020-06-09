import com.tank.PropertyMgr;
import org.junit.Test;


import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class MyTest {

    @Test
    public void test01() throws IOException {
        //BufferedImage i1 = ImageIO.read(new File("src/images/tankL.gif"));

        BufferedImage i2 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/tankL.gif"));
        assertNotNull(i2);
    }

    @Test
    public void test02(){
        System.out.println(PropertyMgr.getValue("initTankCount"));
    }



}
