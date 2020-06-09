package com.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TankFrame extends Frame {


    static final int GAME_WIDTH = 800;
    static final int GAME_HEIGHT = 600;
    List<Bullet> bullets = new ArrayList<>();//子弹飞出窗口，不消除就会出现内存泄露问题

    Tank myTank = new Tank(200,200,Dir.DOWN,this);
    //Bullet b = new Bullet(300,300,Dir.DOWN,this);
    Tank enemyTank = new Tank(400,500,Dir.UP,this);

    public TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);//设置窗口的大小
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

    Image offScreenImage = null;
    //解决对象移动时的闪烁问题（游戏中的双缓冲概念） ，update方法会在paint方法之前调用，系统劫用画笔g，先在内存中画出整张背景图
    //然后交还给paint方法，画出坦克、子弹对象，画完之后把所有画面直接显示到屏幕上，就解决了闪烁问题。

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);//把画笔交还给paint方法
        g.drawImage(offScreenImage, 0, 0, null);//把内存中的画面一下子画到屏幕上
    }

    /**
     * paint方法窗口重新绘制就会被调用，Graphis参数相当于一只画笔
     * @param g
     */
    @Override
    public void paint(Graphics g){
        //Tank类要自己控制自己移动，所以自己封装paint方法，并且把这只画笔g交给tank.
        Color color = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量"+bullets.size(),10,50);
        g.setColor(color);

        myTank.paint(g);

        enemyTank.paint(g);

        for(int i = 0;i < bullets.size();i++){
            bullets.get(i).paint(g);
        }
        //也可以使用这种方式进行删除，不会出现异常
//        for(Iterator<Bullet> it = bullets.iterator();it.hasNext();){
//            Bullet b = it.next();
//            if(!b.isAlive()){
//                it.remove();
//            }
//        }


    }

    class MyKeyListener extends KeyAdapter{
        //根据按键的情况来设置坦克的状态
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        boolean ebL = false;
        boolean ebU = false;
        boolean ebR = false;
        boolean ebD = false;

        //记录按键来设置tank的方向
        @Override
        public void keyPressed(KeyEvent e) {
            //System.out.println("key pressed");
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_J:
                    ebL = true;
                    break;
                case KeyEvent.VK_L:
                    ebR = true;
                    break;
                case KeyEvent.VK_I:
                    ebU = true;
                    break;
                case KeyEvent.VK_K:
                    ebD = true;
                    break;
                default:
                    break;
            }

            setMainTankDir();
            setEmenyTankDir();

        }

        @Override
        public void keyReleased(KeyEvent e) {
            //System.out.println("key realeased");
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_J:
                    ebL = false;
                    break;
                case KeyEvent.VK_L:
                    ebR = false;
                    break;
                case KeyEvent.VK_I:
                    ebU = false;
                    break;
                case KeyEvent.VK_K:
                    ebD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();//此处的fire（）方法不应该有返回对象，此时应该去看具体的坦克内部的fire策略去进行绘制
                    break;
                case KeyEvent.VK_0:
                    enemyTank.fire();
                    break;

                default:
                    break;
            }

            setMainTankDir();
            setEmenyTankDir();
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

        private void setEmenyTankDir() {

            if(!ebL && !ebR && !ebU && !ebD){//如果没有按键按下来，tank的状态是为静止
                enemyTank.setMoving(false);
            }else {//有按键按下来，tank的状态为运动，并且记录tank的方向
                enemyTank.setMoving(true);
                if(ebL) enemyTank.setDir(Dir.LEFT);
                if(ebR) enemyTank.setDir(Dir.RIGHT);
                if(ebU) enemyTank.setDir(Dir.UP);
                if(ebD) enemyTank.setDir(Dir.DOWN);
            }
        }


    }




}
