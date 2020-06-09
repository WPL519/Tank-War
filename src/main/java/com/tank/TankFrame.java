package com.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

//    int x = 200,y = 200;
    Dir dir = Dir.DOWN;//定义tank的方向
//    private static final int SPEED  = 10;//定义每次tank运动的速度

    Tank myTank = new Tank(200,200,Dir.DOWN);

    public TankFrame(){
        setSize(800,600);//设置窗口的大小
        setResizable(false);//设置窗口是否可以改变大小
        setTitle("Tank War");
        setVisible(true);
        this.addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {//内部类实现监听窗口关闭
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * paint方法窗口重新绘制就会被调用，Graphis参数相当于一只画笔
     * @param g
     */
    @Override
    public void paint(Graphics g){
        //Tank类要自己控制自己移动，所以自己封装paint方法，并且把这只画笔g交给tank.
        myTank.paint(g);

    }

    class MyKeyListener extends KeyAdapter{
        //根据按键的情况来设置坦克的状态
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        //记录按键来设置tank的方向
        @Override
        public void keyPressed(KeyEvent e) {
            //System.out.println("key pressed");
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
//                    x-=10;
                    bL = true;

                    break;
                case KeyEvent.VK_RIGHT:
//                    x+=10;
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
//                    y-=10;
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
//                    y+=10;
                    bD = true;
                    break;
                default:
                    break;
            }

            setMainTankDir();

        }

        @Override
        public void keyReleased(KeyEvent e) {
            //System.out.println("key realeased");
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
//                    x-=10;
                    bL = false;

                    break;
                case KeyEvent.VK_RIGHT:
//                    x+=10;
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
//                    y-=10;
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
//                    y+=10;
                    bD = false;
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {

            if(!bL && !bR && !bU && !bD){//如果没有按键按下来，tank的状态是为静止
                myTank.setMoving(false);
            }else {//有按键按下来，tank的状态为运动，并且记录tank的方向
                myTank.setMoving(true);
                if(bL) myTank.setDir(Dir.LEFT);
                if(bR) myTank.setDir(Dir.RIGHT);
                if(bU) myTank.setDir(Dir.UP);
                if(bD) myTank.setDir(Dir.DOWN);
            }



        }
    }




}
