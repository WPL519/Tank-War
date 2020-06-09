package com.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    int x = 200,y = 200;
    Dir dir = Dir.DOWN;//定义tank的方向
    private static final int SPEED  = 10;//定义每次tank运动的速度

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
        g.fillRect(x,y,50,50);
//        x+=10;
//        y+=10;
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
    }

    class MyKeyListener extends KeyAdapter{
        //根据按键的情况来设置坦克的状态
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;


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
            if(bL) dir = Dir.LEFT;
            if(bR) dir = Dir.RIGHT;
            if(bU) dir = Dir.UP;
            if(bD) dir = Dir.DOWN;
        }
    }




}
