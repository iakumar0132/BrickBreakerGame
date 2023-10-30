package game;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import map.Mapgenerator;
import javax.swing.*;
public class Gameplay extends JPanel implements ActionListener, KeyListener {
    private boolean play=false;
    private int score=0;
    private int totalbricks=21;
    private Timer timer;
    private int delay=8;
    private int ballposx=120;
    private int ballposy=350;
    private int balldirx=-2;
    private int balldiry=-3;
    private int playerx=350;
    private Mapgenerator map;
    public Gameplay()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer=new Timer(delay,this);
        timer.start();
        map=new Mapgenerator(3,7);
    }
    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(1,1,692,592);

        g.setColor(Color.yellow);
        g.fillRect(0,0,692,5);
        g.fillRect(0,3,3,592);
        g.fillRect(682,3,3,592);

        g.setColor(Color.GREEN);
        g.fillRect(playerx,550,100,8);

        g.setColor(Color.RED);
        g.fillOval(ballposx,ballposy,20,20);
        map.draw((Graphics2D)g);

        g.setColor(Color.GREEN);
        g.setFont(new Font("serif",Font.BOLD,20));
        g.drawString("Score:"+score,550,30);
        if(totalbricks==0)
        {
            g.setColor(Color.GREEN);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("YOU WON",250,300);
            play=false;
        }
        if(ballposy>=600)
        {
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("YOU LOST!!",250,300);
            play=false;
        }
        if(play==false)
        {
            g.setColor(Color.WHITE);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("PRESS SPACE TO START",150,400);
        }
    }
    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            if(playerx<0)
            {
                playerx=0;
            }else{
            moveLeft();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            if(playerx>600)
            {
                playerx=600;
            }else{
            moveRight();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE)
        {
            if(play==false)
            {
                balldirx=-2;
                balldiry=-3;
                ballposx=120;
                ballposy=350;
                playerx=350;
                score=0;
                totalbricks=21;
                map=new Mapgenerator(3, 7);
                play=true;
            }
            
        }
        repaint();
    }
   
    private void moveRight() {
        play=true;
        playerx=playerx+20;
    }
    private void moveLeft() {
        play=true;
        playerx=playerx-20;
    }
    public void keyReleased(KeyEvent e) {}
    public void actionPerformed(ActionEvent k) {
        if(play==true)
        {
            if(ballposx<=0)
            {
                balldirx=-balldirx;
            }
            if(ballposx>=670)
            {
                balldirx=-balldirx;
            }
            if(ballposy<=0)
            {
                balldiry=-balldiry;
            }
            Rectangle ballrect=new Rectangle(ballposx,ballposy,20,20);
            Rectangle paddlerect=new Rectangle(playerx,550,100,8);
            if(ballrect.intersects(paddlerect))
            {
                balldiry=-balldiry;
            }
            for(int i=0;i<map.map.length;i++)
            {
                for(int j=0;j<map.map[0].length;j++)
                {
                    if(map.map[i][j]>0)
                    {
                        int brwidth=map.brickwidth;
                        int brheight=map.brickheight;
                        int brickposx=80+j*brwidth;
                        int brickposy=50+i*brheight;
                        Rectangle brickrect=new Rectangle(brickposx,brickposy,brwidth,brheight);
                        if(ballrect.intersects(brickrect))
                        {
                            map.setbrick(0,i,j);
                            totalbricks--;
                            score++;
                            if(ballposx<=brickposx || ballposx+1>=brickposx+brwidth)
                            {
                                balldirx=-balldirx;
                            }
                            else{
                                balldiry=-balldiry;
                            }
                        }
                    }
                }
            }
            ballposx=ballposx+balldirx;
            ballposy=ballposy+balldiry;
            JLabel l1=new JLabel();
        }
        repaint();
    }
}